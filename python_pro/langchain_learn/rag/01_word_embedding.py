"""词嵌入"""
from dotenv import load_dotenv

load_dotenv()
from langchain_community.document_loaders import WebBaseLoader
from langchain_community.embeddings import DashScopeEmbeddings
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain_openai import ChatOpenAI
from langchain_chroma import Chroma
from langchain_core.runnables import RunnablePassthrough
from langchain_core.output_parsers import StrOutputParser
from langchain import hub

# 加载文件
loader = WebBaseLoader(
    web_paths=("https://baike.baidu.com/item/%E4%BA%BA%E5%B7%A5%E6%99%BA%E8%83%BD/9180?fr=ge_ala",
               )
)
docs = loader.load()
print(docs)
# # 切分
text_splitter = RecursiveCharacterTextSplitter(chunk_size=500, chunk_overlap=100)
splits = text_splitter.split_documents(docs)
# # 嵌入
# embeddings = DashScopeEmbeddings(
#     model="text-embedding-v2",
#     # other params...
# )
#
# # 嵌入
# vector_store = Chroma(
#     collection_name="example_collection",
#     embedding_function=embeddings,
#     persist_directory="F:/program/repo/Coding/python_pro/langchain_learn/rag/chroma_langchain_db"
# )
# vector = vector_store.from_documents(documents=splits, embedding=embeddings)
#
# retriever = vector.as_retriever(search_kwargs={"k": 5})
# print(retriever)`
# #### 检索与生成 ####
# prompt = hub.pull("rlm/rag-prompt")
#
# llm = ChatOpenAI(model='qwen-max')
#
#
# def format_doc(docs):
#     return "\n\n".join(doc.page_content for doc in docs)
#
#
# rag_chain = (
#         {"context": retriever | format_doc, "question": RunnablePassthrough()}
#         | prompt
#         | llm
#         | StrOutputParser
# )
# resp = rag_chain.invoke("能否简答介绍下什么是人工智能?")
# print(resp)
