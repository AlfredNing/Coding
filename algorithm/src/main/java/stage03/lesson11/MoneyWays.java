package stage03.lesson11;

/**
 * 金额组合
 *
 * @author Alfred.Ning
 * @since 2023年04月02日 16:47:00
 */
public class MoneyWays {

  public static int moneyWasy(int[] arbitary, int[] onlyOne, int money) {
    if (money < 0) {
      return 0;
    }
    if ((arbitary == null || arbitary.length == 0) && (onlyOne == null || onlyOne.length == 0)) {
      return 0;
    }

    int[][] doparb = getDpArb(arbitary, money);
    int[][] doOnlyOne = getDpOnlyOne(arbitary, money);
    if (doparb == null) {
      return doOnlyOne[doOnlyOne.length - 1][money];
    }
    if (doOnlyOne == null) {
      return doparb[doparb.length - 1][money];
    }
    int res = 0;
    for (int i = 0; i <= money; i++) {
      res += doparb[doparb.length - 1][i] * doOnlyOne[doOnlyOne.length - 1][money - i];
    }
    return res;
  }

  private static int[][] getDpArb(int[] arr, int money) {
    if (arr == null || arr.length == 0) {
      return null;
    }
    int[][] dp = new int[arr.length][money + 1];

    // 第一列 不要任何面值
    for (int i = 0; i < dp.length; i++) {
      dp[i][0] = 1;
    }
    // 第一行
    for (int j = 1; arr[0] * j < money; j++) {
      dp[0][arr[0] * j] = 1;
    }

    for (int i = 1; i < arr.length; i++) {
      for (int j = 1; j <= money; j++) {
        dp[i][j] = dp[i - 1][j];
        dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
      }
    }
    return dp;
  }

  private static int[][] getDpOnlyOne(int[] arr, int money) {
    if (arr == null || arr.length == 0) {
      return null;
    }
    int[][] dp = new int[arr.length][money + 1];

    // 第一列 不要任何面值
    for (int i = 0; i < dp.length; i++) {
      dp[i][0] = 1;
    }
    // 第一行
    for (int j = 1; arr[0] * j < money; j++) {
      dp[0][arr[0] * j] = 1;
    }

    for (int i = 1; i < arr.length; i++) {
      for (int j = 1; j <= money; j++) {
        dp[i][j] = dp[i - 1][j];
        dp[i][j] += j - arr[i] >= 0 ? dp[i - 1][j - arr[i]] : 0;
      }
    }
    return dp;
  }

}
