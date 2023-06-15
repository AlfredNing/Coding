package stage02.lesson07;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 连岛问题
 */
public class IsLands {

  public static int numsIsLands(char[][] board) {
    int isLands = 0;
    int N = board.length;
    int M = board[0].length;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (board[i][j] == '1') {
          isLands++;
          infect(board, i, j, N, M);
        }
      }
    }
    return isLands;
  }

  private static void infect(char[][] board, int i, int j, int n, int m) {
    if (i < 0 || i >= n || j < 0 || j >= m || board[i][j] != 1) {
      return;
    }
    board[i][j] = 2;
    infect(board, i + 1, j, n, m);
    infect(board, i - 1, j, n, m);
    infect(board, i, j + 1, n, m);
    infect(board, i, j - 1, n, m);
  }
}
