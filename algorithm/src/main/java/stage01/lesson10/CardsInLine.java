package stage01.lesson10;

/**
 * @ClassName CardsInLine
 * @Description 纸牌博弈论
 * @Author Alfred.Ning
 * @Date 2022/6/3 9:59
 * @Version 1.0
 **/
public class CardsInLine {
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 获胜分数
        return Math.max(
                f(arr, 0, arr.length - 1), // 先手
                s(arr, 0, arr.length - 1) // 后手
        );
    }

    public static int f(int[] arr, int L, int R) {
        if (L == R) {
            // 先手拿只剩最后一张牌
            return arr[L];
        }
        return Math.max(
                arr[L] + s(arr, L + 1, R),
                arr[R] + s(arr, L, R - 1)
        );
    }

    public static int s(int[] arr, int L, int R) {
        if (L == R) {
            // 后手拿只剩最后一张表
            return 0;
        }
        return Math.min(
                f(arr, L + 1, R),
                f(arr, L, R - 1)
        );
    }

    // 修改动态规划
    public static int dbWay(int[] arr) {
        if (arr == null || arr.length == 0){
            return 0;
        }
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];

        for (int i = 0; i < N; i++) {
            f[i][i] = arr[i];
        }
        // 省略 s[i][i] = 0
        for (int i = 1; i < N; i++){
            int L = i;
            int R = i;
            while (L < N && R < N){
                f[L][R] = Math.max(
                        arr[L] + s[L + 1][R],
                        arr[R] + s[L - 1][R]);
                s[L][R] =  Math.max(
                        arr[L] + f[L + 1][R],
                        arr[R] + f[L][ R - 1]);
                L++;
                R++;
            }
        }

        return Math.max(f[0][N - 1], s[0][N - 1]);
    }
    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win1(arr));
//        System.out.println(win2(arr));
//        System.out.println(win3(arr));

    }
}
