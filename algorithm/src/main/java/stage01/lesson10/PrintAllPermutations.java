package stage01.lesson10;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;

/**
 * @ClassName PrintAllPermuations
 * @Description 打印字符串的全部排列
 * @Author Alfred.Ning
 * @Date 2022/5/30 8:28
 * @Version 1.0
 **/
public class PrintAllPermutations {
    // 打印字符的全部排列
    public static List<String> permutaions(String s) {
        ArrayList<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        char[] chs = s.toCharArray();
        process(chs, 0, res);
        return res;
    }

    // str[0...i-1]已经做好选择
    // index之后都有可能来到i位置
    private static void process(char[] chs, int index, ArrayList<String> res) {
        if (index == chs.length) {
            res.add(String.valueOf(chs));
        }
        for (int i = index; i < chs.length; i++) { // index后面所有字符都可以来到index位置
            swap(chs, i, index);
            process(chs, i + 1, res);
            // 恢复现场
            swap(chs, index, i);
        }
    }
    // 打印字符的全部排列 不出现重复的的排列
    public static List<String> permutaions2(String s) {
        ArrayList<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        char[] chs = s.toCharArray();
        process2(chs, 0, res);
        return res;
    }
    // 分支界限
    private static void process2(char[] chs, int index, ArrayList<String> res) {
        if (index == chs.length) {
            res.add(String.valueOf(chs));
        }
        boolean[] visited = new boolean[26]; // 0... 26
        for (int i = index; i < chs.length; i++) { // index后面所有字符都可以来到index位置
            if (!visited[chs[index] - 'a']) {
                visited[index]=true;
                swap(chs, i, index);
                process2(chs, i + 1, res);
                // 恢复现场
                swap(chs, index, i);
            }

        }
    }

    private static void swap(char[] chs, int i, int j) {
        char temp = chs[i];
        chs[i] = chs[j];
        chs[j] = temp;
    }
}
