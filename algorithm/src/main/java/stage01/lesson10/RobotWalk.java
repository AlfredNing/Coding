package stage01.lesson10;

import java.util.Arrays;
import java.util.zip.CRC32;

/**
 * @ClassName RobotWalk
 * @Description 机器人到达P位置
 * @Author Alfred.Ning
 * @Date 2022/6/4 13:48
 * @Version 1.0
 **/
public class RobotWalk {
    public static int ways1(int N, int M, int K, int P) {
        if (N < 2 || K < 1 || M > N || P < 1 || P > N) {
            // 无效返回0
            return 0;
        }
        return walk1(N, M, K, P);
    }

    // 只能在1-N位置上移动，当前在cur位置，走完rest步后可以到达p位置
    private static int walk1(int n, int cur, int rest, int p) {
        // 当前没有剩余步数，判断是否到达p点
        if (rest == 0) {
            return cur == p ? 1 : 0;
        }
        if (cur == 1) { // 在1位置
            return walk1(n, 2, rest - 1, p);
        }
        if (cur == n) { // 在n位置
            return walk1(n, n - 1, rest - 1, p);
        }
        // 可以向左可以向右
        return walk1(n, cur + 1, rest - 1, p) + walk1(n, cur - 1, rest - 1, p);
    }

    public static int waysCache(int N, int M, int K, int P) {
        if (N < 2 || K < 1 || M > N || P < 1 || P > N) {
            // 无效返回0
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1];// 0位置不用
        //for (int row = 0; row <= N; row++) {
        //    for (int col = 0; col < K; col++) {
        //        dp[row][col] = -1;  // 初始化
        //    }
        //}
        int[] ints = new int[K + 1];
        Arrays.fill(ints, -1);
        Arrays.fill(dp, ints);
        return walkCache(N, M, K, P, dp);
    }

    // 只能在1-N位置上移动，当前在cur位置，走完rest步后可以到达p位置
    private static int walkCache(int n, int cur, int rest, int p, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        // 当前没有剩余步数，判断是否到达p点
        if (rest == 0) {
            dp[cur][rest] = cur == p ? 1 : 0;
            return dp[cur][rest];
        }
        if (cur == 1) { // 在1位置
            dp[cur][rest] = walkCache(n, 2, rest - 1, p,dp);
            return dp[cur][rest];
        }
        if (cur == n) { // 在n位置
            dp[cur][rest] = walkCache(n, n - 1, rest - 1, p,dp);
            return dp[cur][rest];
        }
        // 可以向左可以向右
        dp[cur][rest] = walkCache(n, cur + 1, rest - 1, p,dp) + walkCache(n, cur - 1, rest - 1, p,dp);
        return dp[cur][rest];
    }
}