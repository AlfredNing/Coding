package stage03.lesson01;

/**
 * 括号匹配
 *
 * @author Alfred.Ning
 */
public class NeedParentheses {
  // 问题一
  public static boolean valid(String s) {
    char[] str = s.toCharArray();
    int count = 0;
    for (int i = 0; i < str.length; i++) {
      count += str[i] == '(' ? 1 : -1;
      if (count < 0) {
        return false;
      }
    }
    return count == 0;
  }

  // 问题2
  public static int needParentheses(String s) {
    char[] str = s.toCharArray();
    int count = 0;
    int needSolevRight = 0;
    for (int i = 0; i < str.length; i++) {
      if (str[i] == '(') {
        count++;
      } else {
        if (count == 0) {
          needSolevRight++;
        } else {
          count--;
        }
      }
    }
    return count + needSolevRight;
  }
}
