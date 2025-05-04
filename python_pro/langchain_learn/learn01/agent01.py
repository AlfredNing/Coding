from langchain.agents import load_tools
from langchain.agents import initialize_agent
import json
import os
from dotenv import load_dotenv

load_dotenv()

from langchain_openai import ChatOpenAI

llm = ChatOpenAI(model_name='qwen-max')

serp_api_key = os.getenv('SERP_API_KEY')
tool_kit = load_tools(['serpapi'], llm=llm, serpapi_api_key=serp_api_key)

agent = initialize_agent(tool_kit, llm, agent="zero-shot-react-description", verbose=True, return_intermediate=True)
response = agent.invoke({"input": "今日上证指数收盘多少点"})
print(response)
