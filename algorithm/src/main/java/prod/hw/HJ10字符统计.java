package prod.hw;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @ClassName HJ10字符统计
 * @Author 宁强-34436
 * @Date 2022/5/23 14:44
 * @Email qiang.ning@going-link.com
 * @Descirption
 **/
public class HJ10字符统计 {
  public static void main(String[] args) {
    //useMap();
    useBitSet();
  }

  /*
  BitSet 位图： 常用来去重，用1位来表示一个数据是否出现过，0为没有出现过，1表示出现过
  一个1G的空间，有 8*1024*1024*1024=8.58*10^9bit，也就是可以表示85亿个不同的数。
  统计海量日志
   */
  public static void useBitSet() {
    String inputStr = new Scanner(System.in).next();
    BitSet bs = new BitSet(128);// 0 - 127
    for (char c : inputStr.toCharArray()) {
      if (!bs.get(c)){
        bs.set(c);
      }
    }
    System.out.println(bs.cardinality());
  }

  public static void useMap() {
    Scanner sc = new Scanner(System.in);
    String inputStr = sc.next();
    HashMap<Character, Integer> map = new HashMap();
    for (char c : inputStr.toCharArray()) {
      if (c != '\n') {
        map.put(c, 1);
      }
    }
    System.out.println(map.keySet().size());
  }
}
