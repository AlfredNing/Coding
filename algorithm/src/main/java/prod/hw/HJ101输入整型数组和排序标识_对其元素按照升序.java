package prod.hw;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @ClassName HJ101输入整型数组和排序标识_对其元素按照升序
 * @Author 宁强-34436
 * @Date 2022/5/24 17:28
 * @Email qiang.ning@going-link.com
 * @Descirption
 **/
public class HJ101输入整型数组和排序标识_对其元素按照升序 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      int len = sc.nextInt();
      Integer[] ints = new Integer[len];
      for (int i = 0; i < len; i++) {
        ints[i] = sc.nextInt();
      }
      sortByFlag(ints, sc.nextInt());
    }
  }

  private static void sortByFlag(Integer[] ints, int flag) {
    if (flag == 0) {
      Arrays.sort(ints, Comparator.comparingInt(o -> o));
    } else {
      Arrays.sort(ints, (o1, o2) -> o2 - o1);
    }
    Arrays.stream(ints).forEach(x -> {
      System.out.print(x +" ");
    });
  }
}
