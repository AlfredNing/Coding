from dotenv import load_dotenv

load_dotenv()
import os

api_key = os.getenv("DASHSCOPE_API_KEY")
from openai import OpenAI

client = OpenAI(
    # api_key=api_key,
    # base_url="https://dashscope.aliyuncs.com/compatible-mode/v1"
)
completion = client.chat.completions.create(
    model='qwen-plus',
    messages=[
        {"role": "system", "content": "You are a helpful assistant."},
        {"role": "user", "content": "你是谁？"},
    ]
)

print(completion.model_dump_json())
