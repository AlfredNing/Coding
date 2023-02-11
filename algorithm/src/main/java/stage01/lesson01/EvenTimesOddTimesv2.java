package stage01.lesson01;

/**
 * @ClassName EvenTimesOddTimes
 * @Description 数组中，有两种数出现了奇数次，其他数出现了偶数次，找出两种数,前提这两种数不等
 * @Author Alfred.Ning
 * @Date 2022/4/3 10:35
 * @Version 1.0
 **/
public class EvenTimesOddTimesv2 {
    public static void printOddTimesNumV2(int[] arr) {
        int eor = 0;
        for (int i : arr) {
            eor ^= i;
        }
        // eor = a ^ b
        // eor != 0
        // a,b必然有一个位置为1
        int rightOne = eor & (~eor) + 1;

        int onlyOne = 0;
        for (int i : arr) {
            if( (i & rightOne) != 0){ // 必然有1
                onlyOne ^= i;
            }
        }
        System.out.println(onlyOne+" ; " + (eor^onlyOne));
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1,4,4,4,5,4};
        printOddTimesNumV2(arr1);


    }
}
