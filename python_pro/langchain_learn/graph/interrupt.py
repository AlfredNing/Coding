from langchain_openai import ChatOpenAI
from dotenv import load_dotenv

load_dotenv()


def multiply(a: int, b: int) -> int:
    """
    负责两个数相乘
    :param a: 第一个数字
    :param b: 第二个数字
    :return: 结果
    """
    return a * b


def plus(a: int, b: int) -> int:
    """
    负责两个数相加
    :param a: 第一个数字
    :param b: 第二个数字
    :return: 结果
    """
    return a + b


def divide(a: int, b: int) -> float:
    """
    负责两个数除
    :param a: 第一个数字
    :param b: 第二个数字
    :return: 结果
    """
    return a / b


tools = [multiply, plus, divide]

llm = ChatOpenAI(model='qwen-max')

llm_with_tools = llm.bind_tools(tools, parallel_tool_calls=False)
from langchain_core.messages import HumanMessage, SystemMessage, AnyMessage
from langgraph.graph import add_messages

sys_msg = SystemMessage(content='你是一位乐于助人的助手，负责对一组输入执行算术运算')
from typing import TypedDict, Annotated


class MessageState(TypedDict):
    messages: Annotated[list[AnyMessage], add_messages]


def assistant(state: MessageState):
    return {"messages": [llm_with_tools.invoke([sys_msg] + state["messages"])]}


from langgraph.graph import StateGraph, START, END
from langgraph.prebuilt import ToolNode, tools_condition

builder = StateGraph(MessageState)
builder.add_node("assistant", assistant)
builder.add_node("tools", ToolNode(tools))

builder.add_edge(START, "assistant")
builder.add_conditional_edges("assistant", tools_condition)
builder.add_edge("tools", "assistant")
builder.add_edge("assistant", END)

# builder.add_edge("assistant", "tools")
from langgraph.checkpoint.memory import MemorySaver

memory = MemorySaver()
graph = builder.compile(interrupt_before=['tools'], checkpointer=memory)
initial_input = {"messages": [HumanMessage(content="将 3 和 4相加等于多少")]}

thread = {"configurable": {"thread_id": "1"}}
for event in graph.stream(initial_input, thread, stream_mode="values"):
    event['messages'][-1].pretty_print()
user_approval = input("您想调用工具？ 是/否：")
if user_approval.lower() == '是':
    for event in graph.stream(None, thread, stream_mode="values"):
        event['messages'][-1].pretty_print()
else:
    print("操作已经被用户取消")
