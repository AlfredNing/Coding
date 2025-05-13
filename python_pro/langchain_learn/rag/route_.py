from dotenv import load_dotenv

load_dotenv()
from langchain_openai import ChatOpenAI
from langchain_core.pydantic_v1 import BaseModel, Field
from langchain_core.prompts import ChatPromptTemplate
from typing import Literal


class RouteQuery(BaseModel):
    """将用户路由到最相关的数据源"""
    datasource: Literal["python_docs", "js_docs", "golang_docs"] = Field(
        ...,
        description="给出一个用户问题，选择哪个数据源与回答他们的问题最相关",
    )


llm = ChatOpenAI(model='qwen-max')
structured_llm = llm.with_structured_output(RouteQuery)
# prompt
system = """您是将用户问题路由到适当数据源的专家。

根据问题所指的编程语言，将其路由到相关数据源。"""

prompt = ChatPromptTemplate.from_messages(
    [
        ("system", system),
        ("human", "{question}"),
    ]
)

router = prompt | structured_llm
question = """为什么下面的代码不起作用:

import random
import string

def generate_password(length):
    characters = string.ascii_letters + string.digits + string.punctuation
    password = ''.join(random.choice(characters) for i in range(length))
    return password

password = generate_password(12)
print("Generated password:", password)
"""


# resp = router.invoke({"question": question})
# print(resp.datasource)

def choose_route(result):
    if "python_docs" in result.datasource.lower():
        ### 逻辑在这里
        return "chain for python_docs"
    elif "js_docs" in result.datasource.lower():
        ### 逻辑在这里
        return "chain for js_docs"
    else:
        ### 逻辑在这里
        return "golang_docs"


from langchain_core.runnables import RunnableLambda

full_chain = router | RunnableLambda(choose_route)

# resp2 = full_chain.invoke({"question": question})
# print(resp2)

from langchain.utils.math import cosine_similarity
from langchain_core.output_parsers import StrOutputParser
from langchain_core.prompts import PromptTemplate
from langchain_core.runnables import RunnableLambda, RunnablePassthrough
from langchain_openai import ChatOpenAI, OpenAIEmbeddings

# 两个 prompts
physics_template = """你是一位非常聪明的物理学教授。\
你擅长以简洁易懂的方式回答有关物理的问题。\
当你不知道问题的答案时，你会承认你不知道。

这里有一个问题：
{query}"""

math_template = """你是一位非常优秀的数学家。你非常擅长回答数学问题。\
你之所以如此优秀，是因为你能将难题分解成各个组成部分，\
回答各个组成部分的问题，然后将它们组合起来回答更广泛的问题。

这里有一个问题：
{query}"""
from langchain_community.embeddings import DashScopeEmbeddings

# 嵌入
embeddings = DashScopeEmbeddings(model='text-embedding-v1')
prompt_templates = [physics_template, math_template]
prompt_embeddings = embeddings.embed_documents(prompt_templates)


# 将问题转至提示
def prompt_router(input):
    # 嵌入问题
    query_embedding = embeddings.embed_query(input["query"])
    # 计算相似度
    similarity = cosine_similarity([query_embedding], prompt_embeddings)[0]
    most_similar = prompt_templates[similarity.argmax()]
    # 选择的提示
    print("呼叫数学专家" if most_similar == math_template else "呼叫物理专家")
    return PromptTemplate.from_template(most_similar)


chain = (
        {"query": RunnablePassthrough()}
        | RunnableLambda(prompt_router)
        | llm
        | StrOutputParser()
)

print(chain.invoke("什么是黑洞？"))
