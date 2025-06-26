package tutorial;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] arr = {5, 3, 4, 8, 2};
        mergeSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 归并合并处理结果
        int[] tmp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, tmp);
    }

    private void mergeSort(int[] arr, int left, int right, int[] tmp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            // 数组 拆分
            mergeSort(arr, left, mid, tmp);
            mergeSort(arr, mid + 1, right, tmp);
            merge(arr, left, mid, right, tmp);
        }
    }

    private void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        // 合并的第一个数组索引
        int i = left;
        // 合并的第二个数组索引
        int j = mid + 1;

        int k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            // 第一个数组有剩余
            tmp[k++] = arr[i++];
        }
        while (j <= right) {
            tmp[k++] = arr[j++];
        }

        System.arraycopy(tmp, 0, arr, left, k);
    }
}
