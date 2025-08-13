from openai import OpenAI
import dotenv

dotenv.load_dotenv()

client = OpenAI()
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
