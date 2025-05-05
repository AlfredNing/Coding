"""
消息裁剪
"""
from dotenv import load_dotenv

load_dotenv()

from langchain_openai import ChatOpenAI

llm = ChatOpenAI(model='qwen-max')
from langgraph.graph import MessagesState


def chat_model_node(state: MessagesState):
    return {"messages": llm.invoke(state["messages"])}


from langgraph.graph import StateGraph, START, END

builder = StateGraph(MessagesState)
builder.add_node("chat_model", chat_model_node)
builder.add_edge(START, "chat_model")
builder.add_edge("chat_model", END)

graph = builder.compile()
from langchain_core.messages import HumanMessage, AIMessage

messages = [
    AIMessage(f"所以你说你在研究神经网络", name="Ronnie"),
    HumanMessage(f"是的，我知道神经网络，但我应该还了解其他哪些东西", name="Simon")
]
resp = graph.invoke({"messages": messages})
# for m in resp["messages"]:
# m.pretty_print()

from langchain_core.messages import RemoveMessage


def filter_messages(state: MessagesState):
    delete_messages = [RemoveMessage(id=m.id) for m in state["messages"][:-2]]
    return {"messages": delete_messages}


builder = StateGraph(MessagesState)
builder.add_node("chat_model", chat_model_node)
builder.add_node("filter", filter_messages)
builder.add_edge(START, "filter")
builder.add_edge("filter", "chat_model")
builder.add_edge("chat_model", END)

graph = builder.compile()
resp = graph.invoke({"messages": messages})
for m in resp["messages"]:
    m.pretty_print()
