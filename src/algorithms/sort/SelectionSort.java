package algorithms.sort;

/**
 * 选择排序
 * 时间复杂度：
 * 最好：O(n2), 平均：O(n2), 最差：O(n2)
 * 空间复杂度：
 * O(1), IN-SPACE.
 * 不稳定的排序。
 */
public class SelectionSort {
	public static void selectionSort(int[] array){
		int length = array.length;
		selectionSort(array, 0, length - 1);
	}
	
	public static void selectionSort(int[] array, int start, int end){
		for(int i = start;i <= end;i++){
			int min = array[i];
			int index = i + 1;
			for(int j = index;j <= end;j++){
				if(array[j] < min){
					min = array[j];
					index = j;
				}
			}
			if(min != array[i]) exchange(array, i, index);
		}
	}
	
	public static void exchange(int[] array, int i, int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args){
		int[] array = {1, 3, 7, 8, 2, 4, 6, 5, 9, 0};
		selectionSort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}
	}
}
