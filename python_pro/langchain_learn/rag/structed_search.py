"""结构化搜索"""
from langchain_chroma import Chroma
from langchain_core.documents import Document
from langchain_openai import OpenAIEmbeddings
from dotenv import load_dotenv

load_dotenv()
from langchain_openai import ChatOpenAI

llm = ChatOpenAI(model='qwen-max')
docs = [
    Document(
        page_content="一群科学家带回恐龙，混乱随之而来",
        metadata={"year": 1993, "rating": 7.7, "genre": "science fiction"},
    ),
    Document(
        page_content="莱昂纳多·迪卡普里奥迷失在梦中梦中梦中梦中...",
        metadata={"year": 2010, "director": "Christopher Nolan", "rating": 8.2},
    ),
    Document(
        page_content="一个心理学家/侦探迷失在梦中梦中梦中，盗梦空间重用了这个想法",
        metadata={"year": 2006, "director": "Satoshi Kon", "rating": 8.6},
    ),
    Document(
        page_content="一群正常身材的女性极其健康，一些男性对她们心生向往",
        metadata={"year": 2019, "director": "Greta Gerwig", "rating": 8.3},
    ),
    Document(
        page_content="玩具活了过来，并乐在其中",
        metadata={"year": 1995, "genre": "animated"},
    ),
    Document(
        page_content="三个人走入区域，三个人走出区域",
        metadata={
            "year": 1979,
            "director": "Andrei Tarkovsky",
            "genre": "thriller",
            "rating": 9.9,
        },
    ),
]
from langchain_community.embeddings import DashScopeEmbeddings

embeddings = DashScopeEmbeddings(model='text-embedding-v1')
vectorstore = Chroma.from_documents(docs, embeddings)

from langchain.chains.query_constructor.base import AttributeInfo
from langchain.retrievers.self_query.base import SelfQueryRetriever
from langchain_openai import ChatOpenAI

metadata_field_info = [
    AttributeInfo(
        name="genre",
        description="电影的类型。类型之一 ['science fiction', 'comedy', 'drama', 'thriller', 'romance', 'action', 'animated']",
        type="string",
    ),
    AttributeInfo(
        name="year",
        description="电影上映的年份",
        type="integer",
    ),
    AttributeInfo(
        name="director",
        description="电影导演的名字",
        type="string",
    ),
    AttributeInfo(
        name="rating", description="电影的1-10评分", type="float"
    ),
]
document_content_description = "电影的简要总结"

retriever = SelfQueryRetriever.from_llm(
    llm,
    vectorstore,
    document_content_description,
    metadata_field_info,
)

print(retriever.invoke("我想看一部评分高于 8.5 的电影"))
print(retriever.invoke("Greta Gerwig导演过任何关于女性的电影吗"))
print(retriever.invoke(
    "1990 年之后、2005 年之前的电影都与玩具有关，最好是动画片"
))
