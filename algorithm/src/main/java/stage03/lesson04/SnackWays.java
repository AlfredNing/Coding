package stage03.lesson04;

/**
 * @author Alfred.Ning
 */
public class SnackWays {
    // 经典的从左到右尝试模型
    public static int ways1(int[] arr, int w) {
        return process(arr, 0, w);
    }

    // 来到当前位置index,剩余背包容量rest,返回方法数
    private static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            // 之前选择有误
            return -1;
        }
        if (index == arr.length) {
            // 已选择所有零食
            return 1;
        }
        int next1 = process(arr, index + 1, rest);// 不要当前位置零食 不会返回-1
        int next2 = process(arr, index + 1, rest - arr[index]);// 要当前位置零食
        return next1 + (next2 == -1 ? 0 : next2);
    }

    // 上面改成二维动态规划
    // dp[i][j] 表示从0-i自由选择，体积必须累加到j的方法数
    // dp[i][j] = dp[i-1][j] + dp[i-1][j-arr[i]]
    // 经典的从左到右尝试模型
    public static int ways2(int[] arr, int w) {
        return dpWays2(arr, 2);
    }

    private static int dpWays2(int[] arr, int w) {
        int n = arr.length;
        int[][] dp = new int[n][w + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        if (arr[0] <= w) {
            dp[0][arr[0]] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= w; j++) {
                dp[i][j] = dp[i - 1][j] + ((j - arr[i] >= 0 ? dp[i - 1][j - arr[i]] : 0));
            }
        }
        int ans = 0;
        for (int j = 0; j <= w; j++) {
            ans += dp[n - 1][j];
        }
        return ans;
    }
}
