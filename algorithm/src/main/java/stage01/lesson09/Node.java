package stage01.lesson09;

import java.util.ArrayList;

/**
 * @ClassName Node
 * @Description 抽象出图的节点
 * @Author Alfred.Ning
 * @Date 2022/5/22 13:32
 * @Version 1.0
 **/
public class Node {
    // 节点值
    public int value;
    // 入度
    public int in;
    // 出度
    public int out;
    // 连接的节点
    public ArrayList<Node> nexts;
    // 连接的边
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
