package stage03.lesson12;

/**
 * 股票交易-最大收益-任意次数交易
 *
 * @author Alfred.Ning
 * @since 2023年04月08日 12:28:00
 */
public class BestTimeToBuyandSellStock2 {

  public static int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }
    int ans = 0;
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > prices[i - 1]) {
        ans += prices[i] - prices[i - 1];
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    int[] input = new int[]{3, 4, 5, 6};
    System.out.println(maxProfit(input));
  }
}
