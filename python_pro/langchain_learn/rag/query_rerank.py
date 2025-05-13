from langchain.prompts import ChatPromptTemplate

template = """你是一名 AI 语言模型助手。你的任务是生成给定用户问题的五个不同版本，
以从向量数据库中检索相关文档。通过生成用户问题的多个视角，你
的目标是帮助用户克服基于距离的相似性搜索的一些限制。
提供这些以换行符分隔的备选问题。原始问题：{question}"""
prompt_rag_fusion = ChatPromptTemplate.from_template(template)
# print(prompt_rag_fusion)
from langchain_core.output_parsers import StrOutputParser
from langchain_openai import ChatOpenAI
from langchain_openai import ChatOpenAI
from dotenv import load_dotenv

load_dotenv()
llm = ChatOpenAI(model='qwen-max')

generate_queries = (
        prompt_rag_fusion
        | llm
        | StrOutputParser()
        | (lambda x: x.split("\n")))

from langchain.load import dumps, loads

question = "根据这篇百度百科链接，你能介绍一下人工智能目前在中国的现状吗？"


def reciprocal_rank_fusion(results: list[list], k=60):
    """ Reciprocal_rank_fusion 采用多个排名文档列表
         以及 RRF 公式中使用的可选参数 k """

    # 初始化一个字典来保存每个唯一文档的融合分数
    fused_scores = {}

    # 迭代每个排名文档列表
    for docs in results:
        # 迭代列表中的每个文档及其排名（列表中的位置）
        for rank, doc in enumerate(docs):
            # 将文档转换为字符串格式以用作键（假设文档可以序列化为 JSON）
            doc_str = dumps(doc)
            # 如果文档尚未在 fused_scores 字典中，则将其添加，初始分数为 0
            if doc_str not in fused_scores:
                fused_scores[doc_str] = 0
            # 检索文档的当前分数（如果有）
            previous_score = fused_scores[doc_str]
            # 使用 RRF 公式更新文档的分数：1 / (rank + k)
            fused_scores[doc_str] += 1 / (rank + k)

    # 根据融合分数对文档进行降序排序，得到最终的重新排序结果
    reranked_results = [
        (loads(doc), score)
        for doc, score in sorted(fused_scores.items(), key=lambda x: x[1], reverse=True)
    ]

    # 将重新排序的结果作为元组列表返回，每个元组包含文档及其融合分数
    return reranked_results


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

retrieval_chain_rag_fusion = generate_queries | retriever.map() | reciprocal_rank_fusion
docs = retrieval_chain_rag_fusion.invoke({"question": question})
print(docs)

