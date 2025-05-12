question = "什么是人工智能？"
document = "人工智能（Artificial Intelligence），英文缩写为AI，是新一轮科技革命和产业变革的重要驱动力量"

from dotenv import load_dotenv
import numpy as np

load_dotenv()
from langchain_community.embeddings import DashScopeEmbeddings

embed = DashScopeEmbeddings(model='text-embedding-v1')
query_result = embed.embed_query(question)
document_result = embed.embed_query(document)


# print(query_result)
# print(document_result)

def cosine_similarity(vec1, vec2):
    dot_product = np.dot(vec1, vec2)
    norm_vec1 = np.linalg.norm(vec1)
    norm_vec2 = np.linalg.norm(vec2)
    return dot_product / (norm_vec1 * norm_vec2)


similarity = cosine_similarity(query_result, document_result)
print(similarity)

import bs4
from langchain_community.document_loaders import WebBaseLoader

loader = WebBaseLoader(
    web_paths=("https://baike.baidu.com/item/%E4%BA%BA%E5%B7%A5%E6%99%BA%E8%83%BD/9180?fr=ge_ala",)
)
blog_docs = loader.load()

# print(blog_docs)

from langchain_text_splitters import RecursiveCharacterTextSplitter

text_splitter = RecursiveCharacterTextSplitter.from_tiktoken_encoder(
    chunk_size=300,
    chunk_overlap=50
)

splits = text_splitter.split_documents(blog_docs)
