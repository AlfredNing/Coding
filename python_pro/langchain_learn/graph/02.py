from langchain_openai import ChatOpenAI
from dotenv import load_dotenv

load_dotenv()
llm = ChatOpenAI(model='qwen-max')


def multiply(a: int, b: int) -> int:
    """
    Multiply two number
    :param a:  the first number
    :param b: the second number
    :return: thr result
    """


llm_with_tools = llm.bind_tools([multiply])
from langchain_core.messages import HumanMessage

message = [
    HumanMessage(content='2 乘以3 等于多少', name='Ronnie')
]
resp = llm_with_tools.invoke(message)
print(resp.tool_calls)

from typing import TypedDict, Annotated
from langchain_core.messages import AnyMessage
from langgraph.graph.message import add_messages


# state
class MessageState(TypedDict):
    messages: Annotated[list[AnyMessage], add_messages]


# node
def tool_with_llm(state: MessageState):
    return {"messages": [llm_with_tools.invoke(state["messages"])]}


# graph
from langgraph.graph import StateGraph, START, END

build = StateGraph(MessageState)
build.add_node("tool_with_llm", tool_with_llm)
build.add_edge(START, "tool_with_llm")
build.add_edge("tool_with_llm", END)

graph = build.compile()

message = graph.invoke({"messages": HumanMessage(content="2 乘以 5等于多少")})
for i in message["messages"]:
    print(i)
