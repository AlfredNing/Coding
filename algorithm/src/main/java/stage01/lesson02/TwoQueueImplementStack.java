package stage01.lesson02;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName TwoQueueImplementStack
 * @Description 两个队列实现栈
 * @Author Alfred.Ning
 * @Date 2022/4/4 7:25
 * @Version 1.0
 **/
public class TwoQueueImplementStack<T> {
    private Queue<T> queue;
    private Queue<T> help;

    public TwoQueueImplementStack() {
        queue = new LinkedList<>();
        help = new LinkedList<>();
    }

    public void push(T value) {
        queue.offer(value);
    }

    public T poll() {
        while (queue.size() > 1) {
            help.offer(queue.poll());
        }
        T ans = queue.poll();
        Queue<T> tmp = queue                                                                                            ;
        queue = help;
        help = tmp;
        return ans;
    }

    public T peek() {
        while (queue.size() > 1) {
            help.offer(queue.poll());
        }
        T ans = queue.poll();
        help.offer(ans);
        Queue<T> tmp = queue;
        queue = help;
        help = tmp;
        return ans;

    }
}
