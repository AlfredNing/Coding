'''
案例-总结
'''
from dotenv import load_dotenv
from langchain_openai import ChatOpenAI
from langchain_core.prompts import PromptTemplate
from langchain_text_splitters import RecursiveCharacterTextSplitter
from langchain.chains.summarize import load_summarize_chain

load_dotenv()

llm = ChatOpenAI(model="qwen-max")


def short_summary():
    # 短文本总结
    template = """
%指示:
请总结一下文字 ，用5岁儿童能理解的方式回答。

%文本:
{text}
"""
    prompt = PromptTemplate(
        input_variables=['text'],
        template=template
    )
    confusing_txt = "《熵噬论》第五章：虚时褶皱中的量子自噬现象表明，非厄米拓扑相变会引发赝标量场的混沌迭代，导致克莱因瓶拓扑结构在希格斯真空中的非对易性坍缩，最终使冯·诺依曼观测者陷入哥德尔不完备性的递归迷宫。"
    final_prompt = prompt.format(text=confusing_txt)
    print(final_prompt)
    response = llm.invoke(final_prompt)
    print(response.content)


def long_summary():
    """
    较长文本的摘要
    :return:
    """

    with open('../data/long_summary.txt', 'r', encoding='utf-8') as f:
        text = f.read()
    # print(text[:200])
    num_token = llm.get_num_tokens(text)
    # print(f"你的文件有{num_token}token个")
    text_splitter = RecursiveCharacterTextSplitter(
        separators=["\n\n", "\n"],
        chunk_size=1000,
        chunk_overlap=150)

    # 切分成文档
    docs = text_splitter.create_documents([text])
    chain = load_summarize_chain(llm=llm, chain_type='map_reduce', verbose=True)
    # output = chain.run(docs)
    output = chain.invoke({"input_documents": docs})
    print(output)


if __name__ == '__main__':
    long_summary()
