#### 索引 ####
from langchain_nomic.embeddings import NomicEmbeddings
from langchain_community.embeddings import DashScopeEmbeddings
# 加载文档
import bs4
from dotenv import load_dotenv
import os

DASHSCOPE_API_KEY = os.getenv("DASHSCOPE_API_KEY")

load_dotenv()
from langchain_community.document_loaders import WebBaseLoader

loader = WebBaseLoader(
    web_paths=("https://baike.baidu.com/item/%E4%BA%BA%E5%B7%A5%E6%99%BA%E8%83%BD/9180?fr=ge_ala#9-10",)
)
blog_docs = loader.load()
print(blog_docs)

# 拆分
from langchain_text_splitters import RecursiveCharacterTextSplitter

text_splitter = RecursiveCharacterTextSplitter.from_tiktoken_encoder(
    chunk_size=300,
    chunk_overlap=50)

splits = text_splitter.split_documents(blog_docs)

# 索引
from langchain_chroma import Chroma

embeddings = DashScopeEmbeddings(
    model="text-embedding-v1", dashscope_api_key=DASHSCOPE_API_KEY
)
vectorstore = Chroma.from_documents(documents=splits,
                                    embedding=embeddings)

retriever = vectorstore.as_retriever(search_kwargs={"k": 5})
# print(retriever)


from langchain.prompts import ChatPromptTemplate

# 多重查询：不同视角
template = """你是一名 AI 语言模型助手。你的任务是生成给定用户问题的五个不同版本，
以从向量数据库中检索相关文档。通过生成用户问题的多个视角，你
的目标是帮助用户克服基于距离的相似性搜索的一些限制。
提供这些以换行符分隔的备选问题。原始问题：{question}"""
prompt_perspectives = ChatPromptTemplate.from_template(template)

from langchain_core.output_parsers import StrOutputParser
from langchain_openai import ChatOpenAI
from dotenv import load_dotenv

load_dotenv()
llm = ChatOpenAI(model='qwen-max')
generate_queries = (
        prompt_perspectives
        | llm
        | StrOutputParser()
        | (lambda x: x.split("\n"))
)

from langchain.load import dumps, loads


def get_unique_union(documents: list[list]):
    """ 检索到的文档的唯一联合 """
    # 展平列表列表，并将每个文档转换为字符串
    flattened_docs = [dumps(doc) for sublist in documents for doc in sublist]
    # 获取独特的文档
    unique_docs = list(set(flattened_docs))
    # 返回
    return [loads(doc) for doc in unique_docs]


question = "根据这篇百度百科链接，你能介绍一下人工智能目前在中国的现状吗？"
retrieval_chain = generate_queries | retriever.map() | get_unique_union
docs = retrieval_chain.invoke({"question": question})

template = """根据此上下文回答以下问题：

{context}

问题：{question}
"""

prompt = ChatPromptTemplate.from_template(template)
from operator import itemgetter

final_rag_chain = (
        {"context": retrieval_chain,
         "question": itemgetter("question")}
        | prompt
        | llm
        | StrOutputParser()
)

resp = final_rag_chain.invoke({"question": question})
print(resp)
