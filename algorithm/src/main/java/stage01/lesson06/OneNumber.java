package stage01.lesson06;

/**
 * 多少个1
 *
 * @author Alfred.Ning
 * @since 2023年04月02日 19:46:00
 */
public class OneNumber {

  public static int countDigitOne(int num) {
    if (num < 1) {
      return 0;
    }
    int len = getOneOfNum(num);
    if (len == 1) {
      return 1;
    }

    int tmp1 = powBaseof10(len - 1);
    // num最高位
    int first = num / tmp1;
    int firstOneNum = first == 1 ? num % tmp1 + 1 : tmp1;
    // 除去最高位之外，剩下1的数量
    // 最高位1 10(k-2次方) * (k-1) * 1
    // 最高位first 10(k-2次方) * (k-1) * first
    int otherOneNum = first * (len - 1) * (tmp1 / 10);
    return firstOneNum + otherOneNum + countDigitOne(num % tmp1);
  }

  private static int powBaseof10(int base) {
    return (int) Math.pow(10, base);
  }

  private static int getOneOfNum(int num) {
    int len = 0;
    while (num != 0) {
      len++;
      num /= 10;
    }
    return len;
  }

}
