package stage03.lesson11;

import java.util.HashMap;

/**
 * 消息打印
 *
 * @author Alfred.Ning
 * @since 2023年04月02日 15:53:00
 */
public class ReceiveAndPrintOrderLine {

  public static class Node {

    public String info;
    public Node next;

    public Node(String info) {
      this.info = info;
    }
  }

  public static class MessageBox {

    private HashMap<Integer, Node> headMap;
    private HashMap<Integer, Node> tailMap;
    private int waitPoint;

    public MessageBox() {
      this.headMap = new HashMap<>();
      this.tailMap = new HashMap<>();
      // 初始1
      this.waitPoint = 1;
    }

    public void receive(int num, String info) {
      if (num < 1) {
        return;
      }
      Node cur = new Node(info);
      headMap.put(num, cur);
      tailMap.put(num, cur);

      if (tailMap.containsKey(num - 1)) {
        // 连接尾部分
        tailMap.get(num - 1).next = cur;
        tailMap.remove(num - 1);
        headMap.remove(num);
      }
      if (headMap.containsKey(num + 1)) {
        cur.next = headMap.get(num + 1).next;
        headMap.remove(num + 1);
        tailMap.remove(num);
      }

      if (num == waitPoint) {
        print();
      }
    }

    private void print() {
      Node node = headMap.get(waitPoint);
      headMap.remove(waitPoint);
      while (node != null) {
        System.out.println(node.info + " ");
        node = node.next;
        waitPoint++;
      }
      tailMap.remove(waitPoint - 1);
      System.out.println();
    }
  }
}

