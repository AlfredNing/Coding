- font-size: 文字大小
- color: 前景色
- background-color: 背景色
- width: 宽度
- height：高度

# 颜色表示法：
- 颜色关键字: https://developer.mozilla.org/zh-CN/docs/Web/CSS/color_value#%E8%AF%AD%E6%B3%95
- RGB颜色： 
    R：red
    G：green
    B: Blue
三原色取值范围：0 - 255
- RGB颜色表示法：
    - 16进制: #RRGGBB[A] A：可选值
    #f09=#ff0099
    #0f38=#00ff3388
- 函数法
    rgb[a](R、G、B[,A]) A:0-1之间的数字，或者百分比：1=100% 完全不透明
# 文本属性
##  text-decoration: 用于设置文字的装饰线， 实际开发用javascript开发比较多
- none:无任何装饰线，<B>可以去除a元素默认的下划线</B>
- underline:下划线
- overline: 上划线
- line-througn: 中划线，删除线
## text-transform: 用于设置文字转换的大小写
- capitalize: 使每个单词的首字符变为大写
- uppercase: 每个单词的所有字符变为大写
- lowercase: 每个单词的所有字符变为小写
- none:没有任何影响
## text-indent: 用于设置第一行内容的缩进
- text-indent: 2em:缩进2个文字
## text-align: 定义行内内容，如何想对它的块元素对齐
- left：左对齐
- right：右对齐
- center:正中间显示
- justify：两端对齐
## left-spacing, word-spacing: 用于设置字母、单词之间的间距
- 默认是0，可以设置为负数
# 字体属性
## font-size:设置字体大小
- 数值+单位：100px，em不推荐：1em=100%,2em=200%,0.5em=50%
- 百分比：基于父元素的font-size计算，50%表示是父元素font-size的一半
## font-family:设置文字的字体名称
- 可以设置1个或多个字体
- 可以通过@font-face:指定下载的字体
## font-weigth:设置文字的粗细
- 100|200|300|400|500....|900
- normal: 等于400
- bold:等于700
- strong、b、h1-h5 font-weight默认是bold
## font-style: 用于设置文字格式内容
- normal: 常规显示
- italic: 用字体的斜体显示
- oblique: 文本倾斜显示
- em/i/cite/address/var/dfn 等元素的的font-style默认就是italic
## font-variant: 影响小写字母的显示形式
- normal:常规显示
- small-caps: 将小写字母替换为缩写过得的大写字母
## line-hegith: 设置文本的行高
- hegiht: 元素整体的高度，line-heigh:元素的每一行文字所占据的高度
## font
- font 属性可以用来作为font-style, font-variant, font-weight, font-size, line-height 和 font-family 属性的缩写

# 选择器
