"""问题分解"""
from langchain.prompts import ChatPromptTemplate
from langchain_openai import ChatOpenAI
from dotenv import load_dotenv

load_dotenv()
llm = ChatOpenAI(model='qwen-max')

# 分解
template = """您是一位乐于助人的助手，可以生成与输入问题相关的多个子问题。\n
目标是将输入分解为一组可以单独回答的子问题/子问题。\n
生成与以下问题相关的多个搜索查询：{question} \n
输出（5 个查询）："""
prompt_decomposition = ChatPromptTemplate.from_template(template)

from langchain_openai import ChatOpenAI
from langchain_core.output_parsers import StrOutputParser

# Chain
generate_queries_decomposition = (prompt_decomposition | llm | StrOutputParser() | (lambda x: x.split("\n")))

question = "根据文章，中国的人工智能是怎么一步步发展到今天的？"
questions = generate_queries_decomposition.invoke({"question": question})
print(questions)

"""整合结果"""
template = """这是您需要回答的问题：

\n --- \n {question} \n --- \n

以下是任何可用的背景问题 + 答案组合：

\n --- \n {q_a_pairs} \n --- \n

以下是与问题相关的其他上下文：

\n --- \n {context} \n --- \n

使用上述上下文和任何背景问题 + 答案组合来回答问题：\n {question}
"""

decomposition_prompt = ChatPromptTemplate.from_template(template)

from operator import itemgetter
from langchain_core.output_parsers import StrOutputParser

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
from langchain_community.embeddings import DashScopeEmbeddings
import os

DASHSCOPE_API_KEY = os.getenv("DASHSCOPE_API_KEY")
embeddings = DashScopeEmbeddings(
    model="text-embedding-v1", dashscope_api_key=DASHSCOPE_API_KEY
)
vectorstore = Chroma.from_documents(documents=splits,
                                    embedding=embeddings)

retriever = vectorstore.as_retriever(search_kwargs={"k": 5})


def format_qa_pair(question, answer):
    """格式化 Q 和 A 对"""

    formatted_string = ""
    formatted_string += f"Question: {question}\nAnswer: {answer}\n\n"
    return formatted_string.strip()


q_a_pairs = ""
for q in questions:
    rag_chain = (
            {"context": itemgetter("question") | retriever,
             "question": itemgetter("question"),
             "q_a_pairs": itemgetter("q_a_pairs")}
            | decomposition_prompt
            | llm
            | StrOutputParser())

    answer = rag_chain.invoke({"question": q, "q_a_pairs": q_a_pairs})
    q_a_pair = format_qa_pair(q, answer)
    q_a_pairs = q_a_pairs + "\n---\n" + q_a_pair
