package stage01.lesson06;

import java.util.ArrayList;

/**
 * @ClassName LinkedListMid
 * @Description 快慢指针求中点
 * @Author Alfred.Ning
 * @Date 2022/4/16 14:35
 * @Version 1.0
 **/
public class LinkedListMid {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // 输入链表头结点，奇数长度返回中点，偶数长度返回上中点
    public static Node midOrUpMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) { // || 运算具有短路效果，这里出现两个节点
            return head;
        }
        // 链表有3个或者以上
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 输入链表头结点，奇数长度返回中点，偶数长度返回上中点 -- 测试
    public static Node right1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> nodes = new ArrayList<>();
        while (cur != null) {
            nodes.add(cur);
            cur = cur.next;
        }
        return nodes.get((nodes.size() - 1) / 2);
    }

    // 输入链表头结点，奇数长度返回中点，偶数长度返回下中点
    public static Node midOrDownMidNode(Node head) {
        if (head == null || head.next == null) { // 保证一个节点
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 输入链表头结点，奇数长度返回中点，偶数长度返回下中点 -- test
    public static Node right2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get(arr.size() / 2);
    }

    // 输入链表头结点，奇数长度返回中点前一个，偶数长度返回上中点前一个
    public static Node midOrUpMidPreNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) { // 前两个元素
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 输入链表头结点，奇数长度返回中点前一个，偶数长度返回上中点前一个 -- test
    public static Node right3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 3) / 2);
    }

    // 输入链表头结点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    public static Node midOrDownMidPreNode(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        if (head.next.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node right4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 2) / 2);
    }

    public static void main(String[] args) {
        Node test = null;
        test = new Node(0);
        test.next = new Node(1);
        test.next.next = new Node(2);
        test.next.next.next = new Node(3);
        test.next.next.next.next = new Node(4);
        test.next.next.next.next.next = new Node(5);
        test.next.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next.next = new Node(7);
        test.next.next.next.next.next.next.next.next = new Node(8);

        Node ans1 = null;
        Node ans2 = null;

        ans1 = midOrUpMidNode(test);
        ans2 = right1(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = midOrDownMidNode(test);
        ans2 = right2(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = midOrUpMidPreNode(test);
        ans2 = right3(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = midOrDownMidPreNode(test);
        ans2 = right4(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

    }

}
