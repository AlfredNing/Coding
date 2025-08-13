import sys
import streamlit as st
import os, json, time

from openai import OpenAI


@st.cache_resource
def get_open_ai_client(base_url, api_key):
    client = OpenAI(base_url=base_url, api_key=api_key)
    return client


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

    option = st.radio("system_prompt", ("Manual Input", "prompts"), horizontal=True, index=0)

    if option == 'Manual Input':
        system_prompt = st.text_input("System prompt please clear history",
                                      config_default['completions']['system_prompt'])

    else:
        with open(os.path.join(src_path, 'config/prompts.json'), 'r', encoding='utf-8') as f:
            masks = json.load(f)
            mask_zh = [item['name'] for item in masks['zh']]
            masks_zh_name = st.selectbox('prompts', mask_zh)
            for item in masks['zh']:
                if item['name'] == masks_zh_name:
                    system_prompt = item['context']
                    break

    if not st.checkbox('default param', True):
        max_tokens = st.number_input('Max Tokens', 1, 200000, config_default['completions']['max_tokens'],
                                     key='max_tokens')
        temperature = st.slider('Temperateure', 0.0, 1.0, config_default['completions']['temperature'],
                                key='temperature')

        top_p = st.slider('top p', 0.0, 1.0, config_default['completions']['top_p'],
                          key='top_p')

        stream = st.checkbox('Stream', config_default['completions']['stream'], key='stream')
    else:
        max_tokens = config_default['completions']['max_tokens']
        temperature = config_default['completions']['temperature']
        top_p = config_default['completions']['top_p']
        stream = config_default['completions']['stream']

    if 'chat_message' not in st.session_state:
        st.session_state['chat_message'] = [{"role": "system", "content": system_prompt}]

    if st.button("clear history"):
        st.session_state.chat_message = [{"role": "system", "content": system_prompt}]

    for msg in st.session_state.chat_message:
        with st.chat_message(msg['role']):
            st.markdown(msg["content"])

    if prompt := st.chat_input():
        try:
            client = get_open_ai_client(base_url, api_key)
        except Exception as e:
            st.error(e)
            st.stop()
        st.chat_message("user").write(prompt)
        with st.chat_message('assistant'):
            with st.spinner("thinking"):
                start_time = time.time()

                try:
                    temp_chat_message = st.session_state.chat_message
                    temp_chat_message.append({"role": "user", "content": prompt})
                    response = client.chat.completions.create(
                        model=model_name,
                        messages=temp_chat_message,
                        max_tokens=max_tokens,
                        temperature=temperature,
                        top_p=top_p,
                        stream=stream
                    )
                except Exception as e:
                    st.error(e)
                    st.stop()
                if response:
                    if stream:
                        placeholder = st.empty()
                        streaming_text = ' '
                        for chunk in response:
                            if chunk.choices[0].finish_reason == 'stop':
                                break
                            chunk_text = chunk.choices[0].delta.content
                            if chunk_text:
                                streaming_text += chunk_text
                                placeholder.markdown(streaming_text)
                        model_msg = streaming_text
                    else:
                        # model_msg = response.choices[0].message_content
                        model_msg = response.choices[0].message.content
                        st.markdown(model_msg)
                end_time = time.time()

                temp_chat_message.append({"role": "assistant", "content": model_msg})
                st.session_state.chat_message = temp_chat_message
                if config_default['completions']['use_time']:
                    st.info(f"User Time: {round(end_time - start_time, 2)}s")


if __name__ == '__main__':
    chat_page()
