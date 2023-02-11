package stage01.lesson09;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @ClassName Graph
 * @Description 图的数据结构
 * @Author Alfred.Ning
 * @Date 2022/5/22 13:36
 * @Version 1.0
 **/
public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
