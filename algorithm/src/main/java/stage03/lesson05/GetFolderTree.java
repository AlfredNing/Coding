package stage03.lesson05;

import java.util.TreeMap;

/**
 * 打印目录树结构.
 * trie树
 *
 * @author Alfred.Ning
 */
public class GetFolderTree {
    // 前缀树node节点
    public static class Node {
        // 保存是由哪条路径下来的
        public String path;
        // treeMap保证输出有序
        public TreeMap<String, Node> nextMap;

        public Node(String p) {
            this.path = p;
            nextMap = new TreeMap<>();
        }
    }

    public static void print(String[] stringPath) {
        if (stringPath == null || stringPath.length == 0) {
            return;
        }
        // 建立前缀树
        Node head = generateFolderTree(stringPath);

        // 打印
        printProcess(head, 0);
    }

    // 打印，level当前层
    private static void printProcess(Node node, int level) {
        if (level != 0) {
            System.out.println(get4nSpace(level) + node.path);
        }
        for (Node next : node.nextMap.values()) {
            printProcess(next, level + 1);
        }

    }

    private static String get4nSpace(int level) {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < level; i++) {
            res.append("    ");

        }
        return res.toString();
    }

    private static Node generateFolderTree(String[] stringPath) {
        Node root = new Node("");// 顶层节点
        for (String folderPath : stringPath) {
            String[] path = folderPath.split("\\\\");
            Node cur = root;
            for (int i = 0; i < path.length; i++) {
                if (!cur.nextMap.containsKey(path[i])) {
                    cur.nextMap.put(path[i], new Node(path[i]));
                }
                cur = cur.nextMap.get(path[i]);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        String[] stringPath = {"b\\st", "d\\", "a\\d\\e", "a\\b\\c"};
        print(stringPath);
    }
}
