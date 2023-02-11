package stage03.lesson01;

/**
 * 最大正方形边长
 *
 * @author Alfred.Ning
 */
public class MaxOneBoardSize {
  // 生成辅助数组
  public static void setBorderMap(int[][] m, int[][] right, int[][] down) {
    int r = m.length;
    int c = m[0].length;

    if (m[r - 1][c - 1] == 1) {
      right[r - 1][c - 1] = 1;
      down[r - 1][c - 1] = 1;
    }
    // 设定左边位置界限值
    for (int i = r - 2; i != -1; i--) {
      if (m[i][c - 1] == 1) {
        right[i][c - 1] = 1;
        down[i][c - 1] = down[i + 1][c - 1] + 1;
      }
    }
    // 设定下边位置界限值
    for (int i = r - 2; i != -1; i--) {
      if (m[r - 1][i] == 1) {
        right[r - 1][i] = right[r - 1][i + 1] + 1;
        down[r - 1][i] = 1;
      }
    }
    for (int i = r - 2; i != -1; i--) {
      for (int j = c - 2; j != -1; j--) {
        if (m[i][j] == 1) {
          right[i][j] = right[i][j + 1] + 1;
          down[i][j] = down[i + 1][j] + 1;
        }
      }
    }
  }

  public static int getMaxSize(int[][] m) {
    int r = m.length;
    int c = m[0].length;
    int[][] right = new int[r][c];
    int[][] down = new int[r][c];
    setBorderMap(m, right, down);

    // 从边长开始
    for (int size = Math.min(r, c); size != 0; size--) {
      if (hasSizeOfBorder(size, right, down)) {
        return size;
      }
    }
    return 0;
  }

  private static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
    for (int i = 0; i != right.length; i++) {
      for (int j = 0; j != right[0].length - size + 1; j++) {
        if (right[i][j] >= size
            && down[i][j] >= size // 说明存在 合理的正方形
            && right[i + size - 1][j] >= size // 下方的点满足
            && down[i][j + size - 1] >= size) { // 右方的点满足
          return true;
        }
      }
      return false;
    }

    return false;
  }
}
