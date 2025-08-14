import streamlit as st
import os, sys, json

from openai import OpenAI


def vision_page():
    st.title("图像理解")
    st.caption("接受vision")

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

    client = OpenAI(
        # 若没有配置环境变量，请用百炼API Key将下行替换为：api_key="sk-xxx",
        api_key=api_key,
        base_url=base_url
    )

    src_path = os.path.dirname(os.path.realpath(sys.argv[0]))
    with open(os.path.join(src_path, 'config/default.json'), 'r', encoding='utf-8') as f:
        config_default = json.load(f)

    st.session_state['model_list'] = config_default["completions"]["models"]

    upload_images = st.file_uploader("upload image ", type=["png", "jpg", "jpeg"], label_visibility="collapsed")

    MAX_FILE_SIZE = 5 * 1024 * 1024

    max_tokens = st.number_input("max tokens", min_value=1, value=300, step=1)

    bytes_data = None
    if upload_images is not None:
        if upload_images.size > MAX_FILE_SIZE:
            st.error("file is too large")
        else:
            bytes_data = upload_images.getvalue()
            st.image(bytes_data, caption=upload_images.name, width=200)

    if prompt := st.chat_input():
        st.chat_message("user").write(prompt)
        with st.chat_message("assistant"):
            with st.spinner("thinking..."):
                try:
                    completion = client.chat.completions.create(
                        model="qwen-vl-plus",
                        # 此处以qwen-vl-plus为例，可按需更换模型名称。模型列表：https://help.aliyun.com/zh/model-studio/getting-started/models
                        messages=[{"role": "user", "content": [
                            {"type": "image_url",
                             "image_url": {
                                 "url": "https://dashscope.oss-cn-beijing.aliyuncs.com/images/dog_and_girl.jpeg"}},
                            {"type": "text", "text": "这是什么"},
                        ]}]
                    )

                    result = completion.choices[0].message.content
                    st.markdown(result)
                except Exception as e:
                    st.error(e)
                    st.stop()


if __name__ == "__main__":
    vision_page()
