from dotenv import load_dotenv
from langchain_openai import ChatOpenAI
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.output_parsers import JsonOutputParser

load_dotenv()

llm = ChatOpenAI(model="qwen1.5-72b-chat")

prompt = ChatPromptTemplate.from_messages([
    ('system', '您是世界级别的技术文档编写'),
    ('user', "{input}"),
])

output_parser = JsonOutputParser()

chain = prompt | llm | output_parser
resp = chain.invoke({"input": "大模型的langchain是什么?问题用question 答案用answer, 用JSON格式回复"})

print(resp)
print("======")
# print(resp.content)
