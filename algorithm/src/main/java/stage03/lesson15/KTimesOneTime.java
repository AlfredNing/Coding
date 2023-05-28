package stage03.lesson15;

/**
 * @author Alfred.Ning
 * @since 2023年05月28日 10:41:00
 */
public class KTimesOneTime {

  public static int oneNum(int[] arr, int k) {
    int[] eo = new int[32];
    for (int i = 0; i < arr.length; i++) {
      // 转为k进制
      setExclusiveOr(eo, arr[i], k);
    }
    int res = getNumFromKSys(eo, k);
    return res;
  }

  // k进制 转换10进制
  private static int getNumFromKSys(int[] eo, int k) {
    int res = 0;
    for (int i = eo.length - 1; i != -1; i--) {
      res = res * k + eo[i];
    }
    return res;
  }

  private static void setExclusiveOr(int[] eo, int value, int k) {
    int[] curKeyNum = getKSysFromNum(value, k);
    for (int i = 0; i < eo.length; i++) {
      // 防止溢出 直接取模
      eo[i] = (eo[i] + curKeyNum[i]) % k;
    }
  }

  private static int[] getKSysFromNum(int value, int k) {
    int[] res = new int[32];
    int index = 0;
    while (value != 0) {
      res[index++] = value % k;
      value = value / k;
    }
    return res;
  }

  public static void main(String[] args) {
    int[] test1 = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 9};
    System.out.println(oneNum(test1, 3));

  }
}
