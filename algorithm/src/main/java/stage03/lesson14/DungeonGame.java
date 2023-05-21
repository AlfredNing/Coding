package stage03.lesson14;

/**
 * 最少血量游戏
 *
 * @author Alfred.Ning
 * @since 2023年05月21日 11:35:00
 */
public class DungeonGame {

  // 暴力尝试过程
  public static int needMin(int[][] matrix) {
    return process(matrix, matrix.length, matrix[0].length, 0, 0);
  }

  // 来到marix[row][col]，还没有登上去，到达右下角的位置，返回至少的初始血量
  private static int process(int[][] matrix, int n, int m, int row, int col) {
    if (row == n - 1 && col == m - 1) { // 达到右下角
      return matrix[row][col] < 0 ? (-matrix[row][col] + 1) : 1;
    }

    // 来到最后一行
    if (row == n - 1) {
      // 拿到右侧的最少血量
      int rightNeed = process(matrix, n, m, row, col + 1);
      if (matrix[row][col] < 0) {
        return -matrix[row][col] + rightNeed;
      } else if (matrix[row][col] >= rightNeed) {
        return 1;
      } else {
        return rightNeed - matrix[row][col];
      }
    }
    // 来到最后一列
    if (col == m - 1) {
      // 拿到下侧的最少血量
      int downNeed = process(matrix, n, m, row, col + 1);
      if (matrix[row][col] < 0) {
        return -matrix[row][col] + downNeed;
      } else if (matrix[row][col] >= downNeed) {
        return 1;
      } else {
        return downNeed - matrix[row][col];
      }
    }

    // 普遍位置
    int minNeed = Math.min(process(matrix, n, m, row, col + 1),
        process(matrix, n, m, row + 1, col));
    if (matrix[row][col] < 0) {
      return -matrix[row][col] + minNeed;
    } else if (matrix[row][col] >= minNeed) {
      return 1;
    } else {
      return minNeed - matrix[row][col];
    }
  }

  // 改成dp
  public static int minHp1(int[][] matrix) {
    int n = matrix.length, m = matrix[0].length;
    int[][] dp = new int[n][m];
    dp[n - 1][m - 1] = Math.max(1, 1 - dp[n - 1][m - 1]);

    // 最后一行
    for (int i = m - 2; i >= 0; i--) {
      dp[n - 1][i] = Math.max(1, dp[n - 1][i + 1] - matrix[n - 1][i]);
    }

    // 最后一列
    for (int i = n - 2; i >= 0; i--) {
      dp[i][m - 1] = Math.max(1, dp[i + 1][m - 1] - matrix[i][m - 1]);
    }
    // 普遍位置
    for (int i = n - 2; i >= 0; i--) {
      for (int j = m - 2; j >= 0; j--) {
        dp[i][j] = Math.max(Math.min(dp[i + 1][j], dp[i][j + 1]) - matrix[i][j], 1);
      }
    }
    return dp[0][0];
  }

}
