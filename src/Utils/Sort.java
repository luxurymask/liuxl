package Utils;

public class Sort {
    /**
     * 插入排序
     * @param A 待排序数组。
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
     * @param A 待排序数组。
     * @param low 排序起始位置。
     * @param high 排序终止位置。
     * @return
     */
    public static void mergeSort(int[] A, int low, int high){
        int mid = (low + high)/2;
        
        if(low < high){//don't forget the bound.
            mergeSort(A, low, mid);
            mergeSort(A, mid + 1, high);
            merge(A, low, mid, high);
        }
    }
    
    /**
     * 归并数组中的两个有序段
     * @param A 待排序数组。
     * @param low 第一有序段起始位置。
     * @param mid 第一有序段结束位置。
     * @param high 第二有序段终止位置。
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
    
    /**
     * 快速排序。
     * @param A
     * @param p
     * @param q
     */
    public static void quickSort(int[] A, int p, int q){
        if(p < q){//don't forget the bound.
            int i = partitionTwoPointers(A, p, q);
            quickSort(A, p, i - 1);
            quickSort(A, i + 1, q);
        }
    }
    
    public static int partitionTwoPointers(int[] A, int p, int q){
    	int x = A[p];
    	int i = p;
    	int j = q;
    	while(i < j){
    		while(i < j && A[j] >= x) j--;
    		A[i] = A[j];
    		while(i < j && A[i] <= x) i++;
    		A[j] = A[i];
    	}
    	A[i] = x;
    	return i;
    }
    
    public static int partition(int[] A, int p, int q){
        int x = A[p];
        int i = p;
        for(int j = p + 1;j <= q;j++){
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
    
    /**
     * 求左孩子index。
     * @param i 当前节点index。
     * @return 左孩子index。
     */
    private static int leftChild(int i){
    	return 2 * i + 1;
    }
    
    /**
     * 下滤。
     * @param A 堆数组。
     * @param hole 空位。
     * @param n 数组长度（不是最大index）。
     */
    private static void percDown(int[] A, int hole, int n){
    	int child;
    	int tmp = A[hole];
    	for(;leftChild(hole) < n;hole = child){
    		child = leftChild(hole);
    		//大顶堆。
    		if(child != n - 1 && A[child + 1] > A[child]){
    			child++;
    		}
    		if(tmp < A[child]){
    			A[hole] = A[child];
    		}else{
    			break;
    		}
    	}
    	A[hole] = tmp;
    }
    
    /**
     * 堆排序，建堆，逐个删除堆顶最大值，移入最后一个位置。
     * @param A 
     */
    public static void heapSort(int[] A){
    	for(int i = A.length / 2 - 1;i >= 0;i--){
    		percDown(A, i, A.length);
    	}
    	for(int i = A.length - 1;i > 0;i--){
    		int tmp = A[i];
    		A[i] = A[0];
    		A[0] = tmp;
    		percDown(A, 0, i);
    	}
    }
    
    /**
     * 计数排序。
     * @param A 待排序数组。
     * @param k 数组中最大数。
     * @return 排序好的数组。
     */
    public static int[] countingSort(int[] A, int k){
    	int length = A.length;
    	int[] aux = new int[k];
    	int[] result = new int[length];
    	
    	for(int i = 0;i < length;i++){
    		aux[A[i]]++;
    	}
    	
    	for(int i = 2;i < length;i++){
    		aux[i] = aux[i] + aux[i - 1];
    	}
    	
    	for(int i = length - 1;i >= 0;i--){
    		result[aux[A[i]]] = A[i];
    		aux[A[i]]--;
    	}
    	
    	return result;
    }
    
    public static void main(String[] args){
        int[] A = new int[]{1, 3, 7, 8, 2, 4, 6, 5, 9, 0};
        //insertionSort(A);
        //int[] B = mergeSort(A, 0, A.length - 1);
        heapSort(A);
        for(int i = 0;i < A.length;i++){
            System.out.print(A[i]);
        }
    }
}
