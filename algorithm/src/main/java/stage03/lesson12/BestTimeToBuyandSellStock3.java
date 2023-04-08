package stage03.lesson12;

/**
 * 股票交易-最大收益-限制次数交易
 *
 * @author Alfred.Ning
 * @since 2023年04月08日 12:28:00
 */
public class BestTimeToBuyandSellStock3 {


  public static int dp(int k, int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }
    int N = prices.length;
    if (k >= N / 2) {
      return allTrans(prices);
    }
    // dp[i][j] => dp[i+1][j]
    int[][] dp = new int[N][k + 1];
    int ans = 0;
    for (int i = 1; i <= k; i++) {
      //dp[1][j] = dp[0][j] || dp[1][j-1] - [1] dp[0][j-1] - [0]   ===》 +[1]
      int t = dp[0][i - 1] - prices[0];
      for (int j = 1; j < N; j++) {
        t = Math.max(t, dp[j][i - 1] - prices[j]);
        dp[j][i] = Math.max(dp[j - 1][i], t + prices[j]);
        ans = Math.max(ans, dp[j][i]);
      }
    }
    return ans;
  }

  public static int allTrans(int[] prices) {
    int ans = 0;
    for (int i = 1; i < prices.length; i++) {
      ans += Math.max(prices[i] - prices[i - 1], 0);
    }
    return ans;
  }

  public static void main(String[] args) {
    int[] test = {4, 1, 231, 21, 12, 312, 312, 3, 5, 2, 423, 43, 146};
    int K = 3;
    System.out.println(dp(K, test));
  }
}
