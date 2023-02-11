package stage03.lesson03;

import java.util.PriorityQueue;

/**
 * @author Alfred.Ning
 */
public class TrappinGRainWater2 {
  public static class Node {
    public int value;
    public int row;
    public int col;

    public Node(int value, int row, int col) {
      this.value = value;
      this.row = row;
      this.col = col;
    }
  }

  public static int maxWater(int[][] arr) {
    if (arr == null || arr.length == 0 || arr[0] == null || arr[0].length == 0) {
      return 0;
    }
    int M = arr.length;
    int N = arr[0].length;
    boolean[][] flag = new boolean[M][N];
    PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.value - b.value);

    // 最外层一圈加进去
    for (int col = 0; col < N - 1; col++) {
      flag[0][col] = true;
      heap.add(new Node(arr[0][col], 0, col));
    }
    for (int row = 0; row < M - 1; row++) {
      flag[row][N - 1] = true;
      heap.add(new Node(arr[row][N - 1], row, N - 1));
    }
    for (int col = N - 1; col > 0; col--) {
      flag[M - 1][col] = true;
      heap.add(new Node(arr[M - 1][col], M - 1, col));
    }
    for (int row = M - 1; row > 0; row--) {
      flag[row][0] = true;
      heap.add(new Node(arr[row][0], row, 0));
    }

    int water = 0;
    int max = 0;
    while (!heap.isEmpty()) {
      Node cur = heap.poll();
      int r = cur.row;
      int c = cur.col;
      // 加入上下左右
      if (r > 0 && !flag[r - 1][c]) {
        water += Math.max(0, max - arr[r - 1][c]);
        flag[r - 1][c] = true;
        heap.add(new Node(arr[r - 1][c], r - 1, c));
      }
      if (r < N - 1 && !flag[r + 1][c]) {
        water += Math.max(0, max - arr[r + 1][c]);
        flag[r + 1][c] = true;
        heap.add(new Node(arr[r + 1][c], r + 1, c));
      }
      if (c > 0 && !flag[r][c - 1]) {
        water += Math.max(0, max - arr[r][c - 1]);
        flag[r][c - 1] = true;
        heap.add(new Node(arr[r][c - 1], r, c - 1));
      }
      if (c < N -1 && !flag[r][c + 1]) {
        water += Math.max(0, max - arr[r][c + 1]);
        flag[r][c + 1] = true;
        heap.add(new Node(arr[r][c + 1], r, c + 1));
      }
    }
    return water;
  }
}
