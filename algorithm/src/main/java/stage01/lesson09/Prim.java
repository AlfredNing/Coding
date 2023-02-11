package stage01.lesson09;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @ClassName Prim
 * @Description 最小生成树Prim
 * @Author Alfred.Ning
 * @Date 2022/5/22 15:07
 * @Version 1.0
 **/
public class Prim {
    static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
    public  static Set<Edge> primMST(Graph graph) {
        // 那些点被解锁
        HashSet<Node> nodeSet = new HashSet<>();
        // 解锁的边进入小根堆
        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>(new EdgeComparator());
        HashSet<Edge> res = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            nodeSet.add(node);
            // 进入小根堆
            edgePriorityQueue.addAll(node.edges);

            // 挑选阶段
            while (!edgePriorityQueue.isEmpty()){
                Edge edge = edgePriorityQueue.poll();
                Node toNode = edge.to;
                if (!nodeSet.contains(toNode)){
                    nodeSet.add(toNode);
                    res.add(edge);
                    edgePriorityQueue.addAll(toNode.edges);
                }
            }
        }
        return res;
    }
}
