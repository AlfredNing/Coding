package tutorial;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 4, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) { // 控制是的轮数
            boolean isChange = false; // 是否发生变化
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    isChange = true;
                    swap(arr, j, j + 1);
                }
            }
            if (!isChange) {
                // 没有发生改变 说明结束了
                return;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
