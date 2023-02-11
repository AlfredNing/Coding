package stage01.lesson08;

import java.util.HashSet;

/**
 * @ClassName Light
 * @Description 点亮居民所有需要的灯-贪心
 * @Author Alfred.Ning
 * @Date 2022/5/21 14:27
 * @Version 1.0
 **/
public class Light {
    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<Integer>());
    }

    // index 当前位置 lights 已经点亮的灯  -- 暴力
    private static int process(char[] strs, int index, HashSet<Integer> lights) {
        // 结束
        if (index == strs.length) {
            for (int i = 0; i < strs.length; i++) {
                if (strs[i] != 'X') {
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        // 返回无效解
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {
            // index位置没有放灯
            int no = process(strs, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (strs[index] == '.') {
                lights.add(index);
                // index位置放灯
                yes = process(strs, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    public static int minLight2(String road) {
        char[] str = road.toCharArray();
        int i = 0, light = 0;
        while (i < str.length) {
            if (str[i] == 'X') {
                i++;
            } else {
                light++;
                if (i + 1 == str.length) {
                    break;
                } else {
                    // i位置有灯，看i+2位置
                    if (str[i + 1] == 'X') {
                        i = i + 2;
                    } else {
                        i = i + 3;
                    }
                }
            }
        }
        return light;
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}
