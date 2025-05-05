from langchain_openai import ChatOpenAI
from dotenv import load_dotenv

load_dotenv()
llm = ChatOpenAI(model="qwen-max")


def multiply(a: int, b: int) -> int:
    """
    Multiply two number
    :param a:  the first number
    :param b:  the second number
    :return: the result
    """
    return a * b


llm_with_tools = llm.bind_tools([multiply])

from typing import TypedDict, Annotated
from langchain_core.messages import AnyMessage, HumanMessage
from langgraph.graph.message import add_messages


# status
class MessageStatue(TypedDict):
    messages: Annotated[list[AnyMessage], add_messages]


def tool_calling_llm(state: AnyMessage) -> MessageStatue:
    return {"messages": [llm_with_tools.invoke(state["messages"])]}


# graph
from langgraph.graph import StateGraph, START, END
from langgraph.prebuilt import ToolNode, tools_condition

build = StateGraph(MessageStatue)
build.add_node("tool_calling_llm", tool_calling_llm)
build.add_node("tools", ToolNode([multiply]))

build.add_edge(START, "tool_calling_llm")
build.add_conditional_edges("tool_calling_llm", tools_condition)
build.add_edge("tools", END)

graph = build.compile()

message = graph.invoke({"messages": [HumanMessage(content="2乘以5等于多少", name="firstss")]})
for i in message['messages']:
    i.pretty_print()
