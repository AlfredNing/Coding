package stage03.lesson14;

import java.util.HashSet;

/**
 * 不使用排序，求出最大差值
 *
 * @author Alfred.Ning
 * @since 2023年05月21日 14:02:00
 */
public class MaxGap {

  public static int maxGap(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int len = nums.length;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < len; i++) {
      min = Math.min(min, nums[i]);
      max = Math.max(max, nums[i]);
    }
    if (min == max) {
      return 0;
    }

    // 桶，hasNum[i]表示桶里面进来过数字
    boolean[] hasNum = new boolean[len + 1];
    int[] maxs = new int[len + 1]; // max[i] i号桶里面的最大值
    int[] mins = new int[len + 1]; // max[i] i号桶里面的最小值

    int bid; // 桶号
    for (int i = 0; i < len; i++) {
      bid = bucket(nums[i], len, min, max);
      mins[bid] = hasNum[i] ? Math.min(mins[bid], nums[i]) : nums[i];
      maxs[bid] = hasNum[i] ? Math.max(maxs[bid], nums[i]) : nums[i];
      hasNum[bid] = true;
    }
    int res = 0;
    int lastMax = maxs[0]; // 上一个非空桶的最大值
    for (int i = 0; i < len; i++) {
      if (hasNum[i]) {
        res = Math.max(res, mins[i] - lastMax);
        lastMax = maxs[i];
      }
    }
    return res;
  }

  private static int bucket(long num, long len, long min, long max) {
    return (int) (((num - min) * len) / (max - min));
  }

}
