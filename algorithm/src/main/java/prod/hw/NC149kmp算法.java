package prod.hw;

/**
 * @ClassName NC149kmp算法
 * @Author 宁强-34436
 * @Date 2022/5/25 14:55
 * @Email qiang.ning@going-link.com
 * @Descirption
 **/
public class NC149kmp算法 {
    public int kmp(String str, String t) {
        if (str.length() < t.length()) {
            System.out.println(0);
        }
        return find(str, t);
    }

    // str给定文本字符串，t是模式
    private int find(String str, String t) {
        int strLen = str.length();
        int patternLen = t.length();
        char[] strArray = str.toCharArray();
        char[] par = t.toCharArray();
        // 前缀表 next数组 next存储的是当前位置包含当前位置的子串相同前后缀长度
        int[] nexts = new int[patternLen];
        nexts[0] = -1; // 以整体减1实现
        int k = -1;
        // 初始化前缀表
        for (int i = 1; i < patternLen; i++) {
            while (k != -1 && par[k + 1] != par[i]) { // 前后缀不相同
                k = nexts[k];  // 向前回退
            }
            //while (k >= 0 && par[k + 1] != par[i]) {
            //  k = nexts[k];
            //}
            if (par[k + 1] == par[i]) { // 前后缀相同
                k++;
            }
            nexts[i] = k; // 将k（前缀的长度）赋给nexts[i]
        }
        k = -1;
        int res = 0;
        for (int i = 0; i < strLen; i++) {
            if (strArray[i] == par[k + 1]) {
                // 匹配成功i++，k++
                k++;
            } else {
                // 匹配失败继续向前搜索
                while (k != -1 && par[k + 1] != strArray[i]) {
                    k = nexts[k];
                }
            }
            if (k == patternLen - 1) {
                k = -1;
                i = i - patternLen + 2;
                res++;

            }
        }
        return res;
    }

    ////////////////////// 优化后的结果

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 计算模板串S在文本串T中出现了多少次
     *
     * @param S string字符串 模板串
     * @param T string字符串 文本串
     * @return int整型
     */
    public int kmp2(String S, String T) {
        // write code here
        if (S == null || S.length() == 0 || T == null || T.length() == 0) {
            return 0;
        }
        int[] nexts = helper(S);
        int sIdx = 0;
        int tIdx = 0;
        int m = S.length();
        int n = T.length();
        int count = 0;

        while (tIdx < n) {
            if (tIdx == -1 || S.charAt(sIdx) == T.charAt(tIdx)) {
                tIdx++;
                sIdx++;
            } else {
                sIdx = nexts[sIdx];
            }
            if (sIdx == m) {
                count += 1;
                sIdx = nexts[sIdx];
            }
        }

        return count;
    }

    private int[] helper(String S) {
        char[] chs = S.toCharArray();
        int n = chs.length;
        int[] nexts = new int[n + 1];
        nexts[0] = -1;
        nexts[1] = 0;
        int i = 2;
        int j = 0;

        while (i <= n) {
            if (j == -1 || chs[i - 1] == chs[j]) {
                j++;
                nexts[i] = j;
                i++;
            } else {
                j = nexts[j];
            }
        }
        return nexts;
    }

    // kmp算法暴力求解 pat 模式串 txt 文本串
    public int kmpSearch(String pat, String txt) {
        int txtLen = txt.length();
        int patLen = pat.length();
        for (int i = 0; i < txtLen; i++) {
            int j;
            for (j = 0; j < patLen; j++) {
                if (pat.charAt(j) != txt.charAt(i + j)){
                    break;
                }
            }
            if (j == patLen) { // 匹配成功
                return i;
            }
        }
        return -1; // 未匹配成功
    }
}
