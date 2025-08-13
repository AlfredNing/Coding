import os

from openai import OpenAI
from dashscope import ImageSynthesis
import dotenv

dotenv.load_dotenv()

client = OpenAI()


def chat():
    response = client.chat.completions.create(
        model='qwen-max',
        messages=[
            {"role": "user", "content": "1加1等于几？"}
        ],
        max_tokens=4096,
        temperature=0.7,
        top_p=0.9,
        stream=True
    )
    for chunk in response:
        print(chunk.model_dump_json())


def drawing():
    rsp = ImageSynthesis.call(api_key=os.getenv('OPENAI_API_KEY'),
                              model="wan2.2-t2i-flash",
                              prompt="一张猫的图片",
                              n=1,
                              size='1024*1024')
    print(rsp)


if __name__ == '__main__':
    drawing()
