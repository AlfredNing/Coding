package stage03.lesson01;

/**
 * 涂染方案
 *
 * @author Alfred.Ning
 */
public class ColorLeftRight {
  public static int minPaint(String s) {
    if (s == null || s.length() < 2) {
      return 0;
    }
    char[] str = s.toCharArray();
    int N = str.length;
    // 预处理辅助数组
    int[] right = new int[N];
    right[N - 1] = str[N - 1] == 'R' ? 1 : 0;
    for (int i = N - 2; i >= 0; i--) {
      right[i] = right[i + 1] + (str[i] == 'R' ? 1 : 0);
    }

    int ans = right[0]; // 最左侧位置，全部改为G
    int left = 0; // 左侧G
    for (int i = 0; i < N - 1; i++) {
      left += str[i] == 'G' ? 1 : 0;
      ans = Math.min(ans, left + right[i + 1]);
    }
    // 从左侧位置，全部改为R
    ans = Math.min(ans, left + (str[N - 1] == 'G' ? 1 : 0));
    return ans;
  }

  // 省略辅助数组
  public static int minPaint2(String s) {
    if (s == null || s.length() < 2) {
      return 0;
    }
    char[] str = s.toCharArray();
    int N = str.length;
    int rAll = 0;
    for (int i = 0; i < N; i++) {
      rAll += str[i] == 'R' ? 1 : 0;
    }

    int ans = rAll;
    int left = 0; // 左侧G
    for (int i = 0; i < N - 1; i++) {
      left += str[i] == 'G' ? 1 : 0;
      rAll -= str[i] == 'R' ? 1 : 0;
      ans = Math.min(ans, left  + rAll);
    }
    // 从左侧位置，全部改为R
    ans = Math.min(ans, left + (str[N - 1] == 'G' ? 1 : 0));
    return ans;
  }

  public static void main(String[] args) {
    String test = "GGGGR";
    System.out.println(minPaint(test));
    System.out.println(minPaint2(test));
  }
}
