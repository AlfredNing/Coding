package stage03.lesson02;

/**
 * 数组构造
 *
 * @author Alfred.Ning
 */
public class Make_NO {
  // 对于任意的数组【i，j, k] [i] + [k] != 2[j]
  public static int[] makeNo(int size) {
    if (size == 1) {
      return new int[] {1};
    }
    int resSize = (size + 1) / 2;
    // 获取种子数据
    int[] base = makeNo(resSize);
    int[] ans = new int[size];
    int index = 0;
    // 加工数据
    for (; index < resSize; index++) {
      ans[index] = base[index] * 2 - 1;
    }
    // 补全数据
    for (int i = 0; index < size; index++, i++) {
      ans[index] = base[i] * 2;
    }
    return ans;
  }

  // 检验函数
  public static boolean isValid(int[] arr) {
    int N = arr.length;
    for (int i = 0; i < N; i++) {
      for (int k = i + 1; k < N; k++) {
        for (int j = k + 1; j < N; j++) {
          if (arr[i] + arr[j] == 2 * arr[k]) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println("test begin");
    for (int N = 1; N < 1000; N++) {
      int[] arr = makeNo(N);
      if (!isValid(arr)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("test end");
    System.out.println(isValid(makeNo(1042)));
    System.out.println(isValid(makeNo(2981)));
  }

}
