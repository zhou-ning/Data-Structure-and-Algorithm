package Sort;

import java.util.Arrays;

public class RadixSort {
    public static void radixSort(int[] data) {
        // 待排序列最大值
        int max = data[0];
        int exp;// 指数
        // 找最大值
        for (int i : data) {
            if (i > max) {
                max = i;
            }
        }

        // 从个位开始，对数组进行排序
        for (exp = 1; max / exp > 0; exp *= 10) {
            // 存储待排元素的临时数组
            int[] temp = new int[data.length];
            // 分桶个数
            int[] buckets = new int[10];

            // 将数据出现的次数存储在buckets中
            for (int value : data) {
                // (value / exp) % 10 :value的最底位(个位)
                buckets[(value / exp) % 10]++;
            }

            // 更改buckets[i]，统计buckets数组的前i位（包含i）共有多少个数
            for (int i = 1; i < 10; i++) {
                buckets[i] += buckets[i - 1];
            }

            // 将数据存储到临时数组temp中
            for (int i = data.length - 1; i >= 0; i--) {
                temp[buckets[(data[i] / exp) % 10] - 1] = data[i];
                buckets[(data[i] / exp) % 10]--;
            }

            // 将有序元素temp赋给data
            System.arraycopy(temp, 0, data, 0, data.length);
        }
    }

    public static void main(String[] args) {
        int[] data = { 63, 157, 189, 51, 101, 47, 141, 121, 157, 156, 194, 117, 98, 139, 67, 133, 181, 12, 28, 0, 109 };
        radixSort(data);
        System.out.println(Arrays.toString(data));
    }
}