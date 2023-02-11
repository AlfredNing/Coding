package stage01.lesson10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * @ClassName PrintAllSubsquence
 * @Description 打印一个字符串的全部子序列
 * @Author Alfred.Ning
 * @Date 2022/5/29 21:41
 * @Version 1.0
 **/
public class PrintAllSubsequence {
    public static List<String> subs(String str) {
        char[] strArray = str.toCharArray();
        // 经过的路径
        String path = "";
        ArrayList<String> res = new ArrayList<>();
        process1(strArray, 0, path, res);
        return res;
    }

    /**
     * 来到strArray[index]位置，index之前已经处理
     *
     * @param strArray 输入的字符
     * @param index    处理的当前位置
     * @param path     处理过的路径
     * @param res      返回结果
     */
    private static void process1(char[] strArray, int index, String path, ArrayList<String> res) {
        if (index == strArray.length) { // 达到Base case
            res.add(path);
            return;
        }
        // index位置没有选择
        process1(strArray, index + 1, path, res);
        // index位置被选择
        process1(strArray, index + 1, path + strArray[index], res);
    }

    public static List<String> subsNoRepeat(String str) {
        char[] strArray = str.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();
        process2(strArray, 0, path, set);
        return new ArrayList<>(set);
    }

    /**
     * 来到strArray[index]位置，index之前已经处理
     *
     * @param strArray 输入
     * @param index    当前位置
     * @param path     路径
     * @param set      临时保存结果
     */
    private static void process2(char[] strArray, int index, String path, HashSet<String> set) {
        if (index == strArray.length) {
            set.add(path);
            return;
        }
        process2(strArray, index + 1, path, set);
        process2(strArray, index + 1, path + strArray[index], set);
    }
    public static void main(String[] args) {
        String test = "abca";
        List<String> ans1 = subs(test);
        List<String> ans2 = subsNoRepeat(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");

    }
}
