package stage01.lesson10;

import stage01.lesson07.MaxHappy;

/**
 * @ClassName Coffee
 * @Description 洗咖啡杯
 * @Author Alfred.Ning
 * @Date 2022/6/23 21:50
 * @Version 1.0
 **/
public class Coffee {
    //a 洗杯子的常量 b 自己挥发的固定变量
    // drinks 每个员工喝完的时间
    // drinks[0..index-1]已经决定好了，不用你操心了
    // drinks[index..]之后都想变干净
    // washLine 表示洗的机器何时可用
    // 以上情况下，最早的完成时间 process1(drinks, 3, 10, 0, 0)
    public static int proces1(int[] drinks, int a, int b, int index, int washLine) {
        if (index == drinks.length - 1) {
            return Math.min(
                    Math.max(washLine, drinks[index]) + a,
                    drinks[index] + b
            );
        }

        // 不止一杯咖啡
        // 当前杯子要洗
        int wash = Math.max(washLine, drinks[index]) + a;
        // index+1...变干净的时间
        int next1 = proces1(drinks, a, b, index + 1, wash);
        // 整体结束
        int p1 = Math.max(wash, next1);

        int dry = drinks[index] + b;
        int next2 = proces1(drinks, a, b, index + 1, washLine);
        int p2 = Math.max(dry, next2);

        return Math.min(p1, p2);

    }

    // 该动态规划
    public static int dp(int[] drinks, int a, int b) {
        if (a >= b) {
            return drinks[drinks.length - 1] + b;
        }
        int N = drinks.length;
        // a < b
        int limit = 0; // 什么时候可用
        for (int i = 0; i < drinks.length; i++) {
            limit = Math.max(limit, drinks[i]) + a;
        }
        int[][] dp = new int[N + 1][limit + 1];

        for (int washLine = 0; washLine <= limit; washLine++) {
            dp[N - 1][washLine] = Math.min(
                    Math.max(washLine, drinks[N - 1]) + a,
                    drinks[N - 1] + b
            );
        }
        for (int index = N - 2; index >= 0; index--) {
            for (int washLine = 0; washLine <= limit; washLine++) {
                int p1 = Integer.MAX_VALUE;
                int wash = Math.max(washLine, drinks[index]) + a;
                if (wash <= limit) {
                    p1 = Math.max(wash, dp[index + 1][wash]);
                }

                int p2 = Math.max(drinks[index] + b, dp[index + 1][washLine]);

                dp[index][washLine] = Math.min(p1, p2);

            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 5, 5, 7, 10, 12, 12, 12, 12, 12, 12, 15};
        int a = 3;
        int b = 10;
        System.out.println(proces1(arr, a, b, 0, 0));
        System.out.println(dp(arr, a, b));
    }
}
