"""
聊天机器人
"""
from dotenv import load_dotenv

load_dotenv()
from langchain_openai import ChatOpenAI

llm = ChatOpenAI(model_name='qwen-max')

template = """
你是一个毫无帮助的机器人,
你的目标不是帮助用户，而是开玩笑，
把用户说的话当作笑话讲。

{chat_history}
Human:{human_input}
ChatBot:""
"""
from langchain_core.prompts import PromptTemplate
from langchain.memory import ConversationBufferMemory

prompt = PromptTemplate(
    template=template,
    input_variables=['chat_history', 'human_input']
)

memory = ConversationBufferMemory(memory_key='chat_history')

from langchain import LLMChain

llm_chain = LLMChain(
    llm=llm,
    prompt=prompt,
    verbose=True,
    memory=memory
)
llm_chain.predict(human_input='你是好人还是坏人')

llm_chain.predict(human_input='之前谁是人')
