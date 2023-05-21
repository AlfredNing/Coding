package stage03.lesson14;

import java.util.HashSet;

/**
 * 字符串最小方法数
 *
 * @author Alfred.Ning
 * @since 2023年05月21日 14:57:00
 */
public class WordBreak {
  // 暴力方法

  public static int ways1(String str, String[] arr) {
    HashSet<String> hashSet = new HashSet<>();
    for (String s : arr) {
      hashSet.add(s);
    }

    return process(str, 0, hashSet);
  }

  // 从str[i....]开始，最大方法数
  private static int process(String str, int i, HashSet<String> set) {
    if (i == str.length()) {
      // 到达结尾
      return 1;
    }
    int ways = 0;
    // 判断前缀串
    for (int end = i; end < str.length(); end++) {
      String pre = str.substring(i, end);
      if (set.contains(pre)) {
        ways += process(str, end + 1, set);
      }
    }
    return ways;
  }

  // 构建前缀树
  public static class Node {

    public boolean end;
    public Node[] nexts;

    public Node() {
      end = false;
      nexts = new Node[26];
    }
  }

  public static int way2(String str, String[] arr) {
    if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
      return 0;
    }
    Node root = new Node();
    for (String s : arr) {
      char[] chars = s.toCharArray();
      Node node = root;
      int index = 0;
      for (int i = 0; i < chars.length; i++) {
        index = chars[i] - 'a';
        if (node.nexts[index] == null) {
          node.nexts[index] = new Node();
        }
        node = node.nexts[index];
      }
      node.end = true;
    }
    return g(str.toCharArray(), root, 0);
  }

  private static int g(char[] str, Node root, int i) {
    if (i == str.length) {
      return 1;
    }
    int ways = 0;
    Node cur = root;
    for (int end = 0; end < str.length; end++) {
      int path = str[end] - 'a';
      if (cur.nexts[path] == null) {
        break;
      }
      cur = cur.nexts[path];
      if (cur.end) {
        ways += g(str, root, end + 1);
      }
    }
    return ways;
  }

  // 从左到右的尝试模型
  public static int dpWay(String str, String[] arr) {
    Node root = new Node();
    for (String s : arr) {
      char[] chars = s.toCharArray();
      Node node = root;
      int index = 0;
      for (int i = 0; i < chars.length; i++) {
        index = chars[i] - 'a';
        if (node.nexts[index] == null) {
          node.nexts[index] = new Node();
        }
        node = node.nexts[index];
      }
      node.end = true;
    }
    int n = str.length();
    char[] chars = str.toCharArray();
    int[] dp = new int[n + 1];
    dp[n] = 1;
    for (int i = n - 1; i >= 0; i--) {
      Node cur = root;
      for (int end = i; end < n; end++) {
        cur = cur.nexts[chars[end] - 'a'];
        if (cur == null) {
          break;
        }
        if(cur.end){
          dp[i] += dp[end + 1];
        }
      }
    }
    return dp[0];
  }

}
