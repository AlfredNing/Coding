package stage03.lesson12;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 最大可整合数组
 *
 * @author Alfred.Ning
 * @since 2023年04月08日 10:36:00
 */
public class LongestIntegratedLength {

  // 暴力方法
  public static int getLIT1(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int len = 0;
    for (int start = 0; start < arr.length; start++) {
      for (int end = start; end < arr.length; end++) {
        if (isIntegrated(arr, start, end)) {
          len = Math.max(len, end - start + 1);
        }
      }
    }
    return len;
  }

  private static boolean isIntegrated(int[] arr, int left, int right) {
    int[] newArr = Arrays.copyOfRange(arr, left, right + 1);
    Arrays.sort(newArr);
    for (int i = 1; i < newArr.length; i++) {
      if (newArr[i - 1] != newArr[i] - 1) {
        return false;
      }
    }
    return true;
  }


  // 重新定义
  public static int getLIT2(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    HashSet<Integer> set = new HashSet<>();
    int max = 0;
    int min = 0;
    int len = 0;
    for (int l = 0; l < arr.length; l++) {
      set.clear();
      max = Integer.MIN_VALUE;
      min = Integer.MAX_VALUE;
      for (int r = l; r < arr.length; r++) {
        if (set.contains(arr[r])) {// 出现重复，后面不需要验证
          break;
        }
        set.add(arr[r]);
        max = Math.max(max, arr[r]);
        min = Math.min(min, arr[r]);
        if (max - min == r - l) { // r - l 个数减1
          len = Math.max(len, r - l + 1);
        }
      }
    }
    return len;
  }
}
