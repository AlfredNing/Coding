package stage03.lesson08;

/**
 * 最大数组异或和
 *
 * @author Alfred.Ning
 * @since 2023年02月12日 14:23:00
 */
public class MaxEOR {

  public static int maxXorSubArray(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    // 生成一个eor数组，每个数组eor[i]含义：arr[0...i]的异或和
    int[] eor = new int[arr.length];
    eor[0] = arr[0]; // arr[0,0] ^ 0;
    for (int i = 1; i < arr.length; i++) {
      eor[i] = arr[i] ^ eor[i - 1];
    }
    int max = Integer.MIN_VALUE;
    for (int j = 0; j < arr.length; j++) {
      for (int i = 0; i <= j; i++) {
        // 这里的意思是 比如你要求 eor[i,j] = > eor[i-1] ^ eor[j]
        max = Math.max(max, i == 0 ? eor[j] : eor[j] ^ eor[i - 1]);
      }
    }
    return max;
  }

  // 前缀树解法 走到每一个节点，想法就是如何选择出最大的数，类似贪心
  public static int maxEorArray2(int[] array) {
    if (array == null || array.length == 0) {
      return 0;
    }
    int max = Integer.MIN_VALUE;
    int eor = 0;
    NumTrie numTrie = new NumTrie();
    numTrie.add(0); // 一个数也没有，异或为0
    for (int i = 0; i < array.length; i++) {
      eor ^= array[i];
      max = Math.max(max, numTrie.maxXor(eor));
      numTrie.add(eor);
    }
    return max;
  }

  public static void main(String[] args) {
  }
}

class NumTrie {

  // 头节点
  public Node head = new Node();

  public static class Node {

    public Node[] nexts = new Node[2];// 选择只有0和1
  }

  // 把某个数字添加到前缀树当中 这里的原理就是每次选择好自己同号符号位
  public void add(int nodeNum) {
    Node cur = head;
    for (int move = 31; move >= 0; move--) {
      // 高位到低位
      int path = ((nodeNum >> move) & 1);
      cur.nexts[path] =
          cur.nexts[path] == null ? new Node() : cur.nexts[path];
      cur = cur.nexts[path];
    }
  }

  // 之前已经收集了eor, 给定sum 返回和谁^ 最大的结果
  public int maxXor(int sum) {
    Node cur = head;
    int res = 0;
    for (int move = 31; move >= 0; move--) {
      int path = ((sum >> move) & 1);
      int best = move == 31 ? path : (path ^ 1);
      best = cur.nexts[best] != null ? best : best ^ 1;
      // path ^ best的异或结果
      res |= (path ^ best) << move;
      cur = cur.nexts[best];
    }
    return res;
  }
}
