package stage01.lesson02;

/**
 * @ClassName DeleteGivenValue
 * @Description 删掉单链表中给定的值
 * @Author Alfred.Ning
 * @Date 2022/4/3 20:53
 * @Version 1.0
 **/
public class DeleteGivenValue {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node removeGivenValue(Node head, int num) {
        while (head != null) {
            // 这里有可能出现 单链表前面是 要删除的
            if (head.value != num) {
                // 找到第一个不是要 删除的位置
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

}
