package stage03.lesson13;

/**
 * 最长递增链
 *
 * @author Alfred.Ning
 * @since 2023年04月22日 12:20:00
 */
public class MaxIncreasingPath {

  public static int maxPath(int[][] matrix) {
    int ans = Integer.MIN_VALUE;
    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[0].length; col++) {
        ans = Math.max(ans, process(matrix, row, col));
      }
    }
    return ans;
  }

  // 暴力方法
  private static int process(int[][] matrix, int row, int col) {
    if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
      return -1;
    }
    int up = 0;
    int down = 0;
    int left = 0;
    int right = 0;
    if (row - 1 >= 0 && matrix[row - 1][col] > matrix[row][col]) {
      up = process(matrix, row - 1, col);
    }
    if (row + 1 < matrix.length && matrix[row + 1][col] > matrix[row][col]) {
      down = process(matrix, row + 1, col);
    }
    if (col - 1 >= 0 && matrix[row][col - 1] > matrix[row][col]) {
      left = process(matrix, row, col - 1);
    }
    if (col + 1 < matrix[0].length && matrix[row][col + 1] > matrix[row][col]) {
      right = process(matrix, row, col + 1);
    }
    return 1 + Math.max(Math.max(up, down), Math.max(left, right));
  }


  // 记忆化搜索 自顶向底的动态规划
  /*
  时间复杂度 N * M
    非递归行为：每个位置碰一次 N * M * 1
    递归行为：每个i,j位置只会在第一次遇到O(1)
   */
  public static int maxPath2(int[][] matrix) {
    int ans = Integer.MIN_VALUE;
    int[][] dp = new int[matrix.length][matrix[0].length];
    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[0].length; col++) {
        ans = Math.max(ans, process(matrix, row, col, dp));
      }
    }
    return ans;
  }


  private static int process(int[][] matrix, int row, int col, int[][] dp) {
    if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
      return -1;
    }
    // dp[i][j] = 0 说明process(i,j)没有算过
    if (dp[row][col] != 0) {
      return dp[row][col];
    }
    int up = 0;
    int down = 0;
    int left = 0;
    int right = 0;
    if (row - 1 >= 0 && matrix[row - 1][col] > matrix[row][col]) {
      up = process(matrix, row - 1, col);
    }
    if (row + 1 < matrix.length && matrix[row + 1][col] > matrix[row][col]) {
      down = process(matrix, row + 1, col);
    }
    if (col - 1 >= 0 && matrix[row][col - 1] > matrix[row][col]) {
      left = process(matrix, row, col - 1);
    }
    if (col + 1 < matrix[0].length && matrix[row][col + 1] > matrix[row][col]) {
      right = process(matrix, row, col + 1);
    }
    int ans = 1 + Math.max(Math.max(up, down), Math.max(left, right));
    // 加入缓存
    dp[row][col] = ans;
    return ans;
  }
  // 每个位置依赖的太多，不要进行精细化组织
  // 每个位置右枚举行为，严格依赖有限个位置

}
