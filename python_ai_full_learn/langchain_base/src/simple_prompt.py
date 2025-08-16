from langchain_core.prompts import PromptTemplate

prompt_template = PromptTemplate.from_template("我的名字是{name},然后起一个英文名字")
print(prompt_template.invoke({"name": "宁强"}))
