package stage01.lesson01;

/**
 * @ClassName EvenTimesOddTimes
 * @Description 数组中，有一种数出现了其数次，其他数出现了偶数次，找出这个数
 * @Author Alfred.Ning
 * @Date 2022/4/3 10:35
 * @Version 1.0
 **/
public class EvenTimesOddTimes {
    public static void printOddTimesNum(int[] arr){
        int eor = 0;
        for (int i : arr) {
            eor ^= i;
        }
        System.out.println(eor);
    }
    public static void main(String[] args) {
        int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
        printOddTimesNum(arr1);

        int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
        printOddTimesNum(arr2);

    }
}
