package stage01.lesson10;

import stage01.lesson09.Edge;
import stage01.lesson09.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @ClassName Dijkstra
 * @Description Dijkstra算法
 * @Author Alfred.Ning
 * @Date 2022/5/28 13:56
 * @Version 1.0
 **/
public class Dijkstra {
    public static HashMap<Node, Integer> dijkstra1(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        HashSet<Node> selectedSet = new HashSet<>();
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedSet);
        while (minNode != null) {
            Integer distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node to = edge.to;
                if (!distanceMap.containsKey(to)) {
                    distanceMap.put(to, distance + edge.weight);
                } else {
                    distanceMap.put(to, Math.min(distanceMap.get(to), distance + edge.weight));
                }
            }
            selectedSet.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedSet);
        }
        return distanceMap;
    }

    // 改进后的dijkstra
    public static HashMap<Node,Integer> dijkstra2(Node head, int size){
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()){
            NodeRecord nodeRecord = nodeHeap.pop();
            Node cur = nodeRecord.node;
            int distance = nodeRecord.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to,edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }

    // 返回距离最小而且没有选择过的node
    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> touchNodes) {
        Node res = null;
        int minDistance = Integer.MIN_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!touchNodes.contains(node) && distance < minDistance) {
                res = node;
                minDistance = distance;
            }
        }
        return res;
    }


    // 结点堆
    public static class NodeHeap {
        private Node[] nodes; // 实际的堆结构
        // k中某一个node,value上面堆中的位置
        private HashMap<Node, Integer> heapIndexMap;
        // key某一个节点，从源节点到该节点的最小距离
        private HashMap<Node, Integer> distanceMap;
        // 堆上面的元素
        private int size;

        public NodeHeap(int size) {
            this.nodes = new Node[size];
            this.heapIndexMap = new HashMap<>();
            this.distanceMap = new HashMap<>();
            this.size = size;
        }

        // 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
        // 判断要不要更新，如果需要的话，就更新
        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) { // 在堆内
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(node,heapIndexMap.get(node));
            }
            if (!isEntered(node)){
                // 没有进入堆中过
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node, size++);
            }
        }

        // 是否在堆内
        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        // 是否已经添加过
        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }


        ///////////下面是基本堆结构
        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            //重新堆化
            heapify(0, --size);
            return nodeRecord;
        }

        // 插入堆化
        private void insertHeapify(Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) { //从底往上进行堆化
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        // 堆化
        private void heapify(int index, int size) {
            // 左孩子节点
            int left = index * 2 + 1;
            while (left < size) { // 没有到达堆最后面的元素
                // 选择左孩子还是右孩子节点
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                        ? left + 1 : left; //
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    // 出堆的元素下面的节点一样
                    break;
                }
                // 交换元素
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}
