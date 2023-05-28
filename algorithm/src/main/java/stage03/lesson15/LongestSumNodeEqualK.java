package stage03.lesson15;

import java.util.HashMap;

/**
 * 树 - 最长路径节点
 *
 * @author Alfred.Ning
 * @since 2023年05月28日 10:16:00
 */
public class LongestSumNodeEqualK {

  public static class Node {

    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
      this.value = value;
    }
  }

  public static int ans = 0; // 收集累加和为k，最长路径有多少节点

  public static int largest(Node head, int k) {
    ans = 0;
    // key为前缀和 ，该前缀和最早出现的层
    HashMap<Integer, Integer> sumMap = new HashMap<>();
    sumMap.put(0, -1);
    process(head, 0, 0, k, sumMap);
    return ans;

  }

  /*
    节点在level层，从头节点到x节点形成的累加和是preSum
    求出必须以X节点结尾，累加和是k的所有路径，含有最多的节点个数
   */
  private static void process(Node x, int level, int preSum, int k,
      HashMap<Integer, Integer> sumMap) {
    if (x != null) {
      int allSum = preSum + x.value;
      if (sumMap.containsKey(allSum - k)) {
        ans = Math.max(ans, level - sumMap.get(allSum - k));
      }
      if (!sumMap.containsKey(allSum)) {
        sumMap.put(allSum, level);
      }
      process(x.left, level + 1, allSum, k, sumMap);
      process(x.right, level + 1, allSum, k, sumMap);
      // 这里需要擦除下层的信息
      if (sumMap.get(allSum) == level) {
        sumMap.remove(allSum);
      }
    }
  }


}
