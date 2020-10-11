package Sort;

import java.util.Arrays;

/***
 * 计数排序
 */
public class CountingSort {
    public static void countingSort(int[] data) {
        if (data == null || data.length == 0) {
            return;
        }

        int sortedIndex = 0;
        int arrLen = data.length;

        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < arrLen; i++) {
            maxValue = Math.max(maxValue, data[i]);
        }
        int bucketLen = maxValue + 1;
        int[] bucket = new int[bucketLen];
        // 记录桶的数量
        for (int i = 0; i < arrLen; i++) {
            bucket[data[i]]++;
        }

        for (int i = 0; i < bucketLen; i++) {
            while (bucket[i] > 0) {
                data[sortedIndex++] = i;
                bucket[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = { 12, 48, 46, 4, 2, 6 };
        CountingSort.countingSort(data);
        System.out.println(Arrays.toString(data));
    }
}