package stage01.lesson02;


import java.util.Arrays;

/**
 * @ClassName GetMaxValueInArr
 * @Description 获取一个arr在[L, R]范围的最大值，递归实现   时间复杂度为O(n)
 * @Author Alfred.Ning
 * @Date 2022/4/4 7:34
 * @Version 1.0
 **/
public class GetMaxValueInArr {
    public static int getMax(int[] arr, int l, int r) {
        return process(arr, l, r);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int mid = l + ((r - l) >> 1);
        int leftMax = process(arr, l, mid);
        int rightMax = process(arr, mid + 1, r);
        return Math.max(leftMax, rightMax);
    }

    public static int testGetMaxValue(int[] arr, int l, int r) {
        int max = arr[l];
        for (int i = l; i <= r; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;

    }

    public static void main(String[] args) {
        int testTime = 100_000;
        int maxLength = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomValue(maxLength, maxValue);
            int l = arr.length / 4;
            int r = arr.length / 2;
            int res1 = getMax(arr, l, r);
            int res2 = testGetMaxValue(arr, l, r);
            succeed = (res1 == res2);
            if (!succeed) {
                System.out.println(Arrays.toString(arr));
                System.out.println(res1);
                System.out.println(res2);
                break;
            }
        }
        System.out.println("succeed: " + succeed);

    }

    private static int[] generateRandomValue(int maxLength, int maxValue) {
        int[] arr = new int[(int) (1 + (maxLength + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }


}
