package prod.hw;


import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @ClassName HJ17坐标移动
 * @Author 宁强-34436
 * @Date 2022/5/24 14:08
 * @Email qiang.ning@going-link.com
 * @Descirption
 **/
public class HJ17坐标移动 {
  public static void main(String[] args) {
    int[] res = {0, 0};
    Scanner sc = new Scanner(System.in);
    String inputStr = sc.next();
    String[] splits = inputStr.split(";");
    for (String split : splits) {
      if (Pattern.matches("[AWSD][0-9]*", split)) {
        String directionStr = split.substring(0, 1);
        int distance = Integer.parseInt(split.substring(1));
        switch (directionStr) {
          case "A":
            res[0] -= distance;
            break;
          case "W":
            res[1] += distance;
            break;
          case "S":
            res[1] -= distance;
            break;
          case "D":
            res[0] += distance;
            break;
          default:
            break;
        }
      }
    }
    System.out.println(res[0] + "," + res[1]);
  }
}
