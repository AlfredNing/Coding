import streamlit as st
from openai import OpenAI


def generate_speech(model, rate, prompt):
    # coding=utf-8
    import sys, os, tempfile
    from dashscope.audio.tts import SpeechSynthesizer
    # 若没有将API Key配置到环境变量中，需将apiKey替换为自己的API Key
    import dashscope
    dashscope.api_key = os.getenv('OPENAI_API_KEY')
    result = SpeechSynthesizer.call(model=model,
                                    text=prompt,
                                    rate=rate,
                                    format='wav')
    if result.get_audio_data() is not None:
        with open('output.wav', 'wb') as f:
            f.write(result.get_audio_data())
            print(f.name)
            speech_file_path = f.name
        print('SUCCESS: get audio data: %dbytes in output.wav' %
              (sys.getsizeof(result.get_audio_data())))
    else:
        print('ERROR: response is %s' % (result.get_response()))

    return speech_file_path


def tts_page():
    st.title("Text to Speech")
    st.caption("文本转语音")

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

    model = st.selectbox('model', ["sambert-zhinan-v1", "sambert-zhide-v1"])
    # voice = st.selectbox('voice', ["Acoustic", ""])
    rate = st.slider("speed", 0.5, 1.0, 2.0)
    option = st.radio("Input Methods", ("Manual Input", "import document"), horizontal=True, index=0)

    if option == "import document":
        upload_file = st.file_uploader("Choose a file", type=["txt"])
        content = None
        if upload_file is not None:
            content = upload_file.read().decode("utf-8")
            st.text(content)
        if st.button("generated") and content:
            with st.spinner("doing..."):
                speech_file = generate_speech(model, rate, content)
                st.audio(speech_file)

    else:
        if prompt := st.chat_input("prompt"):
            st.chat_message("user").write(prompt)
            with st.chat_message('assistant'):
                with st.spinner("doing..."):
                    try:
                        speech_file = generate_speech(model, rate, prompt)
                        st.audio(speech_file)
                    except Exception as e:
                        st.error(e)
                        st.stop()


if __name__ == '__main__':
    tts_page()
