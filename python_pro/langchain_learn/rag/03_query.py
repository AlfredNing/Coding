#### 索引 ####
from langchain_nomic.embeddings import NomicEmbeddings
from langchain_community.embeddings import DashScopeEmbeddings
# 加载文档
import bs4
from dotenv import load_dotenv

load_dotenv()
from langchain_community.document_loaders import WebBaseLoader

loader = WebBaseLoader(
    web_paths=("https://baike.baidu.com/item/%E4%BA%BA%E5%B7%A5%E6%99%BA%E8%83%BD/9180?fr=ge_ala#9-10",)
)
blog_docs = loader.load()

# 拆分
from langchain.text_splitter import RecursiveCharacterTextSplitter

text_splitter = RecursiveCharacterTextSplitter.from_tiktoken_encoder(
    chunk_size=300,
    chunk_overlap=50)

splits = text_splitter.split_documents(blog_docs)

# 索引
embeddings = DashScopeEmbeddings(
    model="text-embedding-v1"
)
from langchain_community.vectorstores import Chroma

vectorstore = Chroma.from_documents(documents=splits,
                                    embedding=embeddings)

retriever = vectorstore.as_retriever(search_kwargs={"k": 5})
