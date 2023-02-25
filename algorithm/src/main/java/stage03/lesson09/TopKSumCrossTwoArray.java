package stage03.lesson09;

import static stage03.lesson09.TopKSumCrossTwoArray.Node.topKSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Alfred.Ning
 * @since 2023年02月25日 10:43:00
 */
public class TopKSumCrossTwoArray {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StreamTokenizer in = new StreamTokenizer(br);
    PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    while (in.nextToken() != StreamTokenizer.TT_EOF) {
      int N = (int) in.nval;
      in.nextToken();
      int K = (int) in.nval;
      int[] arr1 = new int[N];
      int[] arr2 = new int[N];
      for (int i = 0; i < N; i++) {
        in.nextToken();
        arr1[i] = (int) in.nval;
      }
      for (int i = 0; i < N; i++) {
        in.nextToken();
        arr2[i] = (int) in.nval;
      }
      int[] topK = topKSum(arr1, arr2, K);
      for (int i = 0; i < K; i++) {
        out.print(topK[i] + " ");
      }
      out.println();
      out.flush();
    }
  }

  // 大根堆结构
  public static class Node {

    public int index1; // arr1的位置
    public int index2; // arr2的位置
    public int sum; // arr1[index1] + arr2[index2]

    public Node(int index1, int index2, int sum) {
      this.index1 = index1;
      this.index2 = index2;
      this.sum = sum;
    }

    // 生成大根堆的比较器
    public static class MaxHeapComp implements Comparator<Node> {

      @Override
      public int compare(Node o1, Node o2) {
        return o2.sum - o1.sum;
      }
    }

    public static int[] topKSum(int[] arr1, int[] arr2, int topK) {
      if (arr1 == null || arr2 == null || topK < 1) {
        return null;
      }
      // 健壮性，避免入参k不合理
      topK = Math.min(topK, arr1.length * arr2.length);

      int[] res = new int[topK];
      int resIndex = 0;

      PriorityQueue<Node> maxHeap = new PriorityQueue<>(new MaxHeapComp());

      // 标识已经访问过
      boolean[][] set = new boolean[arr1.length][arr2.length];
      int i1 = arr1.length - 1;
      int i2 = arr2.length - 1;

      // 初始化右下角
      maxHeap.add(new Node(i1, i2, arr1[i1] + arr2[i2]));
      set[i1][i2] = true; // true表示访问过

      while (resIndex != topK) {
        Node curNode = maxHeap.poll();
        res[resIndex++] = curNode.sum;
        i1 = curNode.index1;
        i2 = curNode.index2;
        if (i1 - 1 >= 0 && !set[i1 - 1][i2]) { // 保证上方有数
          set[i1 - 1][i2] = true;
          maxHeap.add(new Node(i1 - 1, i2, arr1[i1 - 1] + arr2[i2]));
        }
        if (i2 - 1 >= 0 && !set[i1][i2 - 1]) { // 保证左方有数
          set[i1][i2 - 1] = true;
          maxHeap.add(new Node(i1, i2 - 1, arr1[i1] + arr2[i2 - 1]));
        }
      }
      return res;
    }

  }

}
