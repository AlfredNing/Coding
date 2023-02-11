package stage03.lesson02;

/**
 * @author Alfred.Ning
 */
public class MaxSumTree {
  class Node {
    public Node left;
    public Node right;
    public int value;
  }

  public static int maxSum = Integer.MIN_VALUE;
  public static int next = Integer.MIN_VALUE;
  // 1 - 1
  public static int maxPath1(Node head) {
    maxSum = Integer.MIN_VALUE;
    p(head, 0);
    return maxSum;
  }

  private static void p(Node x, int pre) {
    if (x.left == null && x.right == null) {
      maxSum = Math.max(maxSum, pre + x.value);
    }
    if (x.left != null) {
      p(x.left, pre + x.value);
    }
    if (x.right != null) {
      p(x.right, pre + x.value);
    }
  }

  // 1 - 2
  private static int maxSumPath1(Node head) {
    // 这里只能以叶子节点作为baseCase
    if (head == null) {
      return 0;
    }
    return process2(head);
  }

  /**
   * 给定一个节点从x出发，求出到叶子节点得最大值
   *
   * @param x
   * @return
   */
  private static int process2(Node x) {
    if (x.left == null && x.right == null) {
      return x.value;
    }
    if (x.left != null) {
      next = process2(x.left);
    }
    if (x.right != null) {
      next = Math.max(next, process2(x.right));
    }
    return next;
  }

  // 2.
  // 与 x 无关 1. 左树上上的最大路径和 2. 右树上的最大路径和
  // 与 x有关， 1. x自己， 2. x往左走，3. x往右走
  public static int maxPath2(Node head) {
    if (head == null) {
      return 0;
    }
    return f2(head).allTreeMaxSum;
  }

  private static Info f2(Node x) {
    if (x == null) {
      return null;
    }
    Info leftInfo = f2(x.left);
    Info rightInfo = f2(x.right);
    int p1 = Integer.MIN_VALUE;
    if (leftInfo != null) {
      p1 = leftInfo.allTreeMaxSum;
    }
    int p2 = Integer.MIN_VALUE;
    if (rightInfo != null) {
      p2 = rightInfo.allTreeMaxSum;
    }
    int p3 = x.value;
    int p4 = Integer.MIN_VALUE;
    if (leftInfo != null) {
      p4 = x.value + leftInfo.fromHeadMaxSum;
    }
    int p5 = Integer.MIN_VALUE;
    if (rightInfo != null) {
      p5 = x.value + rightInfo.fromHeadMaxSum;
    }
    int allTreeMaxSum = Math.max(Math.max(Math.max(p1, p2), p3), Math.max(p4, p5));
    int fromHeadMaxSum = Math.max(Math.max(p3, p4), p5);
    return new Info(allTreeMaxSum, fromHeadMaxSum);
  }

  static class Info {
    public int allTreeMaxSum;
    public int fromHeadMaxSum;

    public Info(int allTreeMaxSum, int fromHeadMaxSum) {
      this.allTreeMaxSum = allTreeMaxSum;
      this.fromHeadMaxSum = fromHeadMaxSum;
    }
  }

  // 3  包含既往右 右往左走
  public static int maxPath3(Node head) {
    if (head == null) {
      return 0;
    }
    return f3(head).allTreeMaxSum;
  }

  private static Info f3(Node x) {
    if (x == null) {
      return null;
    }
    Info leftInfo = f3(x.left);
    Info rightInfo = f3(x.right);
    int p1 = Integer.MIN_VALUE;
    if (leftInfo != null) {
      p1 = leftInfo.allTreeMaxSum;
    }
    int p2 = Integer.MIN_VALUE;
    if (rightInfo != null) {
      p2 = rightInfo.allTreeMaxSum;
    }
    int p3 = x.value;
    int p4 = Integer.MIN_VALUE;
    if (leftInfo != null) {
      p4 = x.value + leftInfo.fromHeadMaxSum;
    }
    int p5 = Integer.MIN_VALUE;
    if (rightInfo != null) {
      p5 = x.value + rightInfo.fromHeadMaxSum;
    }
    int p6 = Integer.MIN_VALUE;
    if (leftInfo != null && rightInfo != null) {
      p6 = x.value + leftInfo.fromHeadMaxSum + rightInfo.fromHeadMaxSum;
    }
    int allTreeMaxSum = Math.max(Math.max(Math.max(p1, p2), p3), Math.max(Math.max(p4, p5), p6));
    int fromHeadMaxSum = Math.max(Math.max(p3, p4), p5);
    return new Info(allTreeMaxSum, fromHeadMaxSum);
  }
}
