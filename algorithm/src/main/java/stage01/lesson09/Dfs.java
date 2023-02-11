package stage01.lesson09;

import java.util.HashSet;
import java.util.Stack;

/**
 * @ClassName Dfs
 * @Description 图的深度优先遍历
 * @Author Alfred.Ning
 * @Date 2022/5/22 14:11
 * @Version 1.0
 **/
public class Dfs {
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> sets = new HashSet<>();
        stack.push(node);
        sets.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                // 这里实际上只选择了一个顶点
                if (!sets.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    sets.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
