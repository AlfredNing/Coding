package stage02.lesson08;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** 匹配文章的候选字符串 */
public class AcWord {
  // 前缀树节点
  public static class Node {
    public String end; // 字符串的结尾
    public boolean endUse; // end变量不为空，endUser才有意义，改字符串是否加入过答案
    public Node fail;
    public Node[] nexts;

    public Node() {
      endUse = false;
      end = null;
      fail = null;
      nexts = new Node[26];
    }
  }

  public static class ACAutomation {
    private Node root;

    public ACAutomation() {
      root = new Node();
    }

    public void insert(String s) {
      char[] str = s.toCharArray();
      Node cur = root;
      int index = 0;
      for (int i = 0; i < str.length; i++) {
        index = str[i] - 'a';
        if (cur.nexts[index] == null) {
          cur.nexts[index] = new Node();
        }
        cur = cur.nexts[index];
      }
      cur.end = s;
    }

    // 设置fail指针
    public void build() {
      LinkedList<Node> queue = new LinkedList<>();
      queue.add(root);
      Node cur = null;
      Node cFail = null;
      while (!queue.isEmpty()) {
        // cur是父亲节点
        cur = queue.poll();
        for (int i = 0; i < 26; i++) {
          if (cur.nexts[i] != null) { // 有效路
            cur.nexts[i].fail = root;
            cFail = cur.fail;
            while (cFail != null) {
              if (cFail.nexts[i] != null) {
                cur.nexts[i].fail = cFail.nexts[i];
              }
              cFail = cFail.fail;
            }
            queue.add(cur.nexts[i]);
          }
        }
      }
    }

    public List<String> containsWords(String content) {
      char[] str = content.toCharArray();
      Node cur = root;
      Node follow = null;
      int index = 0;
      ArrayList<String> ans = new ArrayList<>();
      for (int i = 0; i < str.length; i++) {
        index = str[i] - 'a';
        while (cur.nexts[index] == null && cur != root) { // fail指针跳转
          cur = cur.fail;
        }
        cur = cur.nexts[index] != null ? cur.nexts[index] : root;
        follow = cur;
        while (follow != root) {
          if (follow.endUse) {
            break;
          }
          if (follow.end != null) {
            ans.add(follow.end);
            follow.endUse = true;
          }
          follow = follow.fail;
        }
      }

      return ans;
    }
  }

  public static void main(String[] args) {
    ACAutomation ac = new ACAutomation();
    ac.insert("dhe");
    ac.insert("he");
    ac.insert("abcdheks");
    ac.build();

    List<String> contains = ac.containsWords("abcdhekdfsldsdfsodhedhequer");
    System.out.println(contains);
  }
}
