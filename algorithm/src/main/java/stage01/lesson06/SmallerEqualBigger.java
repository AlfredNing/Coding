package stage01.lesson06;

/**
 * @ClassName SmallerEqualBigger
 * @Description 将单向链表按照某值划分成左边小、中间相等、右边大的形式
 * @Author Alfred.Ning
 * @Date 2022/4/16 16:25
 * @Version 1.0
 **/
public class SmallerEqualBigger {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 通过patittion 构建，荷兰国旗问题
    public static Node listPartitions(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        cur = head;
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur; // 填充数组
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);
        // 还原成之前链表
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        return nodeArr[0];
    }

    // 以val值换分成 <区域，= 区域, > 区域
    public static void arrPartition(Node[] nodes, int val) {
        int less = -1; // 左边界起始位置
        int idx = 0;
        int more = nodes.length;
        while (idx != more) {
            if (nodes[idx].value == val) {
                idx++;
            } else if (nodes[idx].value < val) {
                swap(nodes, idx++, ++less);
            } else {
                swap(nodes, idx, --more);
            }
        }
    }

    public static void swap(Node[] nodes, int i, int j) {
        Node node = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = node;
    }

    // 原链表上操作 分成小、中、大三部分，在把各个部分串起来
    public static Node listPartition2(Node head, int pivot) {
        Node sH = null; // 小于区域头指针
        Node sT = null; // 小于区域尾指针
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;
        Node next;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                // 属于小区域的
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        // 连接三部分，有可能没有某一部分
        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT; // 选择连接大于之值的一部分
        }
        if (eT != null) {
            eT.next = mH;
        }
        // 返回头节点
        return sH != null ? sH : (eH != null ? eH : mH);
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        // head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }
}
