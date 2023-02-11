package stage01.lesson10;

/**
 * @ClassName Knapsack
 * @Description 背包问题
 * @Author Alfred.Ning
 * @Date 2022/5/30 9:14
 * @Version 1.0
 **/
public class Knapsack {
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        return process(w, v, 0, 0, bag);
    }

    /**
     * 返回货物的最大价值
     *
     * @param w        重量数组
     * @param v        价值
     * @param index    当前选择的货物
     * @param alreadyW 之前做的选择重量
     * @param bag      袋子总重量
     * @return -1 认为没有方案
     */
    private static int process(int[] w, int[] v, int index, int alreadyW, int bag) {
        if (alreadyW > bag) {
            return -1;
        }
        if (index == w.length) {
            return 0; // 方案有效，但已经没货了
        }
        // 没有选在index所在货物
        int p1 = process(w, v, index + 1, alreadyW, bag);
        int p2Next = process(w, v, index + 1, alreadyW + w[index], bag);

        int p2 = -1;
        if (p2Next != -1) {
            p2 = v[index] + p2Next;
        }
        return Math.max(p1, p2);
    }

    // rest代表剩余空间，index..货物自由选择
    public static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            // 无效方案
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, rest);
        int p2 = -1;
        int p2Next = process(w, v, index + 1, rest - w[index]);
        if (p2Next != -1) {
            p2 = v[index] + p2Next;//有效结果
        }
        return Math.max(p1, p2);
    }

    // 动态规划版本
    public static int dpWay (int[] w, int[] v, int bag){
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        // dp[N][...]=0
        for (int index = N - 1; index >= 0; index--){
            for (int rest = 0; rest <= bag; rest++){ // rest <=0 无效解
                int p1 = dp[index + 1][rest];
                int p2 = -1;
                if (rest - w[index] >= 0){
                    p2 = v[index] + dp[index + 1][rest - w[index]];
                }
                dp[index][rest] = Math.max(p1, p2);

            }
        }
        return dp[0][bag];
    }
}
