package stage03.lesson01;

import java.util.Arrays;

/** 有序数组 涵盖的点 */
public class CordCoverMaxPoint {
  // 解法一
  public static int maxPoint1(int[] arr, int L) {
    int res = 1;
    for (int i = 0; i < arr.length; i++) {
      int nearest = nearestIndex(arr, i, arr[i] - L);
      res = Math.max(res, i - nearest + 1); // >= 左边界位置
    }
    return res;
  }

  // 二分法求出最左边位置
  private static int nearestIndex(int[] arr, int R, int value) {
    int L = 0;
    int index = R;
    while (L <= R) {
      int mid = L + (R - L) >> 1;
      if (arr[mid] >= value) {
        index = mid;
        R = mid - 1;
      } else {
        L = mid + 1;
      }
    }
    return index;
  }

  // 解法2 -滑动窗口
  public static int maxPoint2(int[] arr, int L) {
    int res = 1;
    int left = 0;
    int right = 0;
    int N = arr.length;
    while (left < N) {
      while (right < N && arr[right] - arr[left] <= L) {
        right++; // 右边界移动
      }
      res = Math.max(res, right - (left++)); // 更新结果 左边界移动
    }
    return res;
  }

  // for test
  public static int test(int[] arr, int L) {
    int max = 0;
    for (int i = 0; i < arr.length; i++) {
      int pre = i - 1;
      while (pre >= 0 && arr[i] - arr[pre] <= L) {
        pre--;
      }
      max = Math.max(max, i - pre);
    }
    return max;
  }

  // for test
  public static int[] generateArray(int len, int max) {
    int[] ans = new int[(int)(Math.random() * len)+1];
    for (int i = 0; i < ans.length; i++) {
      ans[i] = (int) (Math.random() * max);
    }
    Arrays.sort(ans);
    return ans;
  }

  public static void main(String[] args) {
    int len = 100;
    int max = 100;
    int testTime = 100_000;
    System.out.println("測試開始");
    for (int i = 0; i < testTime; i++) {
      int L = (int) (Math.random() * max);
      int[] arr = generateArray(len,max);
      int res1 = maxPoint1(arr, L);
      int res2 = maxPoint2(arr, L);
      int res3 = test(arr, L);
      if (res1 != res2 || res2 != res3) {
        System.out.println("oops!");
        break;
      }
    }
  }
}
