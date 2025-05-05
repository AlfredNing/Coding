"""
流式输出
"""
from langchain_openai import ChatOpenAI
from dotenv import load_dotenv

load_dotenv()
llm = ChatOpenAI(model="qwen-max")

from langgraph.graph import MessagesState


class State(MessagesState):
    summary: str


from langchain_core.runnables import Runnable
from langchain_core.messages import HumanMessage, AIMessage, AnyMessage, SystemMessage, RemoveMessage


def call_model(state: State, config: Runnable):
    summary = state.get('summary', "")
    if summary:
        # 添加
        sys_message = f"Summary of conversation: {summary}"
        messages = [SystemMessage(content=sys_message)] + state["messages"]

    else:
        messages = state["messages"]
    responses = llm.invoke(messages, config)
    return {"messages": responses}


def summarize_conversation(state: State):
    summary = state.get('summary', "")
    if summary:
        summary_message = (
            f"this is summary of the conversation to date:{summary}\n\n"
            "Extend the summary by taking into account the new message above"
        )
    else:
        summary_message = "Create summay of the conversation"
    messages = state["messages"] + [HumanMessage(content=summary_message)]
    responses = llm.invoke(messages)
    delete_message = [RemoveMessage(id=m.id) for m in state["messages"][:-2]]
    return {"summary": responses.content, "messages": delete_message}


from langgraph.graph import StateGraph, START, END


def should_continue(state: State):
    message = state["messages"]
    if (len(message) > 6):
        return "summarize_conversation"
    return END


workflow = StateGraph(State)
workflow.add_node("conversation", call_model)
workflow.add_node(summarize_conversation)
workflow.add_edge(START, "conversation")
workflow.add_conditional_edges("conversation", should_continue)
workflow.add_edge("summarize_conversation", END)

from langgraph.checkpoint.memory import MemorySaver

memory = MemorySaver()
graph = workflow.compile(checkpointer=memory)
config = {"configurable": {"thread_id": 1}}
# for chunk in gr aph.stream({"messages": [HumanMessage(content="你好 我是罗尼")]}, config, stream_mode="updates"):
# 流失输出
# for chunk in graph.stream({"messages": [HumanMessage(content="你好 我是罗尼")]}, config, stream_mode="update"):
#     chunk['conversation']['messages'].pretty_print()

async for event in graph.astream_events({"messages": [HumanMessage(content="你好 我是罗尼")]}, config, version="v2"):
    print(f"Node:{event['metadata'].get('langgraph_code', '')}. Type:{event['event']}. Name:{event['name']}")
