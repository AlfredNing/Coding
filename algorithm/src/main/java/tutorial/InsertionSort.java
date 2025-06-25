package tutorial;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {5, 3, 4, 8, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            // j表示已经排好序元素索引
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
