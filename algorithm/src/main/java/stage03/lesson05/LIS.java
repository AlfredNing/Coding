package stage03.lesson05;

/**
 * 最长递增子序列
 *
 * @author Alfred.Ning
 */
public class LIS {
    // 获取dp
    public static int[] getDp2(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        ends[0] = arr[0];
        dp[0] = 1;
        int right = 0; // 0....right 表示有效区
        int l = 0;
        int m = 0;
        int r = 0;
        for (int i = 0; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            // 如果扩充了，l = m + 1
            right = Math.max(right, l);
            ends[l] = arr[i];
            dp[i] = l + 1;
        }
        return dp;
    }
}
