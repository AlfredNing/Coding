package stage01.lesson07;

import java.util.Stack;

/**
 * @ClassName UnRecursiveTraversalBT
 * @Description
 * @Author Alfred.Ning
 * @Date 2022/4/30 10:28
 * @Version 1.0  非递归遍历输出二叉树
 **/
public class UnRecursiveTraversalBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 非递归实现先序遍历 - 栈模拟
    public static void pre(Node head) {
        System.out.println("pre-order...");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.value + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    // 非递归实现中序遍历
    public static void in(Node cur) {
        System.out.println("in-order: ");
        if (cur != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || cur != null) {
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    cur = stack.pop();
                    System.out.println(cur.value + " ");
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }

    // 后序遍历-第一种 形成头 右 左，然后逆序
    public static void pos1(Node head) {
        System.out.println("post-order-1: ");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            Stack<Node> helper = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                helper.push(head);
                if (head.left != null) {
                    stack.push(head.left);
                }
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
            while (!helper.isEmpty()) {
                System.out.println(helper.pop().value + " ");
            }
        }
        System.out.println();
    }

    // 后序遍历，利用有限变量
    public static void pos2(Node head) {
        System.out.println("pos2-order");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && head != c.left && head != c.right) {
                    // 说明都没有处理过
                    stack.push(c.left);
                } else if (c.right != null && head != c.right) {
                    // 说明左节点处理过
                    stack.push(c.right);
                } else {
                    // 左右都已经处理完毕
                    System.out.println(stack.pop().value + " ");
                    head = c;
                }
            }
        }
        System.out.println();
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
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
    }
}
