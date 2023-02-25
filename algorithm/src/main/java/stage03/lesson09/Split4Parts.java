package stage03.lesson09;

import java.util.HashMap;

/**
 * @author Alfred.Ning
 * @since 2023年02月25日 13:15:00
 */
public class Split4Parts {

  public static boolean conSplit4(int[] arr) {
    if (arr == null || arr.length < 7) {
      return false;
    }
    // 预处理 key某一个累加和，value出现的位置
    HashMap<Integer, Integer> map = new HashMap<>();
    int sum = arr[0]; // 记录总共累加和
    for (int i = 0; i < arr.length; i++) {
      map.put(sum, i);
      sum += arr[i];
    }
    int lSum = arr[0]; // 第一刀左侧累加和
    // 第一刀最左侧的位置，第一刀一定出现在小于arr.length - 5的位置上
    for (int s1 = 1; s1 < arr.length - 5; s1++) {
      int checkSum = lSum * 2 + arr[s1];
      if (map.containsKey(checkSum)) {
        Integer s2 = map.get(checkSum);// 第二刀位置
        checkSum += lSum + arr[s2];
        if (map.containsKey(checkSum)) {
          Integer s3 = map.get(checkSum);// 第三刀位置
          if (checkSum + arr[s3] + lSum == sum) {
            return true;
          }
        }
      }
      lSum += arr[s1];
    }
    return false;
  }

}
