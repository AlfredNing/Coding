from langchain_openai import ChatOpenAI
from dotenv import load_dotenv
from langchain.schema import HumanMessage, SystemMessage, AIMessage

load_dotenv()

llm = ChatOpenAI(model='qwen-turbo', temperature=0.7)
resp = llm.invoke(
    [
        SystemMessage(content='你是一个很棒的机器人，能用一句话帮助用户该吃什么'),
        HumanMessage(content='我喜欢吃西红柿，应该吃什么呢')
    ]
)
print(resp.content)
