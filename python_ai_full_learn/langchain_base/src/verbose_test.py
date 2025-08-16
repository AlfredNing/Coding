from dotenv import load_dotenv
from langchain_openai import ChatOpenAI
from langchain_core.prompts import ChatPromptTemplate
from langchain.agents import AgentExecutor, create_tool_calling_agent
from langchain_tavily import TavilySearch
from langchain.globals import set_debug, set_verbose

load_dotenv()

llm = ChatOpenAI(model='qwen-max')

tools = [TavilySearch(max_results=1)]

prompt = ChatPromptTemplate.from_messages([
    ("system", "你是一位得力的助手"),
    ("placeholder", "{chat_history}"),
    ("human", "{input}"),
    ("placeholder", "{agent_scratchpad}")
])

agent = create_tool_calling_agent(llm, tools, prompt)
set_verbose(True)
# set_debug(True)
agent_executor = AgentExecutor(agent=agent, tools=tools)
agent_executor.invoke(
    {"input": "谁执导了2023年的电影《奥本海默》，他多少岁了？"}
)
