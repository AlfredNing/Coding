package stage01.lesson03;

import java.util.Arrays;

/**
 * @ClassName PartitionAndQuickSort
 * @Description 分区排序，快排
 * @Author Alfred.Ning
 * @Date 2022/4/5 10:25
 * @Version 1.0
 **/
public class PartitionAndQuickSort {
    // arr在[L...R]上面，以arr[R]为界，<= arr[R]在左，>arr[R]在右,返回arr[R]的索引下标
    public static int partition(int[] arr, int l, int r) {
        if (l > r) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        int lessEqual = l - 1;// 初始位置
        int idx = l;
        while (idx < r) {
            if (arr[idx] <= arr[r]) {
                swap(arr, idx, ++lessEqual);
            }
            idx++;
        }
        swap(arr, ++lessEqual, r);
        return lessEqual;
    }

    private static void swap(int[] arr, int idx, int i) {
        int tmp = arr[idx];
        arr[idx] = arr[i];
        arr[i] = tmp;
    }

    // 在arr[L...R]范围荷兰国旗问题，以arr[r]做为分隔值，返回等于区域的左右两边界
    public static int[] netherlandsFlag(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int less = l - 1; // 定于小于区
        int idx = l;
        int more = r; // 定义大于区
        while (idx < more) {
            if (arr[idx] == arr[r]) {
                idx++;
            } else if (arr[idx] < arr[r]) {
                swap(arr, idx++, ++less);
            } else {
                swap(arr, idx, --more);
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }

    //快排1.0 时间复杂度为 n^2
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    private static void process1(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // 保证 arr 在[<arr[r]...arr[r]...> arrr[r]]
        int mid = partition(arr, l, r);
        process1(arr, l, mid - 1);
        process1(arr, mid + 1, r);
    }

    //快排2.0 时间复杂度为 n^2
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    private static void process2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] mid = netherlandsFlag(arr, l, r);
        process2(arr, l, mid[0] - 1);
        process2(arr, mid[1] + 1, r);
    }

    //快排3.0 时间复杂度为 n * logn 随机选取数 划分值越靠近中间，性能越好
    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    private static void process3(int[] arr, int l, int r) {
        if(l >= r){
            return;
        }
        swap(arr,l + (int) (Math.random() * ((r - l + 1))),r);
        int[] mid = netherlandsFlag(arr, l, r);
        process3(arr, l, mid[0] - 1);
        process3(arr, mid[1] + 1, r);
    }
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
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

    // for test
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
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }
}
