package stage03.lesson06;

/**
 * 搜索二叉树转双向链表
 *
 * @author Alfred.Ning
 */
public class BstToDoubleLinkedList {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        public Node start;
        public Node end;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    // 返回转化之后的头节点
    public static Node covert2(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).start;
    }

    private static Info process(Node x) {
        if (x == null) {
            return new Info(null, null);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        if (leftInfo.end != null) {
            leftInfo.end.right = x;
        }
        x.left = leftInfo.end;
        x.right = rightInfo.start;
        if (rightInfo.start != null) {
            rightInfo.start.left = x;
        }
        return new Info(
                leftInfo.start != null ? leftInfo.start : x,
                rightInfo.end != null ? rightInfo.end : x
        );
    }
}
