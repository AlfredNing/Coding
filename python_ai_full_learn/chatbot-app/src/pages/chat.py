import sys

import streamlit as st
import os, json


def chat_page():
    st.title("chat 文本对话")
    if "base_url" in st.session_state:
        base_url = st.session_state["base_url"]
    else:
        base_url = "https://dashscope.aliyuncs.com/compatible-mode/v1"

    api_key = (
        st.session_state.api_key
        if "api_key" in st.session_state and st.session_state.api_key != ""
        else None
    )

    if api_key is None:
        st.error("Please input ApiKey")
        st.stop()

    src_path = os.path.dirname(os.path.realpath(sys.argv[0]))
    with open(os.path.join(src_path, 'config/default.json'), 'r', encoding='utf-8') as f:
        config_default = json.load(f)

    st.session_state['model_list'] = config_default["completions"]["models"]
    model_name = st.selectbox('Models', st.session_state['model_list'])




if __name__ == '__main__':
    chat_page()
