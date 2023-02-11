package prod.hw;

import javax.sound.midi.Soundbank;
import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @ClassName HJ整数与IP地址间的转换
 * @Author 宁强-34436
 * @Date 2022/5/24 15:54
 * @Email qiang.ning@going-link.com
 * @Descirption
 **/
public class HJ整数与IP地址间的转换 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      convert(sc.next());
    }

  }

  private static void convert(String str) {
    if (str.contains(".")) {
      long res = 0L;
      // ddn -> number
      for (String s : str.split("\\.")) {
        res = res * 256 + Long.parseLong(s);
      }
      System.out.println(res);
    } else {
      // number -> ddn
      String[] ans = new String[4];
      long number = Long.parseLong(str);
      for (int i = 3; i >= 0; i--) {
        ans[i] = Long.toString(number % 256);
        number = number / 256;
      }
      System.out.println(String.join(".",ans));

    }
  }
}
