package stage01.lesson07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName TreeMaxWidth
 * @Description 层序遍历-用变量实现某求二叉树的最大宽度
 * @Author Alfred.Ning
 * @Date 2022/4/30 14:27
 * @Version 1.0
 **/
public class TreeMaxWidth {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 用map实现
    public static int maxWithUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        int res = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // node, node所在层
        HashMap<Node, Integer> map = new HashMap<>();
        map.put(head, 1);
        // 当前正在统计那一层
        int curLevel = 1;
        // 当前curLevel层，宽度是多少，出来++
        int curLevelNodes = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            Integer curNodeLevel = map.get(cur);
            if (cur.left != null) {
                queue.add(cur.left);
                map.put(cur.left, curNodeLevel + 1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                map.put(cur.right, curNodeLevel + 1);
            }
            if (curLevel == curNodeLevel) {
                curLevelNodes++;
            } else {
                // 新层开始，更新结果
                res = Math.max(res, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        res = Math.max(res, curLevelNodes);
        return res;
    }

    // 不用map实现
    public static int maxWithNoMap(Node head) {
        if (head == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // 当前层最右节点
        Node curEnd = head;
        // 下一层最右节点
        Node nextEnd = null;
        // 当前层节点数
        int curLevelNodes = 0;
        int res = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd) {
                res = Math.max(res, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return res;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWithUseMap(head) != maxWithNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }


}
