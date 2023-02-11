# 经典面试题

## 题目1 - CordCoverMaxPoint

![image-20221023094530184](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221023094530184.png)

### 解法一 二分法 -O(n * logn)

从数组位置开始，每次二分查找》=左边缘位置

### 解法二  滑动窗口 O(n)

左右移动，维持了单调性。每次到一个位置判断，窗口是否计算。更新结果

## 题目2 -  NeedParentheses

![image-20221023101622060](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221023101622060.png)

### 解法一

用栈去构建

### 解法二

有限几个变量

问题1：

给定一个变量： 遇到 （  加1 遇到 ） 减一

如果中途 < 0 ，退出一定不是有效的

问题2：

设定两个变量：

 count: 遇到 （  加1

count遇到） 减一 end + 1 ,count加1  等同于count不变

返回结果： count + end	

## 题目3 - ParenthesesDeep#maxLength

![image-20221023104108835](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221023104108835.png)

看见子串 子数组 这些一般要想到以枚举每一个开始位置，或者是枚举每一个为结尾的答案

“）（（（（）））））（（（）”

来到i位置，如果是左括号结尾肯定不是有效的。

dp[0] = 0;

dp[1] = 0;

i位置右括号

假设dp[i - 1] = 4; 

查找i - 1 - 4是有效的，

查看在前一个：

右括号： dp[i] = 0

左括号： dp[i - 1] + 2  + 在往前前一个的结果值

## 题目4 - ParenthesesDeep#deep

给定一个括号数组，返回括号的最大嵌套长度

“（）（（（））（）”

返回：2

---

## 题目5 - ColorLeftRight

![image-20221023111505137](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221023111505137.png)

找出分界线，结果左侧是R右侧是G.

枚举每一个分界线，涂染的个数： 左侧的G + 右侧的R

**预处理子数组**

L : 从左往右 记录每个位置上，左侧开始到i位置的G个数 **可以省略该辅助数组**

R: 从右往左每个位置上，记录右侧到i位置的R个数

**从辅助数组加速小查询**

## 题目6 - MaxOneBoardSize

![image-20221023121057930](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221023121057930.png)

基本知识：

给定一个N * N矩阵，生成的长方形数量在O(N ^ 4) , 生成的正方形数量在O( N ^ 3) 

常规流程： 

从每一个左上角位置开始，遍历每一次边长

for ( row) {

​	for (col) {

​			for (边长) {

​					// 如何确定边长为1 的话，遍历O(N)

​			}

​		}

​	}

数量级0(N ^ 4)

**优化流程**：

确定边框全是1，优化查询，达到O(n ^ 3)时间复杂度

right 辅助数组【i】【j】 记录： 从最右侧到ij位置连续为1的个数

down 辅助数组【i】【j】 记录： 从最底层侧到ij位置连续为1的个数