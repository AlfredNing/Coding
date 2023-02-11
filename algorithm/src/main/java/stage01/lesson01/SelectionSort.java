package stage01.lesson01;

import java.util.Arrays;

/**
 * @ClassName SelectionSort
 * @Description 选择排序
 * @Author Alfred.Ning
 * @Date 2022/4/3 7:52 时间复杂度 n^2
 * @Version 1.0
 **/
public class SelectionSort {
    public static void selectionSort(int[] arr) {
        // 0-n-1,选出最小的，放到0位置
        // 1-n-1,选出最小的，放到1位置
        // ....
        for (int i = 0; i < arr.length - 1; i++) { // 外层循环次数
            int minIndex = i;
            for(int j = i + 1;j < arr.length; j++){
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, minIndex, i);
        }
    }
    public static void swap(int[] arr,int minIndex, int i){
        int temp = arr[minIndex];
        arr[minIndex] = arr[i];
        arr[i] = temp;
    }
    public static int[] generateRandomArray(int maxSize, int maxValue){
        int[] arr = new int[(int)((maxSize + 1)*Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i]  = (int)((maxValue + 1) *Math.random())  - (int) (maxValue * Math.random());
        }
        return  arr;
    }

    public static int[] copyArray(int[] arr){
        if(arr == null){
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
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
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize,maxSize);
            int[] arr2 = copyArray(arr1);
            selectionSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        selectionSort(arr);
        printArray(arr);
    }
}
