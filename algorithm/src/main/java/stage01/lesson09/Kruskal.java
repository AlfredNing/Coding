package stage01.lesson09;

import java.util.*;

/**
 * @ClassName Kruskal
 * @Description Kruskal 最小生成树
 * @Author Alfred.Ning
 * @Date 2022/5/22 14:40
 * @Version 1.0
 **/
public class Kruskal {
    public static class UnionFind {
        // node ,指向上层节点
        private HashMap<Node, Node> fatherMap;
        // key 某一个集合的代表节点, value key所在集合的节点个数
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Node findFather(Node node) {
            Stack<Node> path = new Stack<>();
            while (node != fatherMap.get(node)) {
                path.add(node);
                node = fatherMap.get(node);
            }
            // 扁平化
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), node);
            }
            return node;
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aDai = findFather(a);
            Node bDai = findFather(b);
            if (aDai != bDai) {
                Integer aSize = sizeMap.get(aDai);
                Integer bSize = sizeMap.get(bDai);
                if (aSize <= bSize) {
                    fatherMap.put(aDai, bDai);
                    sizeMap.put(bDai, aSize + bSize);
                    sizeMap.remove(aDai);
                } else {
                    fatherMap.put(bDai, aDai);
                    sizeMap.put(aDai, aSize + bSize);
                    sizeMap.remove(bDai);
                }
            }
        }
    }

    static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        // 所有顶点集合进入并查集
        unionFind.makeSets(graph.nodes.values());
        // 从小到大
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        // 获取权值小的边
        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }
        HashSet<Edge> res = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                res.add(edge);
                unionFind.union(edge.from, edge.to);// 连接两个集合
            }
        }
        return res;
    }

}
