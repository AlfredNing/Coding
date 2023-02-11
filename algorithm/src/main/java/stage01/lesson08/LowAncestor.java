package stage01.lesson08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @ClassName LowAncestor
 * @Description 两节点最低公共祖先
 * @Author Alfred.Ning
 * @Date 2022/5/8 19:57
 * @Version 1.0
 **/
public class LowAncestor {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 方法1
    public static Node lowestAncestor1(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        // 节点 -- 父节点保存
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            // 把经过o1 的节点保存下来
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    private static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }

    }

    // 方法2
    public static Node lowestAncestor2(Node head, Node o1, Node o2) {
        return process(head, o1, o2).ans;
    }

    public static class Info {
        public boolean findA;
        public boolean findB;
        public Node ans;

        public Info(boolean fA, boolean fB, Node an) {
            findA = fA;
            findB = fB;
            ans = an;
        }
    }

    private static Info process(Node x, Node o1, Node o2) {
        if (x == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(x.left, o1, o2);
        Info rightInfo = process(x.right, o1, o2);
        boolean findo1 = (x == o1) || leftInfo.findA || rightInfo.findA;
        boolean findo2 = (x == o2) || leftInfo.findB || rightInfo.findB;
        Node ans = null;
        /**
         * o1 o2 交汇点
         * 1. 在左树提前交汇
         * 2. 在右树提前交汇
         * 3. 左右树都没有，但o1, o2 已经全了，在x交汇
         */
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        }
        if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        }
        if (ans == null) {
            if (findo1 && findo2) {
                ans = x;
            }
        }
        return new Info(findo1, findo2, ans);
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

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
