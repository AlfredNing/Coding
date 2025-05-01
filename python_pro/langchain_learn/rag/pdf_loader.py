from dotenv import load_dotenv
from langchain_openai import ChatOpenAI
from langchain_community.document_loaders import PyPDFLoader

load_dotenv()

# load_document
loder = PyPDFLoader("../关于美团vs.京东竞争的一点思考-国证国际-202504.pdf")
pages = loder.load_and_split()

# print(f"page[0]: {pages[0].page_content}")

# split document

from langchain.text_splitter import RecursiveCharacterTextSplitter

text_splitter = RecursiveCharacterTextSplitter(
    chunk_size=200,
    chunk_overlap=100,
    length_function=len,
    add_start_index=True
)

paragraphs = []
for paragraph in pages[:1]:
    paragraphs.extend(text_splitter.create_documents([paragraph.page_content]))
    # print(paragraph.page_content)

print('========\n embedding \n==============\n')
# embedding
import chromadb
from langchain_community.vectorstores import Chroma
from langchain_community.embeddings import DashScopeEmbeddings

embeddings = DashScopeEmbeddings(model='text-embedding-v1')
# doc_result = embeddings.embed_documents(paragraphs[0].page_content)
print(len(paragraphs))
print(paragraphs[0].page_content)
new_client = chromadb.EphemeralClient()
vectordb = Chroma.from_documents(
    paragraphs, embeddings, client=new_client, collection_name="collection.pkl"
)
query = '京东竞争'
# docs = vectordb.similarity_search(query)
# for doc in docs:
#     print(f"{doc.page_content} \n ---- \n")

# retriv 检索器
# top k
retriever = vectordb.as_retriever(search_kwagrs={"k": 1})
docs = retriever.invoke(query)
for doc in docs:
    print(doc.page_content)
