package stage01.lesson10;

import java.util.HashMap;

/**
 * @ClassName CoinsWay
 * @Description 货币目标数
 * @Author Alfred.Ning
 * @Date 2022/6/16 8:33
 * @Version 1.0
 **/
public class CoinsWay {
    public static int ways1(int[] arr, int aim) { // aim目标钱数
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    // 可以使用arr[inidex...]所有的面值，每一种面值都可以使用任意，组成rest 有多少种方法
    private static int process1(int[] arr, int index, int rest) {
        //if (rest < 0) { 不可能被调用
        //    return 0;
        //}
        if (index == arr.length) { // 没有货币可以选择
            return rest == 0 ? 1 : 0;
        }

        // 普遍尝试
        int ways = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ways += process1(arr, index + 1, rest - (zhang * arr[index]));
        }
        return ways;
    }

    // 记忆化搜索
    public static int ways2(int[] arr, int aim) { // aim目标钱数
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        HashMap<String, Integer> map = new HashMap<>();
        int[][] dp = new int[arr.length + 1][aim + 1];
        // 初始化
        for (int i = 0; i < dp.length; i++) {
            for (int i1 = 0; i1 < dp[0].length; i1++) {
                dp[i][i1] = -1;
            }
        }
        return process2(arr, 0, aim, dp);
    }

    // 有重复解 记忆化搜索 自顶向下的动态规划
    // dp【index】[rest]!=-1 说明算过，反之亦然
    private static int process2(int[] arr, int index, int rest, int[][] dp) {
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        if (index == arr.length) { // 没有货币可以选择
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }

        // 普遍尝试
        int ways = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ways += process2(arr, index + 1, rest - (zhang * arr[index]), dp);
        }
        dp[index][rest] = ways;
        return ways;
    }

    // 动态规划
    public static int waysdp(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index++) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    ways += dp[index + 1][rest - zhang * arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    // 动态规划2
    public static int waysdp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index++) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] > 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {5, 10, 50, 100};
        int sum = 1000;
        System.out.println(ways1(arr, sum));
        System.out.println(ways2(arr, sum));
    }
}
