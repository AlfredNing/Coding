package stage03.lesson08;

import java.util.Arrays;

/**
 * @author Alfred.Ning
 * @since 2023年02月12日 16:32:00
 */
public class PMinParts {

  public static int minParts(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    if (s.length() == 1) {
      return 1;
    }
    char[] chars = s.toCharArray();
    int N = chars.length;
    // 范围上的尝试
    boolean[][] isP = new boolean[N][N];
    for (int i = 0; i < N; i++) {
      isP[i][i] = true;// 对角线必定是回文
    }
    for (int i = 0; i < N - 1; i++) {
      isP[i][i + 1] = chars[i] == chars[i + 1];
    }
    for (int row = N - 3; row >= 0; row--) {
      for (int col = row + 2; col < N; col++) {
        isP[row][col] = chars[row] == chars[col] && isP[row + 1][col - 1];
      }
    }
    int[] dp = new int[N + 1]; // 代表从i位置到结束最少的回文部分
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[N] = 0;
    for (int i = N - 1; i >= 0; i--) {
      for (int end = i; end < N; end++) {
        // i..end是回文
        if (isP[i][end]) {
          dp[i] = Math.min(dp[i], 1 + dp[end + 1]);
        }
      }
    }
    return dp[0];
  }
}
