package stage03.lesson04;

import java.util.HashMap;

/**
 * @author Alfred.Ning
 */
public class TopKRealTimes {
    public static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            this.str = s;
            this.times = t;
        }

        public static class TopKRecord {
            private Node[] heap;
            private int heapSize;
            private HashMap<String, Node> strNodeMap;
            private HashMap<Node, Integer> nodeIndexMap;

            public TopKRecord(int k) {
                heap = new Node[k];
                heapSize = 0;
                strNodeMap = new HashMap<>();
                nodeIndexMap = new HashMap<>();
            }

            public void add(String str) {
                Node curNode = null;
                int preIndex = -1;
                if (!strNodeMap.containsKey(str)) {
                    curNode = new Node(str, 1);
                    strNodeMap.put(str, curNode);
                    nodeIndexMap.put(curNode, -1);
                } else {
                    curNode = strNodeMap.get(str);
                    curNode.times++;
                    preIndex = nodeIndexMap.get(curNode);
                }
                if (preIndex == -1) { // 不在堆上
                    if (heapSize == heap.length) { // 堆满了
                        if (heap[0].times < curNode.times) {
                            nodeIndexMap.put(heap[0], -1); // 移除堆顶元素
                            nodeIndexMap.put(curNode, 0);
                            heap[0] = curNode;
                            heapify(0, heapSize);
                        }
                    } else { // 堆没有满
                        nodeIndexMap.put(curNode, heapSize);
                        heap[heapSize] = curNode;
                        heapInsert(heapSize++);
                    }
                } else { // str已经在堆上
                    heapify(preIndex, heapSize);

                }

            }

            private void heapInsert(int index) {
                while (index != 0) {
                    int parent = (index - 1) / 2;
                    if (heap[index].times < heap[parent].times){
                        swap(parent,index);
                        index = parent;
                    } else {
                        break;
                    }
                }
            }

            private void heapify(int index, int heapSize) {
                int l = index * 2 + 1;
                int r = index * 2 + 2;
                int smallest = index;
                while (l < heapSize) {
                    if (heap[l].times < heap[index].times) {
                        smallest = l;
                    }
                    if (r < heapSize && heap[r].times < heap[smallest].times) {
                        smallest = r;
                    }
                    if (smallest != index) {
                        swap(smallest, index);
                    } else {
                        break;
                    }
                    index = smallest;
                    l = index * 2 + 1;
                    r = index * 2 + 2;
                }

            }

            private void swap(int index1, int index2) {
                nodeIndexMap.put(heap[index1], index2);
                nodeIndexMap.put(heap[index2], index1);
                Node tmp = heap[index1];
                heap[index1] = heap[index2];
                heap[index2] = tmp;
            }

            public void printK() {
                System.out.println("top: ");
                for (int i = 0; i < heap.length; i++) {
                    if (heap[i] == null) {
                        break;
                    }
                    System.out.println("Str: " + heap[i].str);
                    System.out.println("Times: " + heap[i].times);
                }
            }
        }
    }
}