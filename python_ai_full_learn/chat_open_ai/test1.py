import os

from openai import OpenAI
from dashscope import ImageSynthesis
import dotenv

dotenv.load_dotenv()

client = OpenAI()


def chat():
    response = client.chat.completions.create(
        model='qwen-max',
        messages=[
            {"role": "user", "content": "1加1等于几？"}
        ],
        max_tokens=4096,
        temperature=0.7,
        top_p=0.9,
        stream=True
    )
    for chunk in response:
        print(chunk.model_dump_json())


def drawing():
    rsp = ImageSynthesis.call(api_key=os.getenv('OPENAI_API_KEY'),
                              model="wan2.2-t2i-flash",
                              prompt="一张猫的图片",
                              n=1,
                              size='1024*1024')
    print(rsp)


def text_to_speech():
    # coding=utf-8
    import sys
    from dashscope.audio.tts import SpeechSynthesizer
    # 若没有将API Key配置到环境变量中，需将apiKey替换为自己的API Key
    import dashscope
    dashscope.api_key = os.getenv('OPENAI_API_KEY')
    result = SpeechSynthesizer.call(model='sambert-zhichu-v1',
                                    text='今天天气怎么样',
                                    sample_rate=48000,
                                    format='wav')
    if result.get_audio_data() is not None:
        with open('output.wav', 'wb') as f:
            f.write(result.get_audio_data())
        print('SUCCESS: get audio data: %dbytes in output.wav' %
              (sys.getsizeof(result.get_audio_data())))
    else:
        print('ERROR: response is %s' % (result.get_response()))


if __name__ == '__main__':
    text_to_speech()
