package algorithms.sort;

/**
 * 快速排序
 * 标准快速排序时间复杂度：
 * 最好：O(nlgn), 平均：O(nlgn), 最差：O(n2)
 * 随机快速排序时间复杂度：
 * 最好：O(nlgn), 平均：O(nlgn), 最差：O(nlgn)
 * 标准快速排序空间复杂度：
 * O(lgn) ~ O(n), IN-SPACE.（空间消耗主要在于递归调用的局部变量保持）
 * 不稳定的排序。
 */
public class QuickSort {
	public static void quickSort(int[] array){
		int length = array.length;
		quickSort(array, 0, length - 1);
	}
	
	public static void quickSort(int[] array, int start, int end){
		if(start < end){
			int mid = partition(array, start, end);
			quickSort(array, start, mid - 1);
			quickSort(array, mid + 1, end);
		}
	}
	
	public static int partition(int[] array, int start, int end){
		int pivot = array[start];
		int i = start;
		for(int j = start + 1;j <= end;j++){
			if(array[j] < pivot){
				i++;
				exchange(array, i, j);
			}
		}
		exchange(array, start, i);
		return i;
	}
	
	public static void exchange(int[] array, int i, int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args){
		int[] array = {1, 3, 7, 8, 2, 4, 6, 5, 9, 0};
		quickSort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}
	}
}
