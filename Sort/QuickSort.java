package Sort;
import java.util.Arrays;

/**
 * QuickSort 快排
 */
public class QuickSort {
    public static void quickSort(int[]data,int left,int right){
        if(data==null || data.length==0)return;
        if(left<0||right<0)return;
        if (left < right) {		//元素序列长度大于1时
            int pivotpos = Partition (data,left, right);    //划分
            quickSort (data, left, pivotpos-1);
            quickSort (data, pivotpos+1, right);
        }
    
    }

    private static int Partition(int[]data,int low,int high){
        int pivotpos = low;
        int pivot = data[low];

        for(int i=low+1;i<=high;i++){

            if(data[i]<pivot){
                pivotpos++;
                if (i!=pivotpos) {
                    swap(data, i, pivotpos);
                }
            }

        }
        swap(data, low, pivotpos);
        return pivotpos;
    }

    public static void swap(int[]data,int i,int j){
        int temp = data[i];
        data[i] = data[j];
        data[j] =temp;
    }

    public static void main(String[] args) {
        int [] data = {12,48,46,4,2,6};
        QuickSort.quickSort(data, 0, data.length-1);
        System.out.println(Arrays.toString(data));
     }
}