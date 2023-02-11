package stage01.lesson09;

/**
 * @ClassName GraphGenerator
 * @Description 生成图
 * @Author Alfred.Ning
 * @Date 2022/5/22 13:37
 * @Version 1.0
 **/
public class GraphGenerator {
    // matrix j矩阵 【2,3,5】 【权值，from, to】
    public static Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);
            fromNode.out++;
            toNode.in++;
            graph.edges.add(edge);
        }
        return graph;
    }
}
