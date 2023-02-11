package stage01.lesson07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @ClassName SerializeAndReConstructTree
 * @Description 序列化及反序列化
 * @Author Alfred.Ning
 * @Date 2022/4/30 15:12
 * @Version 1.0
 **/
public class SerializeAndReConstructTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 先序序列化
    public static Queue<String> preSerial(Node head) {
        Queue<String> res = new LinkedList<String>();
        pres(head, res);
        return res;
    }

    public static void pres(Node head, Queue<String> res) {
        if (head == null) {
            res.add(null);
        } else {
            res.add(String.valueOf(head.value));
            pres(head.left, res);
            pres(head.right, res);
        }
    }

    // 后序序列化
    public static Queue<String> posSerial(Node head) {
        Queue<String> res = new LinkedList<String>();
        pos(head, res);
        return res;
    }

    public static void pos(Node head, Queue<String> res) {
        if (head == null) {
            res.add(null);
        } else {
            pos(head.left, res);
            pos(head.right, res);
            res.add(String.valueOf(head.value));
        }
    }

    // 前序反序列化
    public static Node buildByPreQueue(Queue<String> preList) {
        if (preList == null || preList.size() == 0) {
            return null;
        }
        return preb(preList);
    }

    public static Node preb(Queue<String> preList) {
        String nodeVal = preList.poll();
        if (nodeVal == null) {
            return null;
        }
        Node head = new Node(Integer.parseInt(nodeVal));
        head.left = preb(preList);
        head.right = preb(preList);
        return head;
    }

    // 后序反序列化
    public static Node buildByPosQueue(Queue<String> posList) {
        if (posList == null || posList.size() == 0) {
            return null;
        }
        // 左 右 中 =》 中右左
        Stack<String> stack = new Stack<>();
        while (!posList.isEmpty()) {
            stack.push(posList.poll());
        }
        return posb(stack);
    }

    public static Node posb(Stack<String> stack) {
        String value = stack.pop();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.parseInt(value));
        head.right = posb(stack);
        head.left = posb(stack);
        return head;
    }

    // 层序遍历序列化
    public static Queue<String> levelSerial(Node head) {
        Queue<String> res = new LinkedList<String>();
        if (head == null) {
            res.add(null);
        } else {
            res.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(head);
            while (!queue.isEmpty()) {
                head = queue.poll();
                if (head.left != null) {
                    res.add(String.valueOf(head.left.value));
                    queue.add(head.left);
                } else {
                    res.add(null);
                }
                if (head.right != null) {
                    res.add(String.valueOf(head.right.value));
                    queue.add(head.right);
                } else {
                    res.add(null);
                }

            }
        }
        return res;
    }

    public static Node buildByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        Node head = generateNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.add(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null ) {
                queue.add(node.right);
            }
        }
        return head;
    }

    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.parseInt(val));
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
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = preSerial(head);
            Queue<String> pos = posSerial(head);
            Queue<String> level = levelSerial(head);
            Node preBuild = buildByPreQueue(pre);
            Node posBuild = buildByPosQueue(pos);
            Node levelBuild = buildByLevelQueue(level);
            if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }
}
