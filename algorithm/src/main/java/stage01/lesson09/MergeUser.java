package stage01.lesson09;

import stage01.lesson08.UnionFindSet;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName MergeUser
 * @Description 用户并查集
 * @Author Alfred.Ning
 * @Date 2022/5/22 13:26
 * @Version 1.0
 **/
public class MergeUser {
    public static class User {
        public String a;
        public String b;
        public String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static class UnionFind<T> {
        // node的表示点
        public HashMap<T, UnionFindSet.Node<T>> nodes;
        // 指向上层节点
        public HashMap<UnionFindSet.Node<T>, UnionFindSet.Node<T>> parents;
        // 只有一个点，代表点，才有记录
        public HashMap<UnionFindSet.Node<T>, Integer> sizeMap;

        public UnionFind(List<T> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (T cur : values) {
                UnionFindSet.Node<T> node = new UnionFindSet.Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public boolean isSameSet(T a, T b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(T a, T b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return;
            }
            UnionFindSet.Node<T> aHead = findFather(nodes.get(a));
            UnionFindSet.Node<T> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                Integer aSize = sizeMap.get(aHead);
                Integer bSize = sizeMap.get(bHead);
                UnionFindSet.Node<T> big = aSize > bSize ? aHead : bHead;
                UnionFindSet.Node<T> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSize + bSize);
                sizeMap.remove(small);
            }
        }

        //        给你一个节点，请你往上到不能再往上，把代表返回
        private UnionFindSet.Node<T> findFather(UnionFindSet.Node<T> cur) {
            Stack<UnionFindSet.Node<T>> path = new Stack<>();
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

        public int getNumberSize() {
            return sizeMap.size();
        }
    }

    public static int mergeUsers(List<User> users) {
        UnionFind<User> userUnionFind = new UnionFind<>(users);
        HashMap<String, User> mapA = new HashMap<>();
        HashMap<String, User> mapB = new HashMap<>();
        HashMap<String, User> mapC = new HashMap<>();
        for (User user : users) {
            if (mapA.containsKey(user.a)) {
                userUnionFind.union(user, mapA.get(user.a));
            } else {
                mapA.put(user.a, user);
            }
            if (mapB.containsKey(user.b)) {
                userUnionFind.union(user, mapB.get(user.b));
            } else {
                mapB.put(user.a, user);
            }
            if (mapC.containsKey(user.c)) {
                userUnionFind.union(user, mapC.get(user.c));
            } else {
                mapC.put(user.c, user);
            }
        }
        return userUnionFind.getNumberSize();
    }
}
