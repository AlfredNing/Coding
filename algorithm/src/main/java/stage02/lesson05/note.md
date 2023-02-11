# 线段树 SegmentTreeCode
给定一个数组int[] arr
提供三个方法
- void add(L, R, V, arr) 在L...R范围上加上一个V
- void update(L, R, V, arr) 在L...R范围上更新成V
- int getSum(L, R, arr) 在L...R范围上求和
做到以上方法log(N)
## 线段树题目
1. **FallingSquares**![image-20220821201602658](C:\Users\nq\AppData\Roaming\Typora\typora-user-images\image-20220821201602658.png)

2. 线段重合问题 **CoverMax**
   给出线段起始点终点，返回线程重合的最大个数

   流程：

   - 准备一个小根堆：之前还有哪些线段会此时线程产生影响的结尾都在堆里面
   - 输入参数排序，<= 起始位置的弹出

3. 矩形面积重合问题  **RectangleMax** ）O(N ^ 2 * logN)