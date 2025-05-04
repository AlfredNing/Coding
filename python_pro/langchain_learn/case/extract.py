"""
提取
"""
from dotenv import load_dotenv

load_dotenv()
from langchain_openai import ChatOpenAI

llm = ChatOpenAI(model_name='qwen-max')
from langchain.output_parsers import StructuredOutputParser, ResponseSchema

response_schema = [
    ResponseSchema(name="艺术家", description="音乐艺术家的姓名"),
    ResponseSchema(name="歌曲", description="音乐艺术家演奏的歌曲名称")
]

output_parser = StructuredOutputParser.from_response_schemas(response_schema)
format_instruction = output_parser.get_format_instructions()
# print(output_parser.get_format_instructions())

from langchain_core.messages import HumanMessage
from langchain_core.prompts import ChatPromptTemplate, HumanMessagePromptTemplate

prompt = ChatPromptTemplate(
    messages=[
        HumanMessagePromptTemplate.from_template(
            "根据用户的指令，提取所有的艺术家和歌曲名称 \n {format_instruction} \n {user_prompt}")
    ],
    input_variables=["user_prompt"],
    partial_variables={"format_instruction": format_instruction}
)
query = prompt.format_prompt(user_prompt="在当代乐坛，周杰论以其独特的风格创造了诸如《七里香》等经典作品")

output = llm.invoke(query.to_messages())
resp_content = output_parser.parse(output.content)
print(resp_content)
