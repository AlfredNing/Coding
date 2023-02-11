package stage01.lesson08;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName UnionFind
 * @Description 并差集
 * @Author Alfred.Ning
 * @Date 2022/5/21 15:39
 * @Version 1.0
 **/
public class UnionFindSet {
    public static class Node<T> {
        T value;

        public Node(T t) {
            value = t;
        }
    }

    public static class UnionFind<T> {
        // node的表示点
        public HashMap<T, Node<T>> nodes;
        // 指向上层节点
        public HashMap<Node<T>, Node<T>> parents;
        // 只有一个点，代表点，才有记录
        public HashMap<Node<T>, Integer> sizeMap;

        public UnionFind(List<T> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (T cur : values) {
                Node<T> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public boolean isSameSet(T a, T b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)){
                return false;
            }
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(T a, T b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)){
                return;
            }
            Node<T> aHead = findFather(nodes.get(a));
            Node<T> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                Integer aSize = sizeMap.get(aHead);
                Integer bSize = sizeMap.get(bHead);
                Node<T> big = aSize > bSize ? aHead : bHead;
                Node<T> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSize + bSize);
                sizeMap.remove(small);
            }
        }

        //        给你一个节点，请你往上到不能再往上，把代表返回
        private Node<T> findFather(Node<T> cur) {
            Stack<Node<T>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            // 扁平化 - 优化
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        public int sets() {
            return sizeMap.size();
        }
    }


}
