package stage03.lesson07;

import java.util.HashMap;

/**
 * LRU内存替换实现.
 *
 * @author Alfred.Ning
 */
public class LeastRecentlyUsedCache {
    public static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> last;
        public Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // 双向链表
    public static class NodeDoubleLinkedList<K, V> {
        private Node<K, V> head;// 头指针
        private Node<K, V> tail;// 尾指针

        public NodeDoubleLinkedList() {
            head = null;
            tail = null;
        }

        // 新节点加入，放入到尾指针所指向
        public void addNode(Node<K, V> newNode) {
            if (newNode == null) {
                return;
            }
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.last = tail;
                tail = newNode;
            }
        }

        // 使用到一个节点，将该节点调整到指针指向的尾部
        public void moveNodeToTail(Node<K, V> node) {
            if (this.tail == node) {
                return;
            }
            if (this.head == node) {
                this.head = node.next;
                this.head.last = null;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }
            node.last = this.tail;
            node.next = null;
            this.tail.next = node;
            this.tail = node;
        }

        // 从头移除一个节点
        public Node<K, V> removeFromHead() {
            if (this.head == null) {
                return null;
            }
            Node<K, V> res = this.head;
            if (this.head == this.tail) {
                this.head = null;
                this.tail = null;
            } else {
                this.head = res.next;
                res.next = null;
                this.head.last = null;
            }
            return res;
        }
    }

    // LRU缓存实现
    public static class LRUCache<K, V> {
        private HashMap<K, Node<K, V>> keyNodeMap;
        private NodeDoubleLinkedList<K, V> nodeList;
        private final int capacity;

        public LRUCache(int cap) {
            if (cap < 1) {
                throw new RuntimeException("more than 0");
            }
            keyNodeMap = new HashMap<>();
            nodeList = new NodeDoubleLinkedList<>();
            this.capacity = cap;
        }

        public V get(K key) {
            if (keyNodeMap.containsKey(key)) {
                Node<K, V> res = keyNodeMap.get(key);
                nodeList.moveNodeToTail(res);
                return res.value;
            }
            return null;
        }

        public void set(K key, V value) {
            if (keyNodeMap.containsKey(key)) {
                // update
                Node<K, V> node = keyNodeMap.get(key);
                node.value = value;
                nodeList.moveNodeToTail(node);
            } else {
                // insert
                Node node = new Node(key, value);
                keyNodeMap.put(key, node);
                nodeList.addNode(node);
                if (keyNodeMap.size() == capacity + 1) {
                    removeMostUnusedCache();
                }
            }
        }

        private void removeMostUnusedCache() {
            Node<K, V> removeNode = nodeList.removeFromHead();
            keyNodeMap.remove(removeNode.key);
        }
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(3);
        cache.set("1", 11);
        cache.set("2", 22);
        cache.set("3", 33);
        System.out.println(cache.nodeList);
        System.out.println(cache.get("2"));
        cache.set("4", 444);
        System.out.println(cache.nodeList);
    }
}
