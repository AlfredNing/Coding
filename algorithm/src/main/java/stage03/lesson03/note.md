# 容器装水

## 问题1 TrappingRainWater

![image-20221113084954128](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221113084954128.png)

思路：针对一个位置i，求出所能装载的容器水，对所有位置i进行累加求和

min(max(|左高- 右高| - arr[i]),0)

优化：

- 预处理数组

  输入： 4,5,1,3,2

  前缀高度左：4,5,5,5,5

  高度右：2,3,3，5,5

- 双指针

  初始位置：L: 1; R：n-2

  arr[L] < arr[r] : 计算L指向

  arr[r] < arr[l]: 计算R指向

## 问题2 - TrappinGRainWater2

![image-20221113102553908](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221113102553908.png)

思路：准备一个小根堆：初始化，边缘数组值入堆

二维数组表示数组的数是否进入过堆中

每次出堆时候，更新max值，计算结果

# 求aim

![image-20221113115246783](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221113115246783.png)

1. 双指针：arr[L] + arr[R] = aim,相等时候，多判断一下相邻位置，减支
2. 每一个位置进行进行双指针0(N^2)

# 第k小数值对![image-20221113121048616](https://nq-bucket.oss-cn-shanghai.aliyuncs.com/note_img/image-20221113121048616.png)