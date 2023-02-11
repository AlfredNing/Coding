package stage03.lesson04;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Alfred.Ning
 */
public class TopKTimes {
    public static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            this.str = s;
            this.times = t;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.times - o2.times;
        }
    }

    public static void printTopkAndRank(String[] arr, int topK) {
        if (arr == null || arr.length == 0 || topK < 1) {
            return;
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : arr) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        topK = Math.min(arr.length, topK);

        PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            if (heap.size() < topK){
                heap.add(node);
            }else {
                if (heap.peek().times < node.times){
                    heap.poll();
                    heap.add(node);
                }
            }
        }
        while (!heap.isEmpty()) {
            System.out.println(heap.poll().str);
        }

    }

}
