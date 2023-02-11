package stage03.lesson07;

import java.util.*;

/**
 * 最短变换路径.
 *
 * @author Alfred.Ning
 */
public class WordMinPaths {
    public static List<List<String>> findMinPaths(
            String start,
            String end,
            List<String> list
    ) {
        list.add(start);
        HashMap<String, ArrayList<String>> nexts = getNexts(list); // 生成所有的邻接表

        // 任何位置到start的距离
        HashMap<String, Integer> distances = getDistances(start, nexts);// 图的距离
        LinkedList<String> pathList = new LinkedList<>();
        List<List<String>> res = new ArrayList<>();
        getShortestPaths(start, end, nexts, distances, pathList, res);
        return res;
    }

    // 收集所有最短路径
    private static void getShortestPaths(String cur, String to, HashMap<String, ArrayList<String>> nexts, HashMap<String, Integer> distances, LinkedList<String> path, List<List<String>> res) {
        path.add(cur);
        if (to.equals(cur)) {
            res.add(new LinkedList<String>(path));
        } else {
            for (String next : nexts.get(cur)) {
                // 最短路径要求只能加1
                if (distances.get(next) == distances.get(cur) + 1) {
                    getShortestPaths(next, to, nexts, distances, path, res);
                }
            }
        }
        path.pollLast();
    }

    private static HashMap<String, Integer> getDistances(String start, HashMap<String, ArrayList<String>> nexts) {
        // 宽度有限遍历
        HashMap<String, Integer> distances = new HashMap<>();
        distances.put(start, 0);
        LinkedList<String> queue = new LinkedList<>();
        queue.add(start);
        HashSet<String> set = new HashSet<>();
        set.add(start);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            for (String next : nexts.get(cur)) {
                if (!set.contains(next)) {
                    distances.put(next, distances.get(cur) + 1);
                    queue.add(next);
                    set.add(next);
                }
            }
        }
        return distances;
    }

    private static HashMap<String, ArrayList<String>> getNexts(List<String> words) {
        // list的所有东西放入set
        HashSet<String> dict = new HashSet<>(words);
        HashMap<String, ArrayList<String>> nexts = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            nexts.put(words.get(i), getNext(words.get(i), dict));
        }
        return nexts;
    }

    private static ArrayList<String> getNext(String word, HashSet<String> dict) {
        ArrayList<String> res = new ArrayList<>();
        char[] chs = word.toCharArray();
        for (char cur = 'a'; cur <= 'z'; cur++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] != cur) {
                    char temp = chs[i];
                    chs[i] = cur;
                    if (dict.contains(String.valueOf(chs))) {
                        res.add(String.valueOf(chs));
                    }
                    chs[i] = temp;
                }
            }
        }
        return res;
    }
}
