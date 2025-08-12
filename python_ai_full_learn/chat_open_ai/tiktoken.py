from openai import OpenAI
from dotenv import load_dotenv
import tiktoken

load_dotenv()

# encoding= tiktoken.encoding_for_model("g")

client = OpenAI()

completion = client.embeddings.create(
    model="text-embedding-v4",
    input='衣服的质量杠杠的，很漂亮，不枉我等了这么久啊，喜欢，以后还来这里买',
    dimensions=1024, # 指定向量维度（仅 text-embedding-v3及 text-embedding-v4支持该参数）
    encoding_format="float"
)
print(completion.model_dump_json())
