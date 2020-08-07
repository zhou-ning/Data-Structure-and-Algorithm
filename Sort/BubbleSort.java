package Sort;

import java.util.Arrays;

/**
 * BubbleSort 冒泡排序
 */
public class BubbleSort {
    public static void bubbleSort(int[] data, int left, int right) {
        if (data == null || data.length == 0)
            return;
        if (left < 0 || right < 0)
            return;
        if (data.length < left || data.length > right)
            return;

        int pass = left + 1;
        boolean exchange = true;
        while (pass <= right && exchange) {
            exchange = false;
            for (int j = right; j >= pass; j--) {
                if (data[j - 1] > data[j]) {
                    swap(data, j - 1, j);
                    exchange = true;
                }
            }
            pass++;
        }

    }

    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
        int[] data = { 12, 48, 46, 4, 2, 6 };
        BubbleSort.bubbleSort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
    }
}