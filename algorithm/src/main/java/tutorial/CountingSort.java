package tutorial;

import java.util.Arrays;

public class CountingSort {

    public static void main(String[] args) {
        int[] arr = {5, 3, 4, 2, 8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int max = arr[0], min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[arr.length];

        // 计数排序
        for (int num : arr) {
            count[num - min]++;
        }

        // 前缀和 ，确定最终的位置
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        // 建立output数组 count[i] = c 表示的是i元素应该出现在c位置上
        for (int i = arr.length - 1; i >= 0; i--) {
            // arr[i] - min 确定在count的下标位置
            // [count[arr[i] - min] - 1 -1的目的是建立索引
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        // 拷贝的原数组中
        System.arraycopy(output, 0, arr, 0, arr.length);
    }
}
