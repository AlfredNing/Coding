package stage03.lesson13;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 搜索单词
 *
 * @author Alfred.Ning
 * @since 2023年04月29日 14:32:00
 */
public class WordSearch {

  // 构件前缀树
  public static class TrieNode {

    public TrieNode[] nexts;
    public int pass;// 该节点所经过的数量
    public int end; // 以该位置结尾的数量

    public TrieNode() {
      nexts = new TrieNode[26];
      pass = 0;
      end = 0;
    }
  }

  public static List<String> findWords(char[][] boards, String[] words) {
    TrieNode head = new TrieNode();
    HashSet<String> set = new HashSet<>();
    // 构建前缀树
    for (String word : words) {
      if (!set.contains(word)) {
        fillWord(head, word);
        set.add(word);
      }
    }

    LinkedList<String> res = new LinkedList<>();
    LinkedList<Character> path = new LinkedList<>();
    for (int row = 0; row < boards.length; row++) {
      for (int col = 0; col < boards[0].length; col++) {
        //  从每一个位置出发收集答案
        process(boards, row, col, path, head, res);
      }
    }
    return res;
  }

  // 以boards[row][col]之前走过的路径都在path上面，从board[row][col]出发
  // 搞定多少单词
  private static int process(char[][] boards, int row, int col, LinkedList<Character> path,
      TrieNode cur, LinkedList<String> res) {
    char cha = boards[row][col];
    if (cha == 0) {
      return 0;// 走到回头路
    }
    int index = cha - 'a';
    if (cur.nexts[index] == null || cur.nexts[index].pass == 0) { // 下面没有路了
      return 0;
    }
    // 该节点没有走过
    cur = cur.nexts[index];
    path.addLast(cha);
    int fix = 0; // 从 row col出发一共搞定了多少答案
    if (cur.end > 0) { // 找到一个单词了
      res.add(generatePath(path));
      cur.end--;
      fix++;
    }
    // 上下左右四个方向尝试
    boards[row][col] = 0; // 先标记
    if (row > 0) {
      fix += process(boards, row - 1, col, path, cur, res);
    }
    if (row < boards.length - 1) {
      fix += process(boards, row + 1, col, path, cur, res);
    }
    if (col > 0) {
      fix += process(boards, row, col - 1, path, cur, res);
    }
    if (col < boards[0].length - 1) {
      fix += process(boards, row, col + 1, path, cur, res);
    }
    // 恢复
    boards[row][col] = cha;
    path.pollLast();
    cur.pass -= fix;
    return fix;
  }

  private static String generatePath(LinkedList<Character> path) {
    char[] str = new char[path.size()];
    int index = 0;
    for (Character character : path) {
      str[index++] = character;
    }
    return String.valueOf(str);
  }

  private static void fillWord(TrieNode head, String word) {
    head.pass++;
    char[] chars = word.toCharArray();
    TrieNode node = head;
    int index = 0;
    for (int i = 0; i < chars.length; i++) {
      index = chars[i] - 'a';
      if (node.nexts[index] == null) {
        node.nexts[index] = new TrieNode();
      }
      node = node.nexts[index];
      node.pass++;
    }
    node.end++;
  }
}
