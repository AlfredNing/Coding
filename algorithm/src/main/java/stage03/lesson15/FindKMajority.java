package stage03.lesson15;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

/**
 * @author Alfred.Ning
 * @since 2023年05月28日 11:08:00
 */
public class FindKMajority {

  public static void printHalfMajority(int[] arr) {
    // 一次删除两个不同的数,这里太牛了，不用关心数字的值是什么
    int cand = 0;
    int hp = 0;
    for (int i = 0; i < arr.length; i++) {
      if (hp == 0) {
        // 选出一个候选者
        cand = arr[i];
        hp = 1;
      } else if (arr[i] == cand) {
        // 增加血量
        hp++;
      } else {
        // 出现不同的数字，降低血量
        hp--;
      }
    }
    if (hp == 0) {
      System.out.println("no such number");
      return;
    }
    // 最后需要验证次数
    hp = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == cand) {
        hp++;
      }
    }
    if (hp > arr.length / 2) {
      System.out.println(cand);
    } else {
      System.out.println("no such number");
    }
  }

  public static void printKMajor(int[] arr, int k) {
    if (k < 2) {
      System.out.println("the value of K is invalid.");
      return;
    }
    HashMap<Integer, Integer> cands = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      if (cands.containsKey(arr[i])) {
        cands.put(arr[i], cands.get(arr[i]) + 1);
      } else {
        if (cands.size() == k - 1) {
          // 所有候选人血量减1
          allCandsMinusOne(cands);
        } else {
          cands.put(arr[i], 1);
        }
      }
    }

    // 对候选人进行遍历
    HashMap<Integer, Integer> reals = getReals(arr, cands);
    boolean hasPrint = false;
    for (Entry<Integer, Integer> set : cands.entrySet()) {
      Integer key = set.getKey();
      if (reals.get(key) > arr.length / k) {
        hasPrint = true;
        System.out.println(key + "");
      }
    }
    System.out.println(hasPrint ? "" : "no such number");
  }

  private static HashMap<Integer, Integer> getReals(int[] arr, HashMap<Integer, Integer> cands) {
    HashMap<Integer, Integer> reals = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      int curNum = arr[i];
      if (cands.containsKey(curNum)) {
        if (reals.containsKey(curNum)) {
          reals.put(curNum, reals.get(curNum) + 1);
        } else {
          reals.put(curNum, 1);
        }
      }
    }
    return reals;
  }

  private static void allCandsMinusOne(HashMap<Integer, Integer> cands) {
    LinkedList<Integer> removeList = new LinkedList<>();
    for (Entry<Integer, Integer> entry : cands.entrySet()) {
      Integer key = entry.getKey();
      Integer value = entry.getValue();
      if (value == 1) {
        removeList.add(key);
      }
      cands.put(key, value - 1);
    }
    for (Integer removeKey : removeList) {
      cands.remove(removeKey);
    }
  }
}
