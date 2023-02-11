package stage03.lesson01;

/**
 * 括号问题
 *
 * @author Alfred.Ning
 */
public class ParenthesesDeep {
  public static void main(String[] args) {
    String s = "((((";
    System.out.println(deep(s));
  }

  // 括号的嵌套长度
  public static int deep(String s) {
    char[] str = s.toCharArray();
    if (!isValid(str)) return 0;
    int res = 0;
    int count = 0;
    for (int i = 0; i < str.length; i++) {
      if (str[i] == '(') {
        res = Math.max(res, ++count);
      } else { // 右括号
        count--;
      }
    }
    return res;
  }

  private static boolean isValid(char[] str) {
    if (str == null || str.length == 0) {
      return false;
    }
    int status = 0;
    for (int i = 0; i < str.length; i++) {
      if (str[i] != ')' && str[i] != '(') {
        return false;
      }
      if (str[i] == ')' && --status < 0) {
        return false;
      }
      if (str[i] == '(') {
        status++;
      }
    }
    return status == 0;
  }

  // 有效括号的子括号长度
  public static int maxLength(String s) {
    if (s == null || s.equals("")) {
      return 0;
    }
    char[] str = s.toCharArray();
    int[] dp = new int[str.length];
    int pre = 0;
    int res = 0;
    // dp[0] = 0 左括号也是dp[i]= 0
    for (int i = 1; i < str.length; i++) {
      if (str[i] == '(') { // 只处理右括号
        pre = i - dp[i - 1] - 1;
        if (pre >= 0 && str[pre] == '(') {
          dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
        }
        res = Math.max(res, dp[i]);
      }
    }
    return res;
  }
}
