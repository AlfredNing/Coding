package stage03.lesson09;

/**
 * @author Alfred.Ning
 * @since 2023年02月25日 12:58:00
 */
public class StringCross {

  public static boolean isCross(String s1, String s2, String aimStr) {
    if (s1 == null || s2 == null || aimStr == null) {
      return false;
    }
    char[] str1 = s1.toCharArray();
    char[] str2 = s2.toCharArray();
    char[] aim = aimStr.toCharArray();
    if (aim.length != str1.length + str2.length) {
      return false;
    }

    boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
    dp[0][0] = true;
    // 0列
    for (int i = 1; i <= str1.length; i++) {
      if (str1[i - 1] != aim[i - 1]) {
        break;
      }
      dp[i][0] = true;
    }
    // 0列
    for (int i = 1; i <= str2.length; i++) {
      if (str2[i - 1] != aim[i - 1]) {
        break;
      }
      dp[0][i] = true;
    }
    for (int i = 1; i <= str1.length; i++) {
      for (int j = 1; j <= str2.length; j++) {
        if (
            (str1[i - 1] == aim[i + j - 1] && dp[i - 1][j])
                ||
                (str2[j - 1] == aim[i + j - 1] && dp[i][j - 1])
        ) {
          dp[i][j] = true;
        }
      }
    }

    return dp[str1.length][str2.length];
  }

  public static void main(String[] args) {
    System.out.println(isCross("aabcb", "aacbb", "aaabaccbbb"));
  }

}
