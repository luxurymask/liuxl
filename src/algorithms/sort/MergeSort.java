package algorithms.sort;

/**
 * 归并排序
 * 时间复杂度：
 * 最好：O(nlgn), 平均：O(nlgn), 最差：O(nlgn)
 * 空间复杂度：
 * O(n), Not IN-SPACE.
 * 稳定排序。
 */
public class MergeSort {
	public static void mergeSort(int[] array){
		int length = array.length;
		mergeSort(array, 0, length - 1);
	}
	
	/**
	 * 递归归并，复杂度：O(nlgn)
	 * @param array
	 * @param start
	 * @param end
	 */
	public static void mergeSort(int[] array, int start, int end){
		if(start < end){
			int mid = start + (end - start) / 2;
			mergeSort(array, start, mid);
			mergeSort(array, mid + 1, end);
			merge(array, start, mid, end);
		}
	}
	
	/**
	 * 合并，复杂度：O(n)
	 * @param array
	 * @param start
	 * @param mid
	 * @param end
	 */
	private static void merge(int[] array, int start, int mid, int end){
		int[] temp = new int[end - start + 1];
		int i = start, j = mid + 1, k = 0;
		while(i <= mid && j <= end){
			if(array[i] <= array[j]){
				temp[k++] = array[i++];
			}else{
				temp[k++] = array[j++];
			}
		}
		
		while(i <= mid){
			temp[k++] = array[i++];
		}
		
		while(j <= end){
			temp[k++] = array[j++];
		}
		
		for(int r = 0;r < temp.length;r++){
			array[start + r] = temp[r];
		}
	}
	
	public static void main(String[] args){
		int[] array = {1, 3, 7, 8, 2, 4, 6, 5, 9, 0};
		mergeSort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}
	}
}
