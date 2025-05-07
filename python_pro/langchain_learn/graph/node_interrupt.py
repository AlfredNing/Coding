from langchain_openai import ChatOpenAI
from dotenv import load_dotenv

load_dotenv()
llm = ChatOpenAI(model='qwen-max')

from typing import TypedDict


class State(TypedDict):
    input: str


def step_1(state: State):
    print('---step-1---')
    return state


from langgraph.errors import NodeInterrupt


def step_2(state: State):
    if len(state['input']) > 5:
        raise NodeInterrupt(f"收到的字符超过五个字符: {state['input']}")
    print('---step-2---')
    return state


def step_3(state: State):
    print('---step-3---')
    return state


from langgraph.graph import StateGraph, START, END

builder = StateGraph(State)
builder.add_node("step_1", step_1)
builder.add_node("step_2", step_2)
builder.add_node("step_3", step_3)

builder.add_edge(START, "step_1")
builder.add_edge("step_1", "step_2")
builder.add_edge("step_2", "step_3")
builder.add_edge("step_3", END)

from langgraph.checkpoint.memory import MemorySaver

memory = MemorySaver()

graph = builder.compile(checkpointer=memory)

initial_input = {"input": "你好今天天气怎么样"}
thread = {"configurable": {"thread_id": "1"}}
for event in graph.stream(initial_input, thread, stream_mode="values"):
    print(event)
state = graph.get_state(thread)
print(state.next)
print(state.tasks)
# for event in graph.stream(None, thread, stream_mode="values"):
#     print(event)

graph.update_state(thread, {"input": "你好"}, )
for event in graph.stream(None, thread, stream_mode="values"):
    print(event)
