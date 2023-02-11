package prod.hw;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @ClassName HJ20密码验证合格程序
 * @Author 宁强-34436
 * @Date 2022/5/24 14:34
 * @Email qiang.ning@going-link.com
 * @Descirption
 **/
public class HJ20密码验证合格程序 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      String str = sc.next();
      // 长度合法
      if (str.length() <= 8) {
        System.out.println("NG");
        continue;
      }
      //
      if (getMatchStr(str)) {
        System.out.println("NG");
        continue;
      }
      if (getMatchStRepeat(str, 0, 3)) {
        System.out.println("NG");
        continue;
      }
      System.out.println("ok");
    }
  }

  //不能有长度大于2的包含公共元素的子串重复
  private static boolean getMatchStRepeat(String str, int l, int r) {
    if (r >= str.length()) {
      return false;
    }
    if (str.substring(r).contains(str.substring(l,r))){
      return true;
    }else {
      return getMatchStRepeat(str, l + 1, r + 1);
    }
  }

  //包括大小写字母.数字.其它符号,以上四种至少三种
  private static boolean getMatchStr(String str) {
    int count = 0;
    Pattern p1 = Pattern.compile("[A-Z]");
    Pattern p2 = Pattern.compile("[a-z]");
    Pattern p3 = Pattern.compile("[0-9]");
    Pattern p4 = Pattern.compile("[^a-zA-Z0-9]");
    if (p1.matcher(str).find()) {
      count++;
    }
    if (p2.matcher(str).find()) {
      count++;
    }
    if (p3.matcher(str).find()) {
      count++;
    }
    if (p4.matcher(str).find()) {
      count++;
    }
    return count < 3;
  }
}
