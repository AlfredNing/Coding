package prod.hw;

import java.util.*;

/**
 * @ClassName HJ3明明的随机数
 * @Author 宁强-34436
 * @Date 2022/5/23 14:16
 * @Email qiang.ning@going-link.com
 * @Descirption
 **/
public class HJ3明明的随机数 {
  public static void main(String[] args) {
    //randInt();
    useTreeSet();
  }

  public static void useTreeSet() {
    Scanner sc = new Scanner(System.in);
    int length = sc.nextInt();
    TreeSet<Integer> set = new TreeSet<>();
    for (int i = 0; i < length; i++) {
      set.add(sc.nextInt());
    }
    set.forEach(System.out::println);
  }

  // 太烂了
  public static void randInt() {
    Scanner sc = new Scanner(System.in);
    int length = sc.nextInt();
    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < length; i++) {
      int value = sc.nextInt();
      if (!map.containsKey(value)) {
        map.put(value, i);
      }
    }
    int[] data = new int[map.keySet().size()];
    int index = 0;
    for (Integer key : map.keySet()) {
      data[index++] = key;
    }
    Arrays.stream(data).sorted().forEach(System.out::println);

  }
}
