package Sort;

import java.util.ArrayList;

/***
 * 桶排序,还存在一些问题
 */
public class BucketSort {
    public static void bucketSort(int[] data, int bucketSize) {
        if (data.length == 0) {
            return;
        }

        int minValue = data[0], maxValue = data[0];
        for (int i = 1; i < data.length; i++) {
            // 输入数据的最小值
            minValue = Math.min(minValue, data[i]);
            // 输入数据的最大值
            maxValue = Math.max(maxValue, data[i]);
        }

        // 桶的初始化
        int DEFAULT_BUCKET_SIZE = 5;// 设置桶的默认数量的最大值
        bucketSize = bucketSize == 0 ? DEFAULT_BUCKET_SIZE : bucketSize;
        double x = (maxValue - minValue);
        int bucketCount = (int) (Math.floor(x / bucketSize)) + 1;
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // 利用映射函数将数据分配到各个桶中
        for (int i = 0; i < data.length; i++) {
            double y = (data[i] - minValue);
            int idx = (int) Math.floor(y / bucketSize);
            buckets.get(idx).add(data[i]);
        }

        int sortedIndex = 0;
        for (int i = 0; i < bucketCount; i++) {
            // 进行插入排序 insertionSort(buckets[i]);
            for (int j = 0; j < buckets.get(i).size(); j++) {
                data[sortedIndex++] = buckets.get(i).get(j);
            }
        }
    }
}