package tutorial;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {5, 3, 4, 2, 8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 构建堆
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            heapInsert(arr, i);
        }

        // 堆化的过程
        int size = arr.length;
        while (size > 1) {
            swap(arr, 0, --size);
            heapify(arr, 0, size);
        }
    }

    private static void heapify(int[] arr, int i, int size) {
        // 左孩子
        int l = i * 2 + 1;

        while (l < size) {
            // 比较左右孩子节点
            int best = l + 1 < size && arr[l + 1] > arr[l] ? l + 1 : l;
            // 比较父亲与左右孩子元素
            best = arr[best] > arr[i] ? best : i;
            if (best == i) {
                break;
            }
            swap(arr, best, i);

            // 向下进行堆化过程
            i = best;
            l = i * 2 + 1;
        }
    }

    private static void heapInsert(int[] arr, int i) {
        int p = (i - 1) / 2;
        while (arr[i] > arr[p]) {
            swap(arr, i, p);
            i = p;
            p = (i - 1) / 2;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
