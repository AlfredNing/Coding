import requests
import json, os
from typing import List
from langchain_core.embeddings import Embeddings
from dotenv import load_dotenv

load_dotenv()


class BailianEmbeddings(Embeddings):
    def __init__(
            self,
            api_key: str = os.getenv('OPENAI_API_KEY'),
            endpoint: str = "https://dashscope.aliyuncs.com",  # 请根据实际填写
            model_name: str = "text-embedding-v4",  # 请根据你实际使用的模型名称填写
    ):
        self.api_key = api_key
        self.endpoint = endpoint.rstrip("/")  # 移除末尾斜杠
        self.model_name = model_name

    def embed_documents(self, texts: List[str]) -> List[List[float]]:
        embeddings = []
        for text in texts:
            # 构造请求（注意：这里的请求格式需要根据阿里云百炼的 Embedding API 文档来定！）
            payload = {
                "model": self.model_name,
                "input": text
            }
            headers = {
                "Content-Type": "application/json",
                "Authorization": f"Bearer {self.api_key}",  # 或者使用阿里云签名方式，看API要求
                # 可能还需要 X-API-Key, X-Access-Key 等，根据实际文档
            }

            # 注意：这里的 URL 只是一个示例，请根据阿里云百炼的 Embedding 接口文档填写正确的 endpoint 和 path
            url = f"{self.endpoint}/compatible-mode/v1/embeddings"  # 假设路径，请以文档为准

            response = requests.post(url, headers=headers, data=json.dumps(payload))
            if response.status_code != 200:
                raise ValueError(f"请求阿里云百炼 Embedding API 失败: {response.status_code}, {response.text}")

            result = response.json()
            # 假设返回的数据格式为 {"data": [{"embedding": [0.1, 0.2, ...]}]}
            embedding = result.get("data", [{}])[0].get("embedding")
            if not embedding:
                raise ValueError(f"未能从响应中解析嵌入向量: {result}")
            embeddings.append(embedding)
        return embeddings

    def embed_query(self, text: str) -> List[float]:
        return self.embed_documents([text])[0]
