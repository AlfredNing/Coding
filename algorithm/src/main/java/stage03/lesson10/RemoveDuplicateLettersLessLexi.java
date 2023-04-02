package stage03.lesson10;

/**
 * 保留字符串获取最小字典序
 *
 * @author Alfred.Ning
 * @since 2023年03月26日 11:14:00
 */
public class RemoveDuplicateLettersLessLexi {

  public static String remove(String str) {
    if (str == null || str.length() < 2) {
      return str;
    }
    int[] map = new int[256];
    for (int i = 0; i < str.length(); i++) {
      map[str.charAt(i)]++;
    }
    int minASCIndex = 0;
    for (int i = 0; i < str.length(); i++) {
      if (--map[str.charAt(i)] == 0) {
        break;
      } else {
        minASCIndex = str.charAt(minASCIndex) > str.charAt(i) ? i : minASCIndex;
      }
    }
    return str.charAt(minASCIndex) +
        remove(str.substring(minASCIndex + 1).replace(String.valueOf(str.charAt(minASCIndex)), ""));
  }
}
