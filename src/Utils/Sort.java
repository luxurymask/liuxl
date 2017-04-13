package Utils;

import java.util.logging.Logger;

public class Sort {
    /**
     * 插入排序
     * @param A
     */
    public static void insertionSort(int[] A){
        int aLength = A.length;
        for(int i = 1;i < aLength;i++){
            int target = A[i];
            int j = i - 1;
            //set target(A[j + 1] = target;)就一步，不要放在循环内部用if-else实现，这样更清晰，也有效避免了边界时跳出循环不能成功set。
            while(j >= 0 && target < A[j]){
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = target;
        }
    }
    
    /**
     * 归并排序
     * @param A
     * @param low
     * @param high
     * @return
     */
    public static int[] mergeSort(int[] A, int low, int high){
        int mid = (low + high)/2;
        
        if(low < high){//don't forget the bound.
            mergeSort(A, low, mid);
            mergeSort(A, mid + 1, high);
            merge(A, low, mid, high);
        }
        
        return A;
    }
    
    /**
     * 归并数组中的两个有序段
     * @param A
     * @param low
     * @param mid
     * @param high
     */
    public static void merge(int[] A, int low, int mid, int high){
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        
        while(i <= mid && j <= high){
            if(A[i] < A[j]){
                temp[k++] = A[i++];
            }else{
                temp[k++] = A[j++];
            }
        }
        
        while(i <= mid){
            temp[k++] = A[i++];
        }
        
        while(j <= high){
            temp[k++] = A[j++];
        }
        
        for(int r = 0;r < temp.length;r++){
            A[low + r] = temp[r];
        }
    }
    
    public static void quickSort(int[] A, int p, int q){
        if(p < q){//don't forget the bound.
            int i = partition(A, p, q);
            quickSort(A, p, i - 1);
            quickSort(A, i + 1, q);
        }
    }
    
    public static int partition(int[] A, int p, int q){
        int x = A[p];
        int i = p;
        for(int j = p + 1;j < q;j++){
            if(A[j] <= x){
                int temp = A[++i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        int temp = A[p];
        A[p] = A[i];
        A[i] = temp;
        return i;
    }
    
    public static void main(String[] args){
        int[] A = new int[]{1, 3, 7, 8, 2, 4, 6, 5, 9, 0};
        //insertionSort(A);
        int[] B = mergeSort(A, 0, A.length - 1);
        quickSort(A, 0, A.length - 1);
        for(int i = 0;i < A.length;i++){
            System.out.print(A[i]);
        }
    }
}
