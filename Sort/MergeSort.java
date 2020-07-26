package Sort;

import java.util.Arrays;

/**
 * MergeSort 
 * 归并排序
 */
public class MergeSort {
    private static void merge(int[] data1, int[] data2, int left, int mid, int right) {
        for (int k = left; k <= right; k++) {
            data2[k] = data1[k];
        }

        int s1 = left, s2 = mid + 1, t = left;
        // s1, s2是data2表的检测指针, t是data1表的存放指针
        while (s1 <= mid && s2 <= right) {
            if (data2[s1] <= data2[s2]) {
                data1[t++] = data2[s1++];
            } else {
                data1[t++] = data2[s2++];
            }
        }
        while (s1 <= mid)
            data1[t++] = data2[s1++];
        while (s2 <= right)
            data1[t++] = data2[s2++];
    }

    public static void mergeSort(int[] data1, int[] data2, int left, int right) {
        if (left >= right)
            return;
        int mid = (left + right) / 2;
        mergeSort(data1, data2, left, mid); // 先递归划分
        mergeSort(data1, data2, mid + 1, right);
        merge(data1, data2, left, mid, right); // 再两路合并

    }

    public static void main(String[] args) {
        int[] data = { 12, 48, 46, 4, 2, 6 };
        int[] data2 = { 0, 0, 0, 0, 0, 0 };
        MergeSort.mergeSort(data, data2, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
    }
}