import streamlit as st
from streamlit import audio_input
import dashscope
from http import HTTPStatus
import os, sys, json
from io import BytesIO


def trans_online(audio_file):
    task_response = dashscope.audio.asr.Transcription.async_call(
        model='sensevoice-v1',
        file_urls=['https://dashscope.oss-cn-beijing.aliyuncs.com/samples/audio/sensevoice/rich_text_example_1.wav'],
        #
        # file_urls=[audio_file],
        language_hints=['en'],
    )

    transcribe_response = dashscope.audio.asr.Transcription.wait(task=task_response.output.task_id)
    if transcribe_response.status_code == HTTPStatus.OK:
        # print(json.dumps(transcribe_response.output, indent=4, ensure_ascii=False))
        print(transcribe_response)
        print('transcription done!')

        return transcribe_response["output"]["results"][0]["transcription_url"]
    return "none"


def speech_to_text():
    st.title("speech to text(语音转文本)")
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

    if "button_active" not in st.session_state:
        st.session_state["button_active"] = False

    src_path = os.path.dirname(os.path.realpath(sys.argv[0]))
    with open(os.path.join(src_path, 'config/default.json'), 'r', encoding='utf-8') as f:
        config_default = json.load(f)

    option = st.radio("Input Methods", ["Recoding", "Uploading"], horizontal=True, index=0)
    if option == "Recoding":
        audio_bytes = audio_input(label="recoding audio")
        if audio_bytes:
            st.text("Recoded audio")
            st.audio(audio_bytes, format="audio/wav")
            audio_file = audio_bytes
            audio_file.name = "audio.wav"
    else:
        st.write("uploading wav audio file")
        audio_file = st.file_uploader("upload wav file", type=["wav", "mp3"])
        if audio_file:
            if audio_file.size > 500:
                st.error("too large audio")

    if st.button("transcribe"):
        if audio_file:
            with st.spinner("please wait"):
                try:
                    script = trans_online(audio_file)
                    # st.write(script)
                    st.markdown(script)
                except Exception as e:
                    st.error(e)
                    st.stop()
        else:
            st.warning("please upload an audio file")


if __name__ == '__main__':
    speech_to_text()
