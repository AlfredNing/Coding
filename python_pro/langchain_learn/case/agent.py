from dotenv import load_dotenv

load_dotenv()
import os
import json

GOOGLE_CSE_ID = os.getenv('GOOGLE_CSE_ID')
GOOGLE_API_KEY = os.getenv('GOOGLE_API_KEY')

from langchain_openai import ChatOpenAI

llm = ChatOpenAI(model_name='qwen-max')

from langchain_community.utilities import GoogleSearchAPIWrapper, TextRequestsWrapper

search = GoogleSearchAPIWrapper(google_api_key=GOOGLE_API_KEY, google_cse_id=GOOGLE_CSE_ID)

requests = TextRequestsWrapper()

from langchain_core.tools import Tool

took_kit = [
    Tool(
        name='Search',
        func=search.run,
        description="当你需要搜索google时候会有用"
    ),
    Tool(
        name="Requests",
        func=requests.get,
        description="当你向 URL 发出请求时候会非常有用"
    )
]

from langchain.agents import initialize_agent

agent = initialize_agent(took_kit, llm, agent='zero-shot-react-description', verbose=True,
                         return_intermediate_steps=True, handle_parsing_errors=True)
# agent.invoke({'input': "中国的首都是哪里"})
# agent({
#     'input': "用中文告诉这个网页上关于五四青年节的信息 https://baike.baidu.com/item/%E4%BA%94%E5%9B%9B%E9%9D%92%E5%B9%B4%E8%8A%82"})
agent.invoke({
    'input': "用中文告诉这个网页上关于五四青年节的信息 https://baike.baidu.com/item/%E4%BA%94%E5%9B%9B%E9%9D%92%E5%B9%B4%E8%8A%82"})
