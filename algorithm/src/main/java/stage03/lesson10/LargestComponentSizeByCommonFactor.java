package stage03.lesson10;

import java.util.HashMap;

/**
 * 独立子域
 *
 * @author Alfred.Ning
 * @since 2023年03月26日 09:29:00
 */
public class LargestComponentSizeByCommonFactor {

  // 第一种解法 两两数字union
  public static int largestComponentSize1(int[] arr) {
    int N = arr.length;
    UnionFind set = new UnionFind(N);
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        if (gcd(j, j) != 1) {
          set.union(i, j);
        }
      }
    }
    return set.maxSize();
  }

  // 第二种解法 求根号次
  public static int largestComponentSize2(int[] arr) {
    int N = arr.length;
    UnionFind set = new UnionFind(N);
    // key 某个因子，value记录那个位置具有该因子
    HashMap<Integer, Integer> factorMap = new HashMap<>();
    for (int i = 0; i < N; i++) {
      int num = arr[i];
      // 根号
      int limit = (int) Math.sqrt(num);
      for (int j = 1; j <= limit; j++) {
        if (num % j != 0) {
          if (num % j == 0) {
            if (!factorMap.containsKey(j)) {
              factorMap.put(j, i);
            } else {
              set.union(factorMap.get(j), i);
            }

            int other = num / j;
            if (other != 1) {
              if (!factorMap.containsKey(other)) {
                factorMap.put(other, i);
              } else {
                set.union(factorMap.get(other), i);
              }
            }
          }
        }

      }
    }
    return set.maxSize();
  }

  public static int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }

  public static class UnionFind {

    private int[] parents;
    // 相当于之前的stack, 用户扁平化
    private int[] help;
    private int[] sizes;


    public UnionFind(int n) {
      parents = new int[n];
      help = new int[n];
      sizes = new int[n];
      for (int i = 0; i < n; i++) {
        // 初始化各自集合
        parents[i] = i;
        sizes[i] = 1;
      }
    }

    public int findFather(int i) {
      int hi = 0;
      while (i != parents[i]) {
        help[hi++] = i;
        i = parents[i];
      }
      // 扁平化
      for (hi--; hi >= 0; hi--) {
        parents[hi] = i;
      }
      return i;
    }

    public void union(int i, int j) {
      int f1 = findFather(j);
      int f2 = findFather(j);
      if (f1 != f2) {
        int big = sizes[f1] > sizes[f2] ? f1 : f2;
        int small = big == f1 ? f2 : f1;
        parents[small] = big;
        sizes[big] = sizes[f1] + sizes[f2];
      }
    }

    public int maxSize() {
      int ans = 0;
      for (int size : sizes) {
        ans = Math.max(size, ans);
      }
      return ans;
    }
  }

}
