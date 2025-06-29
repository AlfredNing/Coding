package tutorial;

import java.util.Arrays;


public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 8, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println((0 - 1) / 2);
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(0, arr.length - 1, arr);
    }

    private static void quickSort(int l, int r, int[] arr) {
        if (l >= r) {
            return;
        }
        // 返回基准值的位置
        int mid = parition(l, r, arr);
        quickSort(l, mid - 1, arr);
        quickSort(mid + 1, r, arr);
    }

    private static int parition(int l, int r, int[] arr) {
        // 选定基准值
        int pivot = arr[l + (int) (Math.random() * (r - l + 1))];

        int a = l;
        // ax 标识和基准值相等的任意一个索引
        int ax = 0;
        for (int i = l; i <= r; i++) {
            if (arr[i] <= pivot) {
                swap(arr, a, i);
                if (arr[a] == pivot) {
                    ax = a;
                }
                a++;
            }
        }
        swap(arr, ax, a - 1);
        return a - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
