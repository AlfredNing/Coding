package tutorial;

import java.util.Arrays;

public class RadioSort {

    public static void main(String[] args) {
        RadioSort radioSort = new RadioSort();
        radioSort.base = 10;
        int[] arr = {5, 3, 4, 8, 2, 11, -1, -2};
        radioSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 表示进制
    public int base;

    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        // 用来处理负数
        int min = Integer.MAX_VALUE;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            min = Math.min(min, arr[i]);
        }

        // 用来最高的数组的位数
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            // 处理负数
            arr[i] = arr[i] - min;
            max = Math.max(max, arr[i]);
        }

        radioSort(arr, len, maxBits(max));

        for (int i = 0; i < len; i++) {
            arr[i] = arr[i] + min;
        }
    }

    private void radioSort(int[] arr, int len, int bits) {
        // 初始化桶数组
        int[] count = new int[base];
        int[] helper = new int[len];

        for (int offset = 1; bits > 0; offset = offset * base, bits--) {
            // 桶数组初始化
            Arrays.fill(count, 0);

            // 桶数组进行统计
            for (int i = 0; i < len; i++) {
                // 789: 789 / 1 % 10 = 9
                // 789: 789 / 10 % 10 = 8
                // 789: 789 / 100 % 10 = 7
                count[(arr[i] / offset) % base]++;
            }
            // 前缀和处理
            for (int i = 1; i < base; i++) {
                count[i] += count[i - 1];
            }

            for (int i = len - 1; i >= 0; i--) {
                // count[(arr[i] / offset) % base] count[i]=number 表示<= i位置的元素出现的次数为number
                helper[--count[(arr[i] / offset) % base]] = arr[i];
            }
            System.arraycopy(helper, 0, arr, 0, len);
        }
    }

    private int maxBits(int number) {
        int ans = 0;
        while (number > 0) {
            ans++;
            number /= base;
        }
        return ans;
    }
}
