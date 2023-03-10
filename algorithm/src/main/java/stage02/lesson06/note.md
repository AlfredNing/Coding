> 如何刷题 
>
> LeetCode 英文版
>
> Top Interview Questions
>
> 牛客 看面试经
>
> 面试时候面试官烦人会说的比较复杂

# 打表技巧

给定一个数组，求数组内所有的质数之和

提前做好一张表，需要结果时候直接从表里面拿。与预处理不同的是，预处理每次的预处理的结果都不一样。

1. 问题如果返回值不多，可以用hardcode的方式列出，作为程序的一部分
2. 一个大问题解决时底层频繁使用规模不大的小问题的解，如果小问题的返回值满足条件1，可以把小问题的解列成一张表，作为程序的一部分
3. 打表找规律

## 打表找规律

输入参数简单、输出参数简单、写个暴力解、分析规律，至于什么数学原理，之后再去想，不会耽误工作。

- 某个面试题，输入参数类型简单，并且只有一个实际参数
- 要求的返回值类型也简单，并且只有一个
- 用暴力方法，把输入参数对应的返回值，打印出来看看，进而优化code.

###  题目1 AppleMinBag

小虎去买苹果，商店只提供两种类型的塑料袋，每种类型有任意数量。

- 能装下6个苹果的袋子
- 能装下8个苹果的袋子

小虎自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，且每个袋子必须装满。

给定一个正整数N，返回至少使用多少袋子。如果N无法让使用的每个袋子必须装满，返回-1。

### 题目2 EatGrass

牛羊吃草

![image-20220827184318354](C:\Users\nq\AppData\Roaming\Typora\typora-user-images\image-20220827184318354.png)

### 题目3  连续数和

![image-20220827185457521](C:\Users\nq\AppData\Roaming\Typora\typora-user-images\image-20220827185457521.png)

# 矩阵处理技巧

1. zigzag打印矩阵
2. 转圈打印矩阵
3. 原地旋转正方形矩阵

**核心技巧：找到coding上的宏观调度**

### 题目1 zigzag打印矩阵  ZigZagPrintMatrix

锯齿形打印

准备两个点：A先往右走，直到不能往右，在往下面。B先往下，不能往下了，在往右。

### 题目2 转圈打印矩阵 **PrintMatrixSpiralOrder**

打印矩阵

### 题目3 原地旋转正方形矩阵 ****RotateMatrix****