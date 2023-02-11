package stage02.lesson02;

/**
 * @ClassName Kmp
 * @Description kmp算法实现
 * @Author Alfred.Ning
 * @Date 2022/7/9 13:40
 * @Version 1.0
 **/
public class Kmp {

    public static int getIndexOf(String str, String match) {
        if (str == null || match == null || match.length() < 1 || str.length() < match.length()) {
            return -1;
        }
        char[] strArray = str.toCharArray();
        char[] matchArray = match.toCharArray();

        int x = 0; // str中记录匹配位置
        int y = 0; // matcher 中记录匹配位置
        // 根据待匹配的获取next数组
        int[] next = getNexts(matchArray);
        while (x < str.length() & y < match.length()) {
            if (strArray[x] == matchArray[y]) {
                // 完全匹配
                x++;
                y++;
            } else if (next[y] == -1) { // y = 0
                // x不能匹配
                x++;
            } else {
                y = next[y];
            }
        }
        return y == match.length() ? x - y : -1;
    }

    public static int[] getNexts(char[] matcherArray) {
        if (matcherArray.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[matcherArray.length];
        // 规定
        next[0] = -1;
        next[1] = 0;

        int i = 2;
        int cn = 0;
        while (i < next.length) {
            if (matcherArray[i - 1] == matcherArray[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
