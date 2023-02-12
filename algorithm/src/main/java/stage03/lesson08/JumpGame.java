package stage03.lesson08;

/**
 * 范围上的尝试
 *
 * @author Alfred.Ning
 * @since 2023年02月12日 15:41:00
 */
public class JumpGame {

  public static int jump(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int step = 0; // 当前第几步
    int cur = 0; // step内，右边界
    int next = 0; // step+1步内，最大右边界
    // 本次的最大边界位置 是由第四边界位置往右组成的，寻找base case
    for (int i = 0; i < arr.length; i++) {
      if (cur < i) {
        step++;
        cur = next;
      }
      next = Math.max(next, i + arr[i]);
    }
    return step;
  }
}
