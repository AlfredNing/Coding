"""外部修改图状态"""

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

from langgraph.graph import MessagesState
from langchain_core.messages import SystemMessage, HumanMessage, AIMessage

sys_message = SystemMessage(content='你是一个负责对一组输入执行算术运行的助手')


def human_feedback(msg: MessagesState):
    pass


def assistant(state: MessagesState):
    return {"messages": [llm_with_tools.invoke([sys_message] + state['messages'])]}


from langgraph.graph import StateGraph, START, END
from langgraph.prebuilt import ToolNode, tools_condition

builder = StateGraph(MessagesState)
builder.add_node("assistant", assistant)
builder.add_node("tools", ToolNode(tools))
builder.add_node("human_feedback", human_feedback)

builder.add_edge(START, "human_feedback")
builder.add_edge("human_feedback", "assistant")
builder.add_conditional_edges("assistant", tools_condition)
builder.add_edge("tools", 'human_feedback')
from langgraph.checkpoint.memory import MemorySaver

memory = MemorySaver()
graph = builder.compile(interrupt_before=["human_feedback"], checkpointer=memory)

initial_input = {"message": "2和3相乘等于多少"}
thread = {"configurable": {"thread_id": "1"}}
for event in graph.stream(initial_input, thread, stream_mode="values"):
    event['messages'][-1].pretty_print()

user_input = input("告诉我您想如何更新状态")

graph.update_state(thread, {'messages': user_input}, as_node="human_feedback")
for event in graph.stream(None, thread, stream_mode="values"):
    event['messages'][-1].pretty_print()
