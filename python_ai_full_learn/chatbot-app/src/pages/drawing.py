import streamlit as st
from dashscope import ImageSynthesis
import os


def drawing_page():
    st.title("百炼不支持openAi调用方式")
    st.title("文生图")
    st.caption("draw images")
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

    image_size = st.selectbox('image size', ["1024*1024", "1024*1792", "1792*1024"], key="image_size")
    num_image = st.selectbox('image num', [1], key='num_image')

    if prompt := st.chat_input("prompt"):
        st.chat_message("user").write(prompt)
        with st.chat_message("assistant"):
            with st.spinner('thinking...'):
                try:
                    rsp = ImageSynthesis.call(api_key=api_key,
                                              model="wan2.2-t2i-flash",
                                              prompt=prompt,
                                              n=num_image,
                                              size=image_size)
                    print(rsp)
                    for image in rsp.output.results:
                        image_url = image.url
                        orig_prompt = image.orig_prompt
                        actual_prompt = image.actual_prompt
                        st.image(image_url, caption=orig_prompt, width=200)

                        down_linkd = f'<a href="{image_url} download">DownLoad</a>'

                        st.markdown(down_linkd, unsafe_allow_html=True)
                        st.write("actual_prompt: " + actual_prompt)
                except Exception as e:
                    st.error(e)
                    st.stop()


if __name__ == '__main__':
    drawing_page()
