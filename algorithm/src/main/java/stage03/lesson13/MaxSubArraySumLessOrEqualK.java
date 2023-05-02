package stage03.lesson13;

import java.util.TreeSet;

/**
 * 最小子数组小于等于k之和
 *
 * @author Alfred.Ning
 * @since 2023年04月22日 10:51:00
 */
public class MaxSubArraySumLessOrEqualK {

  // 没有单调性。如果全部是窗口 可以使用窗口
  public static int getMaxLessOrEqualK(int[] arr, int k) {
    TreeSet<Integer> set = new TreeSet<>();
    // 一个元素都没有的时候
    set.add(0);
    int max = Integer.MIN_VALUE;
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
      if (set.ceiling(sum - k) != null) {
        // <=
        max = Math.max(max, sum - set.ceiling(sum - k));
      }
      set.add(sum);
    }
    return max;
  }


}
