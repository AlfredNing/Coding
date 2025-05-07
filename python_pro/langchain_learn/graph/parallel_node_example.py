from dotenv import load_dotenv

load_dotenv()
from langchain_openai import ChatOpenAI

llm = ChatOpenAI(model='qwen-max')
from typing import TypedDict, Annotated
import operator


class State(TypedDict):
    question: str
    answer: str
    context: Annotated[list, operator.add]


from langchain_community.tools import TavilySearchResults


def search_web(state):
    tavily_search = TavilySearchResults(max_results=3)
    search_docs = tavily_search.invoke(state['question'])

    formatted_search_docs = "\n\n---\n\n".join([
        f'<Document href="{doc["url"]}" > \n{doc["content"]}< / Document > '
        for doc in search_docs
    ])
    return {"context": [formatted_search_docs]}


from langchain_community.document_loaders import WikipediaLoader


def search_wikipedia(state):
    search_docs = WikipediaLoader(query=state["question"],
                                  load_max_docs=2).load()

    formatted_search_docs = "\n\n---\n\n".join([
        f'<Document source="{doc.metadata["source"]}" page="{doc.metadata.get("page", "")}"> \n {doc.page_content} </Document>'
        for doc in search_docs
    ])
    return {"context": [formatted_search_docs]}


from langchain_core.messages import SystemMessage, HumanMessage


def generate_answer(state):
    context = state['context']
    question = state['question']
    answer_template = f"""使用一下上下文回答问题 {question}: {context}"""
    answer_instructions = answer_template.format(question=question, context=context)

    answer = llm.invoke([SystemMessage(content=answer_instructions)] + [HumanMessage(content=f"回答这个问题")])
    return {"answer": answer}


from langgraph.graph import StateGraph, START, END

builder = StateGraph(State)
builder.add_node("search_web", search_web)
builder.add_node("search_wikipedia", search_wikipedia)
builder.add_node("generate_answer", generate_answer)

builder.add_edge(START, "search_web")
builder.add_edge(START, "search_wikipedia")
builder.add_edge("search_web", "generate_answer")
builder.add_edge("search_wikipedia", "generate_answer")
builder.add_edge("generate_answer", END)

graph = builder.compile()
result = graph.invoke({"question": "中国2020的人口总数"})
print(result['answer'].content)
