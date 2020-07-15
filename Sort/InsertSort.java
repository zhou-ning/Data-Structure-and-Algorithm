import java.util.Arrays;

/**
 * InsertSort 插入排序
 */
public class InsertSort {

    public static void insertSort(int[] data,int left,int right){
       if(data==null || data.length==0)return;
       if(left<0||right<0)return;
       if(data.length<left||data.length<right)return ;

       int temp=0,i=0,j=0;
       for (i= left+1; i<=right;++i) {
           //默认从 i-1到left是有序的
           if (data[i]<data[i-1]) {
               //比前面一个元素小
               temp=data[i];
               //记录下位置
               j=i-1;
               do {
                //进行移动，找到适合插入的地方
                data[j+1]=data[j];
                --j;
                } while (j>=left && temp<data[j]);//从后向前比较
                data[j+1]=temp;
           }

          
       }
    }

    public static void main(String[] args) {
        int [] data = {12,48,46,4,2,6};
        InsertSort.insertSort(data, 0, data.length-1);
        System.out.println(Arrays.toString(data));
    }
}


