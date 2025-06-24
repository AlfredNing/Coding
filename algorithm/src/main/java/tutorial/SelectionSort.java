package tutorial;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {5, 3, 4, 8, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 升序排序
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                // 说明产生了比i位置更小的元素 进行交互
                swap(arr, i, minIndex);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
