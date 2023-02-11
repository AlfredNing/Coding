package stage02.lesson02;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName FibonacciProblem
 * @Description
 * @Author Alfred.Ning
 * @Date 2022/7/3 15:43
 * @Version 1.0
 **/
public class FibonacciProblem {
    public static void main(String[] args) {
        int N = 10;
        System.out.println(way1(N));
        System.out.println(ways2(N));
        System.out.println(ways3(N));
    }

    // 第一种方法，原始O(N)
    public static int way1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return way1(n - 1) + way1(n - 2);
    }

    // 第二种方法 遍历
    public static int ways2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int pre = 1;
        int res = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;

        }
        return res;
    }

    // 第三种方法 矩阵乘法 O(logN)
    public static int ways3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        // 递推公式 求得矩阵
        int[][] base = {
                {1, 1},
                {1, 0}
        };
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    // 求 n - 2 次方的结果
    private static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) { // 排除掉0的
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }

    // 两个矩阵相乘
    // 两个矩阵乘完之后的结果返回
    public static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }
}
