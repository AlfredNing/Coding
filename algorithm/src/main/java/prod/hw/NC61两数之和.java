package prod.hw;

import java.util.HashMap;

/**
 * @ClassName NC61两数之和
 * @Author 宁强-34436
 * @Date 2022/5/23 16:06
 * @Email qiang.ning@going-link.com
 * @Descirption
 **/
public class NC61两数之和 {
  /**
   * @param numbers int整型一维数组
   * @param target  int整型
   * @return int整型一维数组
   */
  public int[] twoSum1(int[] numbers, int target) {
    int n = numbers.length;
    int[] res = {-1, 1};
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (numbers[i] + numbers[j] == target) {
          res[0] = i + 1;
          res[1] = j + 1;
        }
      }
    }
    return res;
  }
  public int[] twoSum2(int[] numbers, int target) {
    int[] res = new int[0];
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < numbers.length; i++) {
      int temp = target - numbers[i];
      if (map.containsKey(temp)) {
        return new int[] {map.get(temp) + 1, i + 1};
      } else {
        map.put(numbers[i], i);
      }
    }
    return res;
  }
}
