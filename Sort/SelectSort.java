package Sort;
import java.util.Arrays;

/**
 * SelectSort 选择排序
 */
public class SelectSort {
    public static void selectSort(int[]data,int left,int right){
        if(data==null || data.length==0)return;
        if(left<0||right<0)return;
        if(data.length<left||data.length<right)return ;

        for(int i=left;i<right;i++){
            int k=i;
            for(int j=i+1;j<=right;j++){
                if (data[j]<data[k]) {
                    k=j;
                }
            }
            if (k!=i) {
                swap(data, i, k);
            }
        }
        
    }

    public static void swap(int[]data,int i,int j){
        int temp = data[i];
        data[i] = data[j];
        data[j] =temp;
    }

    public static void main(String[] args) {
        int [] data = {12,48,46,4,2,6};
        SelectSort.selectSort(data, 0, data.length-1);
        System.out.println(Arrays.toString(data));
     }

}