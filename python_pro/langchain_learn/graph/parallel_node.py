"""
并行节点执行
"""
from dotenv import load_dotenv

load_dotenv()
from langchain_openai import ChatOpenAI

llm = ChatOpenAI(
    model='qwen-max'
)

from typing import TypedDict


class State(TypedDict):
    state: str


class ReturnNodeValue:
    def __init__(self, node_secret: str):
        self._value = node_secret

    def __call__(self, state: State):
        print(f"增加{self._value} to {state['state']}")
        return {"state": self._value}


from langgraph.graph import StateGraph, START, END

# builder = StateGraph(State)
#
# builder.add_node("a", ReturnNodeValue("我是A"))
# builder.add_node("b", ReturnNodeValue("我是B"))
# builder.add_node("c", ReturnNodeValue("我是C"))
# builder.add_node("d", ReturnNodeValue("我是D"))
#
# builder.add_edge(START, "a")
# builder.add_edge("a", "b")
# builder.add_edge("b", "c")
# builder.add_edge("c", "d")
# builder.add_edge("d", END)
#
# graph = builder.compile()

# invoke
# resp = graph.invoke({"state": []})
# print(resp)


# 二次修改图 下面会报错
#
# builder.add_edge(START, "a")
# builder.add_edge("a", "b")
# builder.add_edge("a", "c")
# builder.add_edge("b", "d")
# builder.add_edge("c", "d")
# builder.add_edge("d", END)
#
# graph = builder.compile()
# from langgraph.errors import InvalidUpdateError
#
# try:
#     resp = graph.invoke({"state": []})
#     # 发生错误 ：错误: At key 'state': Can receive only one value per step. Use an Annotated key to handle multiple values.
# except InvalidUpdateError as e:
#     print(f"错误: {e}")

# builder.add_edge(START, "a")
# builder.add_edge("a", "b")
# builder.add_edge("a", "c")
# builder.add_edge("b", "d")
# builder.add_edge("c", "d")
# builder.add_edge("d", END)

# graph = builder.compile()
from langgraph.errors import InvalidUpdateError

# 这时候修改State
import operator
from typing import Annotated


class State2(TypedDict):
    state: Annotated[list, operator.add]


class ReturnNodeValue2:
    def __init__(self, node_secret: str):
        self._value = node_secret

    def __call__(self, state: State2):
        print(f"增加{self._value} to {state['state']}")
        return {"state": [self._value]}


# builder = StateGraph(State2)
#
# builder.add_node("a", ReturnNodeValue2("我是A"))
# builder.add_node("b", ReturnNodeValue2("我是B"))
# builder.add_node("c", ReturnNodeValue2("我是C"))
# builder.add_node("d", ReturnNodeValue2("我是D"))
#
# builder.add_edge(START, "a")
# builder.add_edge("a", "b")
# builder.add_edge("a", "c")
# builder.add_edge("b", "d")
# builder.add_edge("c", "d")
# builder.add_edge("d", END)
# graph = builder.compile()
# resp = graph.invoke({"state": []})
# print(resp) 成功运行
#
# builder = StateGraph(State2)
#
# builder.add_node("a", ReturnNodeValue2("我是A"))
# builder.add_node("b", ReturnNodeValue2("我是B"))
# builder.add_node("b2", ReturnNodeValue2("我是B2"))
# builder.add_node("c", ReturnNodeValue2("我是C"))
# builder.add_node("d", ReturnNodeValue2("我是D"))
#
# builder.add_edge(START, "a")
# builder.add_edge("a", "b")
# builder.add_edge("a", "c")
# builder.add_edge("b", "b2")
# builder.add_edge(["b2", "c"], "d")
# builder.add_edge("d", END)
# graph = builder.compile()
# resp = graph.invoke({"state": []})
# print(resp)  # {'state': ['我是A', '我是B', '我是C', '我是B2', '我是D']}

# 控制左右边执行逻辑
def sort_reducer(left, right):
    if not isinstance(left, list):
        left = [left]
    if not isinstance(right, list):
        right = [right]
    return sorted(left + right, reverse=False)


class State3(TypedDict):
    state: Annotated[list, sort_reducer]


class ReturnNodeValue3:
    def __init__(self, node_secret: str):
        self._value = node_secret

    def __call__(self, state: State3):
        print(f"增加{self._value} to {state['state']}")
        return {"state": [self._value]}


builder = StateGraph(State3)

builder.add_node("a", ReturnNodeValue3("我是A"))
builder.add_node("b", ReturnNodeValue3("我是B"))
builder.add_node("b2", ReturnNodeValue3("我是B2"))
builder.add_node("c", ReturnNodeValue3("我是C"))
builder.add_node("d", ReturnNodeValue3("我是D"))

builder.add_edge(START, "a")
builder.add_edge("a", "b")
builder.add_edge("a", "c")
builder.add_edge("b", "b2")
builder.add_edge(["b2", "c"], "d")
builder.add_edge("d", END)
graph = builder.compile()
resp = graph.invoke({"state": []})
print(resp)  # {'state': ['我是A', '我是B', '我是B2', '我是C', '我是D']}


