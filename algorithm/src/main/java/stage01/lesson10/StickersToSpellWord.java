package stage01.lesson10;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName StickersToSpellWord
 * @Description 贴纸 拼接
 * @Author Alfred.Ning
 * @Date 2022/6/22 7:55
 * @Version 1.0
 **/
public class StickersToSpellWord {

    public static int A(String[] stickers, String target) {
        int n = stickers.length;
        int[][] map = new int[n][26];
        HashMap<String, Integer> dp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char c : chars) {
                map[i][c - 'a']++;
            }
        }
        dp.put("", 0);
        return process1(dp, map, target);

    }

    public static int process1(HashMap<String, Integer> dp, int[][] map, String rest) {
        if (dp.containsKey(rest)) {
            return dp.get(rest);
        }
        // 递归调用过程
        int ans = Integer.MAX_VALUE;
        int n = map.length;
        int[] tmap = new int[26];
        char[] target = rest.toCharArray();
        for (char c : target) {
            tmap[c - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            if (map[i][target[0] - 'a'] == 0) {
                continue;
            }

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (tmap[j] > 0) {
                    for (int k = 0; k < Math.max(0, tmap[j] - map[i][j]); k++) {
                        sb.append((char) ('a' + j));
                    }
                }
            }
            String s = sb.toString();
            int tmp = process1(dp, map, s);
            if (tmp != -1) {
                ans = Math.min(ans, 1 + tmp);
            }
        }
        dp.put(rest, ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(rest);
    }
}
