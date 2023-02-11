package stage01.lesson03;

/**
 * @ClassName MergeSort
 * @Description 归并排序 N * logn
 * @Author Alfred.Ning
 * @Date 2022/4/4 19:16
 * @Version 1.0
 **/
public class MergeSort {

    // 归并排序
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // 给定一个arr,要求在[l...r]范围有序
    public static void process(int[] arr, int l, int r) {
        if (l == r) { // base case
            return;
        }
        // 取mid
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    // merge阶段
    public static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l; // 左组起始位置
        int p2 = mid + 1; // 右组起始位置

        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 可能左组剩余
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        // 可能右组剩余
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        // 返回给原数组
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    // 非递归实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int len = arr.length;
        int mergeSize = 1; // 规定左组长度
        while (mergeSize < len) {
            int l = 0; // 左组左边界
            while (l < len) {
                int mid = l + mergeSize - 1;// 左组右边界
                if (mid >= len) {  // 最后的左组
                    break;
                }
                int r = Math.min(mid + mergeSize, len -1);
                merge(arr, l, mid, r);
                l = r + 1;
            }
            if (mergeSize > len / 2) { // 防止溢出，接近整数最大值
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int testTime = 50000;
        int maxSize = 6;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }


}