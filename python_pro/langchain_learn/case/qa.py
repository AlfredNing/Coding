from langchain_openai import ChatOpenAI
from dotenv import load_dotenv
from langchain_community.vectorstores import FAISS
from langchain import hub
from langchain.chains.combine_documents import create_stuff_documents_chain
from langchain.chains.retrieval import create_retrieval_chain

from langchain.embeddings.openai import OpenAIEmbeddings
from langchain.document_loaders import TextLoader

load_dotenv()

llm = ChatOpenAI(model_name='qwen-max')
from langchain_community.embeddings import DashScopeEmbeddings

embedding = DashScopeEmbeddings(model='text-embedding-v1')

loader = TextLoader('../data/long_summary.txt', encoding='utf-8')
doc = loader.load()

from langchain_text_splitters import RecursiveCharacterTextSplitter

splitter = RecursiveCharacterTextSplitter(
    chunk_size=1000,
    chunk_overlap=200
)
docs = splitter.split_documents(doc)
docSearch = FAISS.from_documents(docs, embedding)

retrieval_qa_chat_prompt = hub.pull("langchain-ai/retrieval-qa-chat")
retriever = docSearch.as_retriever()
combine_docs_chain = create_stuff_documents_chain(
    llm, retrieval_qa_chat_prompt
)
retrieval_chain = create_retrieval_chain(retriever, combine_docs_chain)
context = """
小明30岁
小王40岁
小文65岁

谁40岁一下?
"""
resp = retrieval_chain.invoke({"input": context})
print(resp)
