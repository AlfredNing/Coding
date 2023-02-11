package prod.hw;

import java.util.HashSet;

/**
 * @ClassName LeeCode1839最长子字符串
 * @Author 宁强-34436
 * @Date 2022/5/25 13:46
 * @Email qiang.ning@going-link.com
 * @Descirption
 **/
public class LeeCode1839最长子字符串 {
  public int longestBeautifulSubstring1(String word) {
    int res = 0;
    // 长度小于5 肯定不是
    if (word.length() < 5) {
      return res;
    }
    char[] chars = word.toCharArray();
    /**
     * 第二个位置 > 第一个位置大 种类数++ 字符长度++
     * 第二个位置 >= 第一个位置 字符长度++
     * 第二个位置 < 第一个位置  初始化起始变量
     *  种类数到达5 更新结果值
     */
    int rLen = 1;
    int typeNum = 1;
    for (int i = 1; i < chars.length; i++) {
      if (chars[i] >= chars[i - 1]) {
        rLen++;
      }
      if (chars[i] > chars[i - 1]) {
        typeNum++;
      }
      if (chars[i] < chars[i - 1]) {
        rLen = 1;
        typeNum = 1;
      }

      if (typeNum == 5){
        res = Math.max(res, rLen);
      }
    }
    return res;
  }
  public int longestBeautifulSubstring2(String word) {
    if (word.length() < 5) {
      return 0;
    }
    int res = 0;
    HashSet<Character> set  = new HashSet<>();
    for (int hi = 0, lo = -1; hi < word.length(); hi++) {
      if (hi > 0 && word.charAt(hi - 1) > word.charAt(hi)) {
        set = new HashSet();
        lo = hi - 1;
      }
      set.add(word.charAt(hi));
      if (set.size() == 5) {
        res = Math.max(res,hi - lo);
      }
    }
    return res;
  }
}
