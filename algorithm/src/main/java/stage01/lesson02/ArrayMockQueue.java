package stage01.lesson02;

/**
 * @ClassName ArrayMockQueue
 * @Description 用数组模拟队列, 类似与环形缓冲区； 数组模拟栈容易实现
 * @Author Alfred.Ning
 * @Date 2022/4/3 21:34
 * @Version 1.0
 **/
public class ArrayMockQueue {
    public static class MyQueue {
        private int[] arr;
        private int pushi; // end
        private int polli; // begin
        private int size; // 真实存储元素数量
        private final int limit; // 数组长度

        public MyQueue(int limit) {
            arr = new int[limit];
            pushi = 0;
            polli = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("Queue is full");
            }
            size++;
            arr[pushi] = value;
            pushi = nextIndex(pushi);
        }

        public int poll() {
            if(size == 0){
                throw new RuntimeException("Queue is empty");
            }
            size--;
            int ans = arr[polli];
            polli = nextIndex(polli);
            return ans;
        }

        private int nextIndex(int i) {
            // 根据现有位置i 返回下一个位置
            return i < limit - 1 ? i + 1 : 0;
        }

    }


}
