# 定义状态
from typing_extensions import TypedDict


# 定义状态
class State(TypedDict):
    graph_state: str


# 定义节点
def node_1(state):
    print('----node1----')
    return {'graph_state': state['graph_state'] + '我很'}


def node_2(state):
    print('----node2----')
    return {'graph_state': state['graph_state'] + '高兴'}


def node_3(state):
    print('----node2----')
    return {'graph_state': state['graph_state'] + '悲伤'}


# 定义边
import random
from typing import Literal


# 定义边
def decide_method(state) -> Literal['node_2', 'node_3']:
    user_input = state['graph_state']
    if random.random() < 0.5:
        return 'node_2'
    return 'node_3'


# 构建图
from langgraph.graph import StateGraph, START, END

builder = StateGraph(State)
builder.add_node("node_1", node_1)
builder.add_node("node_2", node_2)
builder.add_node("node_3", node_3)

## 图上添加边
builder.add_edge(START, "node_1")
### 条件边
builder.add_conditional_edges("node_1", decide_method)
builder.add_edge("node_2", END)
builder.add_edge("node_3", END)

graph = builder.compile()

from IPython.display import Image, display

# display(Image(graph.get_graph().draw_mermaid_png()))

resp = graph.invoke({"graph_state": "你好"})
print(resp['graph_state'])
