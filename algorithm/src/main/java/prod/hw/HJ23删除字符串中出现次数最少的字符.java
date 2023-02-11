package prod.hw;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @ClassName HJ23删除字符串中出现次数最少的字符
 * @Author 宁强-34436
 * @Date 2022/5/24 15:30
 * @Email qiang.ning@going-link.com
 * @Descirption
 **/
public class HJ23删除字符串中出现次数最少的字符 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String inputStr = sc.next();
    HashMap<Character, Integer> countMap = new HashMap<>();
    for (char c : inputStr.toCharArray()) {
      countMap.put(c, countMap.getOrDefault(c, 1) + 1);
    }
    int minValue = Integer.MAX_VALUE;
    for (Integer value : countMap.values()) {
      minValue = Math.min(minValue, value);
    }
    StringBuilder sb = new StringBuilder();
    for (char c : inputStr.toCharArray()) {
      if (countMap.get(c) != minValue){
        sb.append(c);
      }
    }
    System.out.println(sb.toString());

  }
  static void method1(){
    Scanner sc = new Scanner(System.in);
    String inputStr = sc.next();
    TreeMap<Character, Integer> countMap = new TreeMap<>();
    for (char c : inputStr.toCharArray()) {
      countMap.put(c, countMap.getOrDefault(c, 1) + 1);
    }
    ArrayList<Character> list = new ArrayList<>();
    Integer minValue = countMap.values().stream().sorted().findFirst().get();
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
      if (entry.getValue().equals(minValue)) {
        list.add(entry.getKey());
      }
    }
    for (char c : inputStr.toCharArray()) {
      if (list.contains(c)) {
        continue;
      }
      sb.append(c);
    }
    System.out.println(sb.toString());
  }
}
