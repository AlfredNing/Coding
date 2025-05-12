import chromadb
from langchain_chroma import Chroma
from langchain_community.embeddings import DashScopeEmbeddings
from dotenv import load_dotenv

load_dotenv()
embeddings = DashScopeEmbeddings(
    model="text-embedding-v1"
)
persistent_client = chromadb.PersistentClient()
collection = persistent_client.get_or_create_collection("collection_name")

vector_store = Chroma(
    client=persistent_client,
    collection_name="collection_name",
    embedding_function=embeddings,
)

