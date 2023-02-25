package stage03.lesson09;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Alfred.Ning
 * @since 2023年02月25日 18:15:00
 */
public class LongestSubStringWithourRepeatingCharacters {

  public static int lengthOfLongestSubString(String s) {
    int i = 0, j = 0, max = 0;

    HashSet<Character> set = new HashSet<Character>();
    while (j < s.length()) {
      if (!set.contains(s.charAt(j))) {
        set.add(s.charAt(j++));
        max = Math.max(max, set.size());
      } else {
        set.remove(s.charAt(i++));
      }
    }
    return max;
  }

  public static int lengthOfLongestSubstring2(String s) {
    if (s == null || s.equals("")) {
      return 0;
    }
    char[] str = s.toCharArray();
    int[] map = new int[256];
    for (int i = 0; i < 256; i++) {
      map[i] = -1;
    }
    map[str[0]] = 0;
    int N = str.length;
    int ans = 1;
    int pre = 1;
    for (int i = 1; i < N; i++) {
      pre = Math.min(i - map[str[i]], pre + 1);
      ans = Math.max(ans, pre);
      map[str[i]] = i;
    }
    return ans;
  }

}
