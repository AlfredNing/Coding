package stage03.lesson14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * @author Alfred.Ning
 * @since 2023年05月21日 12:45:00
 */
public class CherryPickUP {

  // 暴力解
  public static int getMaxPathSum(int[][] matrix) {
    return process(matrix, 0, 0, 0);
  }

  // A坐标：Ar Ac
  // B坐标：Br Bc => 简化参数，走相同的步数 Br Ar + AC - Br
  // 限制：来到同一份，只获得一份
  private static int process(int[][] matrix, int Ar, int Ac, int Br) {
    int N = matrix.length;
    int M = matrix[0].length;
    if (Ar == N - 1 && Ac == M - 1) {
      // 来到右下角
      return matrix[Ar][Ac];
    }
    /**
     * A 下 B右
     * A 下 B下
     * A 右 B右
     * A 右 B下
     */
    int Bc = Ar + Ac - Br;
    int ADownBright = -1;
    if (Ar + 1 < N && Bc + 1 < M) {
      ADownBright = process(matrix, Ar + 1, Ac, Br);
    }

    int ADownBDown = -1;
    if (Ar + 1 < N && Br + 1 < N) {
      ADownBDown = process(matrix, Ar + 1, Ac, Br + 1);
    }

    int ARightBRight = -1;
    if (Ac + 1 < M && Bc + 1 < M) {
      ARightBRight = process(matrix, Ar, Ac + 1, Br);
    }

    int ARightBDown = -1;
    if (Ac + 1 < M && Br + 1 < N) {
      ARightBDown = process(matrix, Ar, Ac + 1, Br + 1);
    }

    int nextBest = Math.max(Math.max(ADownBright, ADownBDown), Math.max(ARightBRight, ARightBDown));

    if (Ar == Br) { // 同一个位置
      return matrix[Ar][Ac] + nextBest;
    }
    // 不同位置
    return matrix[Ar][Ac] + matrix[Br][Bc] + nextBest;
  }

  // dp
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StreamTokenizer in = new StreamTokenizer(br);
    PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    while (in.nextToken() != StreamTokenizer.TT_EOF) {
      int N = (int) in.nval;
      in.nextToken();
      int M = (int) in.nval;
      int[][] matrix = new int[N][M];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          in.nextToken();
          matrix[i][j] = (int) in.nval;
        }
      }
      out.println(cherryPickup(matrix));
      out.flush();
    }
  }

  public static int cherryPickup(int[][] grid) {
    int N = grid.length;
    int M = grid[0].length;
    int[][][] dp = new int[N][M][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        for (int k = 0; k < N; k++) {
          dp[i][j][k] = Integer.MIN_VALUE;
        }
      }
    }
    int ans = process(grid, 0, 0, 0, dp);
    return ans < 0 ? 0 : ans;
  }

  public static int process(int[][] grid, int x1, int y1, int x2, int[][][] dp) {
    if (x1 == grid.length || y1 == grid[0].length || x2 == grid.length
        || x1 + y1 - x2 == grid[0].length) {
      return Integer.MIN_VALUE;
    }
    if (dp[x1][y1][x2] != Integer.MIN_VALUE) {
      return dp[x1][y1][x2];
    }
    if (x1 == grid.length - 1 && y1 == grid[0].length - 1) {
      dp[x1][y1][x2] = grid[x1][y1];
      return dp[x1][y1][x2];
    }
    int next = Integer.MIN_VALUE;
    next = Math.max(next, process(grid, x1 + 1, y1, x2 + 1, dp));
    next = Math.max(next, process(grid, x1 + 1, y1, x2, dp));
    next = Math.max(next, process(grid, x1, y1 + 1, x2, dp));
    next = Math.max(next, process(grid, x1, y1 + 1, x2 + 1, dp));
    if (grid[x1][y1] == -1 || grid[x2][x1 + y1 - x2] == -1 || next == -1) {
      dp[x1][y1][x2] = -1;
      return dp[x1][y1][x2];
    }
    if (x1 == x2) {
      dp[x1][y1][x2] = grid[x1][y1] + next;
      return dp[x1][y1][x2];
    }
    dp[x1][y1][x2] = grid[x1][y1] + grid[x2][x1 + y1 - x2] + next;
    return dp[x1][y1][x2];
  }
}
