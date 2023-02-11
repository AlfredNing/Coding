package stage01.lesson10;

import java.util.Stack;

/**
 * @ClassName ReverseStackUsingRecursive
 * @Description 逆序一个栈
 * @Author Alfred.Ning
 * @Date 2022/5/28 15:56
 * @Version 1.0
 **/
public class ReverseStackUsingRecursive {
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()){
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    // 函数作用 是返回让栈底元素消失，并返回栈底元素
    public static int f(Stack<Integer> stack) {
        Integer result = stack.pop();
        if (stack.isEmpty()){
            return result;
        }else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }
}
