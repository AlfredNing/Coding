package prod.hw;


import java.util.Scanner;

/**
 * @ClassName HJ46截取字符串
 * @Author 宁强-34436
 * @Date 2022/5/25 14:34
 * @Email qiang.ning@going-link.com
 * @Descirption
 **/
public class HJ46截取字符串 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      String inputStr = sc.next();
      int subStrLen = sc.nextInt();
      subStr2(inputStr, subStrLen);
    }
  }

  private static void subStr2(String inputStr, int subStrLen) {
    if (subStrLen >= inputStr.length()) {
      System.out.println(inputStr);
      return;
    }
    StringBuilder sb = new StringBuilder();
    char[] strs = inputStr.toCharArray();
    int temp = 0;
    for (int i = 0; i < strs.length; i++) {
      if (strs[i] > 128) {
        temp += 2;
        if (temp <= subStrLen) {
          sb.append(strs[i]);
        }
      } else {
        temp += 1;
        if (temp <= subStrLen) {
          sb.append(strs[i]);
        }
      }
    }
    System.out.println(sb.toString());
  }

  private static void subStr1(String inputStr, int subStrLen) {
    if (subStrLen >= inputStr.length()) {
      System.out.println(inputStr);
      return;
    }
    System.out.println(inputStr.substring(0, subStrLen));
  }
}
