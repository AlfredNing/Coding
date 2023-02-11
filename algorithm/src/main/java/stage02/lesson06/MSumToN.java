package stage02.lesson06;

/**
 * @ClassName MSumToN
 * @Description 连续数和
 * @Author Alfred.Ning
 * @Date 8/27/2022 9:21 PM
 * @Version 1.0
 **/
public class MSumToN {
    // 暴力方法
    public static boolean isMSum1(int num) {
        for (int i = 1; i <= num; i++) {
            int sum = i;
            for (int j = i + 1; j <= num; j++){
                if (sum + j > num) {
                    break;
                }
                if (sum + j == num) {
                    return true;
                }
                sum += j;
            }
        }
        return false;
    }
    public static boolean isMSum2(int num) {
        if (num < 3) {
            return false;
        }
        // num 是不是2的某次方
        //num & (num - 1) == 0 是2的某次方
        //num & (num - 1) != 0 不是2的某次方
        return (num & (num - 1)) != 0;
    }

    public static void main(String[] args) {
        for (int num = 1; num < 200; num++) {
            System.out.println(num + " : " + isMSum1(num));
        }

        for (int num = 1; num < 200; num++) {
            System.out.println(num + " : " + isMSum2(num));
        }
    }
}
