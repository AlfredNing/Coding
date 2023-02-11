package stage01.lesson01;

/**
 * @ClassName BSAwesome
 * @Description 无序数组，任意两个相邻的数都不相等，局部最小值问题，返回最小值
 * 0 < 1
 * n -1 > n
 * i - 1 > i < i+1
 * @Author Alfred.Ning
 * @Date 2022/4/3 10:18
 * @Version 1.0
 **/
public class BSAwesome {
    public static int getMinIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 2 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int l = 1;
        int r = arr.length - 2;
        int mid = 0;
        while (l < r) {
            mid = l + ((r - l) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                r = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                l = mid + 1;
            }else {
                return mid;
            }
        }
        return l;
    }
}
