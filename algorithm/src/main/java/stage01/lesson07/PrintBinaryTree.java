package stage01.lesson07;

/**
 * @ClassName PrintBinaryTree
 * @Description 打印二叉树
 * @Author Alfred.Ning
 * @Date 2022/5/1 14:59
 * @Version 1.0
 **/
public class PrintBinaryTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void printTree(Node head) {
        System.out.println("Binary Tree: ");
        printInorder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInorder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        // 右 根 左
        printInorder(head.right, height + 1,"v", len);
        String val = to + head.value + to;
        int valM = val.length();
        int lenL  = (len - valM) / 2; // 左半部分求出
        int lenR = len - lenL - valM;
        val = getSpace(lenL)+val+getSpace(lenR);
        System.out.println(getSpace(height * len)+val);
        printInorder(head.left, height + 1,"^",len);
    }

    private static String getSpace(int num) {
        String space = " ";
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            sb.append(space);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(-222222222);
        head.right = new Node(3);
        head.left.left = new Node(Integer.MIN_VALUE);
        head.right.left = new Node(55555555);
        head.right.right = new Node(66);
        head.left.left.right = new Node(777);
        printTree(head);

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.left.left.right = new Node(7);
        printTree(head);

        head = new Node(1);
        head.left = new Node(1);
        head.right = new Node(1);
        head.left.left = new Node(1);
        head.right.left = new Node(1);
        head.right.right = new Node(1);
        head.left.left.right = new Node(1);
        printTree(head);

    }

}
