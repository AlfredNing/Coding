package prod.hw;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @ClassName HJ5进制转换
 * @Author 宁强-34436
 * @Date 2022/5/23 13:42
 * @Email qiang.ning@going-link.com
 * @Descirption
 **/
public class HJ5进制转换 {
  private static final int BASE = 16;
  private static Map<Character, Integer> map = new HashMap<Character, Integer>() {{
    put('0', 0);
    put('1', 1);
    put('2', 2);
    put('3', 3);
    put('4', 4);
    put('5', 5);
    put('6', 6);
    put('7', 7);
    put('8', 8);
    put('9', 9);
    put('A', 10);
    put('B', 11);
    put('C', 12);
    put('D', 13);
    put('E', 14);
    put('F', 15);
    put('a', 10);
    put('b', 11);
    put('c', 12);
    put('d', 13);
    put('e', 14);
    put('f', 15);
  }};

  public static void main(String[] args) {
    //useMap();
    formula();
  }

  static void formula() {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      String inputStr = sc.next();
      int res = 0;
      for (int i = 0; i < inputStr.length() - 2; i++) {
        char tc = inputStr.charAt(i + 2);
        int temp = 0;
        if (tc >= '0' && tc <= '9') {
          temp = tc - '0';
        } else if (tc >= 'A' && tc <= 'F') {
          temp = tc - 'A' + 10;
        } else if (tc >= 'a' && tc <= 'f') {
          temp = tc - 'A' + 10;
        }
        res += temp * Math.pow(16, inputStr.length() - i - 3);
      }
      System.out.println(res);
    }
  }


  private static int getRes(String str) {
    int res = 0;
    for (char c : str.toCharArray()) {
      res = res * BASE + map.get(c);
    }
    return res;
  }

  static void useMap() {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      String inputStr = sc.next();
      int res = getRes(inputStr.substring(2));
      System.out.println(res);
    }
  }

  static void apiUser() {
    Scanner sc = new Scanner(System.in);
    String inputStr = sc.next();
    int res = Integer.parseInt(inputStr.substring(2), 16);
    System.out.println(res);
  }
}
