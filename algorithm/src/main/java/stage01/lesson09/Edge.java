package stage01.lesson09;

/**
 * @ClassName Edge
 * @Description 图中的边
 * @Author Alfred.Ning
 * @Date 2022/5/22 13:34
 * @Version 1.0
 **/
public class Edge {
    // 权值
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
