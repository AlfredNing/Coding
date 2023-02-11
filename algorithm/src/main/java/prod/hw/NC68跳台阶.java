package prod.hw;

/**
 * @ClassName NC68跳台阶
 * @Author 宁强-34436
 * @Date 2022/5/23 15:00
 * @Email qiang.ning@going-link.com
 * @Descirption
 **/
public class NC68跳台阶 {
  // 时间复杂度为2^n 会超时
  public static int jumpFloor1(int target) {
    if (target <= 1) {
      return 1;
    }
    return jumpFloor1(target - 1) + jumpFloor1(target - 2);
  }

  static int[] data = new int[50];

  // 记忆化搜素
  public static int jumpFloor2(int target) {
    if (target <= 1) {
      return 1;
    }
    if (data[target] > 0) {
      return data[target];
    }
    return data[target] = (jumpFloor2(target - 1) +jumpFloor2( target - 2));

  }

  public static void main(String[] args) {
    System.out.println(jumpFloor2(3));
    System.out.println(jumpFloor1(3));
  }
}
