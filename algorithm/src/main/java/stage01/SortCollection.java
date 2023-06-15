package stage01;

import java.util.Arrays;

/**
 * @author Alfred.Ning
 * @since 2023年06月08日 09:11:00
 */
public class SortCollection {

  public static void main(String[] args) {
    int[] input = {2, 1, -1, 4, 5, 0, 10};

    bubbleSort(input);
    System.out.println("=======");
    selectionSort(input);
    System.out.println("=======");
    insertionSort(input);


  }

  // 插入排序
  public static void insertionSort(int[] arr) {
    System.out.println("insertionSort");
    // 稳定算法
    int[] newArr = Arrays.copyOf(arr, arr.length);
    for (int i = 1; i < newArr.length; i++) {
      for (int j = i - 1; j >= 0 && newArr[j] > newArr[j + 1]; j--) {
        swap(newArr, j, j + 1);
      }
    }
    System.out.println(Arrays.toString(newArr));
  }

  public static void selectionSort(int[] arr) {
    // 不稳定
    System.out.println("selection sort");
    // 0-n-1,选出最小的，放到0位置
    // 1-n-1,选出最小的，放到1位置
    // ....
    int[] newArr = Arrays.copyOf(arr, arr.length);
    for (int i = 0; i < newArr.length - 1; i++) { // 外层循环次数
      int minIndex = i;
      for (int j = i + 1; j < newArr.length; j++) {
        minIndex = newArr[j] < newArr[minIndex] ? j : minIndex;
      }
      if (i != minIndex) {
        swap(newArr, i, minIndex);

      }
    }
    System.out.println(Arrays.toString(newArr));
  }

  public static void bubbleSort(int[] arr) {
    int[] newArr = Arrays.copyOf(arr, arr.length);
    System.out.println("bubble sort");
    // 相等元素没有交换，稳定排序
    for (int j = newArr.length - 1; j > 0; j--) {
      // 每一趟选出最大的
      for (int i = 0; i < j; i++) {
        if (newArr[i] > newArr[i + 1]) {
          swap(newArr, i, i + 1);
        }
      }
    }
    System.out.println(Arrays.toString(newArr));
  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

}
