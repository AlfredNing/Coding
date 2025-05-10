from typing import TypedDict, Optional, List, Annotated

from langgraph.graph import StateGraph, START, END
from operator import add


# Log结构
class Log(TypedDict):
    id: str
    question: str
    docs: Optional[int]
    answer: str
    grade: Optional[int]
    grader: Optional[str]
    feedback: Optional[str]


# 故障分析子图
class FailureAnalysisState(TypedDict):
    cleaned_logs: List[Log]
    failures: List[Log]
    fa_summery: str
    processed_logs: List[str]


# 格式化输出
class FailureAnalysisOutputState(TypedDict):
    fa_summary: str
    processed_logs: List[str]


def get_failures(state):
    """获取包含失败的日志"""
    cleaned_logs = state['cleaned_logs']
    failures = [log for log in cleaned_logs if "grade" in log]
    return {"failures": failures}


def generate_summary(state):
    """生成故障摘要"""
    failures = state['failures']
    fa_summary = "poor quality retrieval Chroma documentation"
    return {"fa_summary": fa_summary,
            "processed_logs": [f"failure-logs-on-log-{failures['id']}" for failures in failures]}


# 故障摘要子图
fa_builder = StateGraph(FailureAnalysisState, output=FailureAnalysisOutputState)
fa_builder.add_node("get_failures", get_failures)
fa_builder.add_node("generate_summary", generate_summary)
fa_builder.add_edge(START, "get_failures")
fa_builder.add_edge("get_failures", "generate_summary")
fa_builder.add_edge("generate_summary", END)


# fa_graph = fa_builder.compile()


# 问题摘要子图
class QuestionSummarizationState(TypedDict):
    cleaned_logs: List[Log]
    qs_summary: str
    report: str
    processed_logs: List[str]


class QuestionSummarizationOutputState(TypedDict):
    report: str
    processed_logs: List[str]


def generate_summary(state):
    cleaned_logs = state['cleaned_logs']
    qs_summary = "问题集中在ChatOllama 和Chroma 矢量存储上面"
    return {"qs_summary": qs_summary, "processed_logs": [f"summary-on-log-{log['id']}" for log in cleaned_logs]}


def send_to_slack(state):
    qs_summary = state["qs_summary"]
    report = "foo baz baz"
    return {"report": report}


qs_builder = StateGraph(QuestionSummarizationState, output=QuestionSummarizationOutputState)
qs_builder.add_node("generate_summary", generate_summary)
qs_builder.add_node("send_to_slack", send_to_slack)
qs_builder.add_edge(START, "generate_summary")
qs_builder.add_edge("generate_summary", "send_to_slack")
qs_builder.add_edge("send_to_slack", END)


# qa_graph = qs_builder.compile()


# 父图

class EntryGraphState(TypedDict):
    raw_logs: List[Log]
    cleaned_logs: List[Log]
    fa_summary: str  # 故障分析子图中使用
    report: str  # 在摘要子图中生成
    processed_logs: Annotated[List[int], add]  # 这将在两个子图中生成


def clean_logs(state):
    raw_logs = state['raw_logs']
    cleaned_logs = raw_logs
    return {"cleaned_logs": cleaned_logs}


entry_builder = StateGraph(EntryGraphState)
entry_builder.add_node("clean_logs", clean_logs)
entry_builder.add_node("question_summarization", qs_builder.compile())
entry_builder.add_node("failure_analysis", fa_builder.compile())

entry_builder.add_edge(START, "clean_logs")
entry_builder.add_edge("clean_logs", "failure_analysis")
entry_builder.add_edge("clean_logs", "question_summarization")
entry_builder.add_edge("failure_analysis", END)
entry_builder.add_edge("question_summarization", END)
graph = entry_builder.compile()

# 虚拟日志（Dummy logs）
question_answer = Log(
    id="1",
    question="我如何导入ChatOllama？",
    answer="要导入ChatOllama，请使用：'from langchain_community.chat_models import ChatOllama.'",
)

question_answer_feedback = Log(
    id="2",
    question="我如何使用Chroma向量存储？",
    answer="要使用Chroma，请定义：rag_chain = create_retrieval_chain(retriever, question_answer_chain).",
    grade=0,
    grader="文档相关性召回",
    feedback="检索到的文档讨论了向量存储的一般概念，但没有具体涉及Chroma。",
)

raw_logs = [question_answer, question_answer_feedback]
resp = graph.invoke({"raw_logs": raw_logs})
print(resp)
