package stage03.lesson09;

/**
 * @author Alfred.Ning
 * @since 2023年02月25日 13:40:00
 */
public class MinLengthForSort {

  public static int getMinLength(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    // 从左往右
    int max = arr[0];
    int maxIndex = -1;
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] < max) {
        maxIndex = i;
      } else {
        max = Math.max(max, arr[i]);
      }
    }
    // 从右往左
    int min = arr[arr.length - 1];
    int minIndex = -1;
    for (int i = arr.length - 2; i >= 0; i--) {
      if (arr[i] > min) {
        minIndex = i;
      } else {
        min = Math.min(min, arr[i]);
      }
    }
    return maxIndex - minIndex + 1;
  }

  public static void main(String[] args) {
    int[] input = {
        1, 2, 3, 12, 4, 6, 18, 20
    };
    System.out.println(getMinLength(input));
  }
}
