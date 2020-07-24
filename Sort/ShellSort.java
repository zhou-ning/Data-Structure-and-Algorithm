package Sort;
import java.util.Arrays;
/**
 * ShellSort 希尔排序
 */
public class ShellSort {
    public static void shellSort(int[]data,int left,int right){
        if(data==null || data.length==0)return;
        if(left<0||right<0)return;
        if(data.length<left||data.length<right)return ;

        int i=0,j=0,gap = right-left+1;
        int temp=0;
        do {
            gap = gap/3+1;
            for (i=left+gap; i<= right; i++) {
                if (data[i]<data[i-gap]) {
                    temp = data[i];
                    j=i-gap;
                    do {
                        data[j+gap] = data[j];
                        j=j-gap;
                    } while (j>=left && temp<data[j]);
                    data[j+gap] = temp;
                }
              
            }

        } while (gap>1);


     }
     
     public static void main(String[] args) {
        int [] data = {12,48,46,4,2,6};
        ShellSort.shellSort(data, 0, data.length-1);
        System.out.println(Arrays.toString(data));
     }
}