package stage01.lesson09;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @ClassName Bfs
 * @Description 图的宽度优先遍历
 * @Author Alfred.Ning
 * @Date 2022/5/22 13:49
 * @Version 1.0
 **/
public class Bfs {
    // 从node开始进行宽度遍历
    public static void bfs(Node start) {
        if (start == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if(!set.contains(next)){
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
