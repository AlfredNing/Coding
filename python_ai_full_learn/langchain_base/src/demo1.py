from dotenv import load_dotenv
from langchain_core.messages import HumanMessage, SystemMessage
from langchain_core.output_parsers import StrOutputParser

load_dotenv()

from langchain_openai import ChatOpenAI

model = ChatOpenAI(model='qwen-max')
message = [
    SystemMessage(content='将以下内容翻译成中文'),
    HumanMessage(content="shit!")
]

parser = StrOutputParser()
chain = model | parser

# result = chain.invoke(message)
for chunk in model.stream(message):
    print(chunk.content)
# chain.ainvoke()


# print(result)
