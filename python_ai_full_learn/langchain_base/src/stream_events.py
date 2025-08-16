from dotenv import load_dotenv
from langchain_openai import ChatOpenAI
import asyncio

load_dotenv()
import langchain_core

import os

print(langchain_core.__version__)
model = ChatOpenAI(model="qwen-max", api_key=os.getenv("OPENAI_API_KEY"), base_url=os.getenv("OPENAI_BASE_URL"))


async def async_stream():
    events = []
    async for event in model.astream_events('hello', version="v2"):
        events.append(event)
    print(events)


asyncio.run(async_stream())
