"""退一步总结"""
from langchain_openai import ChatOpenAI
from langchain_core.output_parsers import StrOutputParser
from langchain_core.prompts import ChatPromptTemplate, FewShotChatMessagePromptTemplate

examples = [
    {
        "input": "警察进行合法逮捕吗？",
        "output": "警察可以做什么？",
    },
    {
        "input": "Jan Sindel’s was born in what country?",
        "output": "what is Jan Sindel’s personal history?",
    },
]
# 我们现在将它们转换为示例消息
example_prompt = ChatPromptTemplate.from_messages(
    [
        ("human", "{input}"),
        ("ai", "{output}"),
    ]
)
few_shot_prompt = FewShotChatMessagePromptTemplate(
    example_prompt=example_prompt,
    examples=examples,
)
prompt = ChatPromptTemplate.from_messages(
    [
        (
            "system",
            """您是世界知识方面的专家。您的任务是退一步思考，将问题解释为更通用的退一步思考问题，这样更容易回答。以下是几个示例：""",
        ),
        # 一些镜头示例
        few_shot_prompt,
        # 新的问题
        ("user", "{question}"),
    ]
)
from langchain_openai import ChatOpenAI
from dotenv import load_dotenv

load_dotenv()
llm = ChatOpenAI(model='qwen-max')

generate_queries_step_back = prompt | llm | StrOutputParser()
question = "人工智能如何用于金融风控？"
resp = generate_queries_step_back.invoke({"question": question})
print(resp)
