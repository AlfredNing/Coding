package stage03.lesson12;

/**
 * 股票交易-最大收益
 *
 * @author Alfred.Ning
 * @since 2023年04月08日 12:28:00
 */
public class BestTimeToBuyandSellStock1 {

  public static int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }
    int min = prices[0];
    int ans = 0;
    for (int i = 0; i < prices.length; i++) {
      min = Math.min(min, prices[i]);
      ans = Math.max(ans, prices[i] - min);
    }
    return ans;
  }
}
