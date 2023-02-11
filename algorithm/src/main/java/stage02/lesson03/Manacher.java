package stage02.lesson03;

/**
 * @ClassName Manacher
 * @Description Manacher算法
 * @Author Alfred.Ning
 * @Date 2022/7/21 20:46
 * @Version 1.0
 **/
public class Manacher {

    /*
    // 伪代码
    public static int maxLen(String str){
        // str -> 填充字符串 11211 #1#1#2#1#1
        // str -> strx
        int[] pArr = new int[str.length()];
        int R  = -1; // 回文半径
        int C = -1; // 回文中心
        for (int i = 0; i < str.length(); i++) {
            if (i在R外){
                暴力扩
            }else {
                // i在r内
                if(i对称点的回文区域彻底在L...R内部){
                pArr[i] = pArr[i对称点]
                } else if(i对称点的回文区域在L...R外部){
                    pArr[i] = i...R
                } else {
                    //i对称点的回文区域左边界和l压线
                    从R外开始扩
                }
            }
        }
        return pArr最大值 / 2;

    }

     */

    // 返回回文半径
    public static int manacher(String s){
        if (s == null ||s.length() == 0){
            return 0;
        }
        char[] str = manacherStr(s);
        int[] pArr = new int[str.length];

        int R = -1;
        int C = -1;
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < str.length; i++) {

            // i位置扩，至少不用验的区域
            pArr[i] = R > i ? Math.min(pArr[2 * C - i],R - i) :1;

            while (i+pArr[i] < str.length && i - pArr[i] > -1){
                if(str[i+pArr[i]] == str[i-pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }

            // 刷新边界R
            if (i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);

        }
        return max - 1;

    }

    private static char[] manacherStr(String s) {
        char[] charArray = s.toCharArray();
        char[] res = new char[s.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArray[index++];
        }
        return res;
    }

}
class f{
    private static char[] manacherStr(String s) {
        int n = s.length();
        char[] res = new char[n * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : s.charAt(index++);
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
