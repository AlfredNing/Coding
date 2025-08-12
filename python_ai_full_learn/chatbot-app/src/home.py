import streamlit as st
from dotenv import load_dotenv
import os

load_dotenv()


def home():
    st.title("AlfredNing Chat OpenAI Library")
    st.caption("Please setting your parameters before using this app")

    if "base_url" not in st.session_state:
        st.session_state['base_url'] = os.getenv("OPENAI_BASE_URL")
    if "api_key" not in st.session_state:
        st.session_state['api_key'] = os.getenv("OPENAI_API_KEY")

    st.session_state.base_url = st.sidebar.text_input("Base Url", st.session_state.base_url)
    st.session_state.api_key = st.sidebar.text_input("API Key", st.session_state.api_key)

    option = st.radio("change Language", ("Zh", "En"), horizontal=True, index=1)

    if option == 'Zh':
        st.markdown(
            """
            ** 体验OpenAI
            ### 使用说明
            侧边栏填写Key
            """
        )
        st.markdown(
            """
            ### 1. chat :文本对话
            ### 2. drawing : 文生图
            ### 3. speech to text: 语音转文本
            ### 4. text to speech: 文本转语音
            ### 5. speech to text: 语音转文本
            """
        )
    elif option == 'En':
        st.markdown("""
        英语内容...
        """)


if __name__ == '__main__':
    home()
