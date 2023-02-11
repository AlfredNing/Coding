# 最高报酬

![image-20221119091556348](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221119091556348.png)

流程：

1. 给定工作进行排序

   难度从小到大，钱数从大到下

2. 二次排序

   取出每个难度组里面的第一个位置

3. 再次筛选：取出难度递增，钱数递增的工作

4. 准备一个有序表：按照难度排序

5. 对于能力查找<=能力值最近的工作

## 背包零食



# ![image-20221119092759614](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221119092759614.png)

动态规划：从左到右的尝试模型

改进：动态规划二维数组

改进：中间的点取决于该点的位置

​	- 准备一维数组：填入第0行，反复自我更新第到i行

![image-20221119095956492](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221119095956492.png)

![](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221119095956492.png)

# 公共子串 lCSubString

![image-20221119100034800](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221119100034800.png)

![image-20221119100317233](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221119100317233.png)

左上角位置+1

**最优解：后缀子数组**

# 词频统计

![image-20221119214235456](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221119214235456.png)

![image-20221119215109548](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221119215109548.png)

---

> 从本节开始，笔记转交语雀
