// 创建一个新的<div>元素
import "../css/style.css"
const newDiv = document.createElement('div');
newDiv.className = "title";
newDiv.innerHTML = '这是一个新的元素';

// 将新的<div>元素添加到<body>标签中
document.body.appendChild(newDiv);