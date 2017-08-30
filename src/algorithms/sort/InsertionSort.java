package algorithms.sort;

/**
 * 插入排序
 * 时间复杂度：
 * 最好：O(n), 平均：O(n2), 最差：O(n2)
 * 空间复杂度：
 * O(1), IN-SPACE.
 * 稳定排序。
 */
public class InsertionSort {
	public static void insertionSort(int[] array){
		int length = array.length;
		insertionSort(array, 0, length - 1);
	}
	
	public static void insertionSort(int[] array, int start, int end) {
		for (int i = start + 1; i <= end; i++) {
			int key = array[i];
			int j = i - 1;
			while (j >= start && array[j] > key) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = key;
		}
	}

	public static void main(String[] args) {
		int[] array = { 1, 3, 7, 8, 2, 4, 6, 5, 9, 0 };
		insertionSort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}
	}
}
