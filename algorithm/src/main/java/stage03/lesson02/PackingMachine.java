package stage03.lesson02;

/**
 * @author Alfred.Ning
 */
public class PackingMachine {
  public static int minOps(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int size = arr.length;
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
    }
    if (sum % size != 0) {
      return -1;
    }
    int avg = sum / size;
    int leftSum = 0;
    int ans = 0;
    for (int i = 0; i < arr.length; i++) {
      int leftRest = leftSum - i * avg;
      int rightRest = (sum - leftSum - arr[i]) - (size - i - 1) * avg;
      if (leftRest < 0 && rightRest < 0) {
        ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
      } else {
        ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
      }
      leftSum += arr[i];
    }
    return ans;
  }
}
