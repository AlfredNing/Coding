package tutorial;

import lombok.Value;

import java.security.Key;

public class CustomHashMap<K, V> {
    // hashmap元素个数
    private int size;
    private final int DEFAULT_CAPACITY = 16;
    private final float LOAD_FACTOR = 0.75f;

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node<K, V>[] buckets;

    public CustomHashMap() {
        this.buckets = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    public CustomHashMap(int capacity) {
        this.buckets = new Node[capacity];
        size = 0;
    }

    // hash索引
    private int getIndex(K key, int length) {
        return Math.abs(key.hashCode() % length);
    }

    public V get(K key) {
        int index = getIndex(key, buckets.length);
        Node<K, V> node = buckets[index];
        if (node == null) {
            return null;
        }

        while (node != null) {
            if ((node.key.hashCode() == key.hashCode())
                    && (node.key == key || node.key.equals(key))
            ) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public void put(K key, V value) {
        if (size >= buckets.length * LOAD_FACTOR) {
            resize();
        }
        putVal(key, value, this.buckets);
    }

    private void resize() {
        Node<K, V>[] newBuckets = new Node[buckets.length * 2];
        rehash(newBuckets);
        this.buckets = newBuckets;
    }

    private void rehash(Node<K, V>[] newBuckets) {
        size = 0;
        for (int i = 0; i < buckets.length; i++) {
            Node<K, V> node = buckets[i];
            if (node == null) {
                continue;
            }
            while (node != null) {
                putVal(node.key, node.value, newBuckets);
                node = node.next;
            }
        }
    }

    private void putVal(K key, V value, Node<K, V>[] buckets) {
        int index = getIndex(key, buckets.length);
        Node<K, V> node = buckets[index];
        // 没有该元素
        if (node == null) {
            Node<K, V> kvNode = new Node<>(key, value);
            buckets[index] = kvNode;
            size++;
            return;
        }
        while (node != null) {
            if ((node.key.hashCode() == key.hashCode())
                    && (node.key == key || node.key.equals(key))
            ) {
                // 认为元素重复
                node.value = value;
                return;
            }
            node = node.next;

        }

        // bucket位置冲突，但没有相同key
        Node<K, V> newNode = new Node(key, value, buckets[index]);
        buckets[index] = newNode;
        size++;
    }
}
