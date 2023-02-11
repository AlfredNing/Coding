package stage01.lesson02;

import java.util.Stack;

/**
 * @ClassName TwoStacksImplementQueue
 * @Description 两个栈实现队列
 * @Author Alfred.Ning
 * @Date 2022/4/3 22:16
 * @Version 1.0
 **/
public class TwoStacksImplementQueue {
    private static class TwoStacksQueue{
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStacksQueue(){
            stackPush = new Stack<>();
            stackPop = new Stack<>();
        }
        // 转移栈中元素
        private void pushToPop(){
            if(stackPop.isEmpty()){ // 只有当pop栈为空，全量写入
                while (!stackPush.isEmpty()){
                    stackPop.push(stackPush.pop());
                }
            }
        }
        public void add(int value){
            stackPush.push(value);
            pushToPop();
        }

        public int poll(){
            if(stackPop.isEmpty() && stackPush.isEmpty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.pop();
        }
        public int peek() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.peek();
        }
    }
    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }

}
