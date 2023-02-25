package stage03.lesson09;

import java.util.Arrays;

/**
 * @author Alfred.Ning
 * @since 2023年02月25日 17:53:00
 */
public class MinPatches {

  public static int minPatches(int[] arr, int aim) {
    int patches = 0; // 最少缺的数字
    int range = 0;
    Arrays.sort(arr);
    for (int i = 0; i < arr.length; i++) {
      while (arr[i] - 1 > range) {
        range += range + 1;
        patches++;
        if (range >= aim) {
          return patches;
        }
        range += arr[i];
        if (range >= aim) {
          return patches;
        }
      }
    }
    // 遍历结束 range还是达不到aim
    while (aim >= range + 1) {
      range += range + 1;
      patches++;
    }
    return patches;
  }

}
