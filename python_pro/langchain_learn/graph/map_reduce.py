subjects_prompt = """Generate a list of 3 sub-topics that are all related to this overall topic:{topic}  Return as JSON with key 'subjects'"""
joke_prompt = """Generate a joke about {subject}  Return as JSON with key 'joke'"""

best_joke_prompt = """Below are a bunch of jokes about {topic}. Select the best one! Return the ID of the best one, starting 0 as the ID for the first joke. Jokes: \n\n {jokes}  Return as JSON with key 'id'"""

from dotenv import load_dotenv

load_dotenv()
from langchain_openai import ChatOpenAI

llm = ChatOpenAI(model='qwen-max')


from pydantic import BaseModel
from typing import List, TypedDict, Annotated


class Subjects(BaseModel):
    subjects: List[str]


class BestJoke(BaseModel):
    id: int


from langgraph.graph import StateGraph, START, END
import operator


class OverAllState(TypedDict):
    topic: str
    subjects: List
    jokes: Annotated[list, operator.add]
    best_selected_joke: str


def generate_topics(state: OverAllState):
    prompt = subjects_prompt.format(topic=state["topic"])
    response = llm.with_structured_output(Subjects).invoke(prompt)
    return {"subjects": response.subjects}


from langgraph.constants import Send


def continue_to_jokes(state: OverAllState):
    return [Send("generate_joke", {"subject": s}) for s in state['subjects']]


class JokeState(TypedDict):
    subject: str


class Joke(BaseModel):
    joke: str


def generate_joke(state: JokeState):
    prompt = joke_prompt.format(subject=state["subject"])
    response = llm.with_structured_output(Joke).invoke(prompt)
    return {"jokes": [response.joke]}


def best_joke(state: OverAllState):
    jokes = "\n\n".join(state["jokes"])
    prompt = best_joke_prompt.format(topic=state["topic"], jokes=jokes)
    response = llm.with_structured_output(BestJoke).invoke(prompt)
    return {"best_selected_joke": state["jokes"][response.id]}


graph = StateGraph(OverAllState)
graph.add_node("generate_topics", generate_topics)
graph.add_node("generate_joke", generate_joke)
graph.add_node("best_joke", best_joke)
graph.add_edge(START, "generate_topics")
graph.add_conditional_edges("generate_topics", continue_to_jokes, ["generate_joke"])
graph.add_edge("generate_joke", "best_joke")
graph.add_edge("best_joke", END)

app = graph.compile()
for s in app.stream({"topic": "玩具"}):
    print(s)
