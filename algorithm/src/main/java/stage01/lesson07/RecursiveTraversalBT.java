package stage01.lesson07;

/**
 * @ClassName RecursiveTraversalBT
 * @Description
 * @Author Alfred.Ning
 * @Date 2022/4/30 10:21
 * @Version 1.0 遍历输出打印二叉树，运用递归序
 **/
public class RecursiveTraversalBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void f(Node heade) {
        if (heade == null) {
            return;
        }
        // 1
        f(heade.left);
        // 2
        f(heade.right);
        // 3
    }

    // 先序遍历打印二叉树节点
    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    // 中序遍历打印二叉树节点
    public static void in(Node head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }
    // 后序遍历打印二叉树节点
    public static void pos(Node head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos(head);
        System.out.println("========");

    }
}
