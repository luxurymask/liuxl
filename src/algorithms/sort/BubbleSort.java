package algorithms.sort;

/**
 * 冒泡排序
 * 时间复杂度：
 * 最好：O(n), 平均：O(n2), 最差：O(n2)
 * 空间复杂度：
 * O(1), IN-SPACE.
 * 稳定排序。
 * @author liuxl
 *
 */
public class BubbleSort {
	public static void bubbleSort(int[] array){
		int length = array.length;
		bubbleSort(array, 0, length - 1);
	}
	
	public static void bubbleSort(int[] array, int start, int end){
		boolean flag = true;
		for(int i = start;i <= end && flag == true;i++){
			flag = false;
			for(int j = end;j > i;j--){
				if(array[j] < array[j - 1]){
					exchange(array, j, j - 1);
					flag = true;
				}
			}
		}
	}
	
	public static void exchange(int[] array, int i, int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args){
		int[] array = {1, 3, 7, 8, 2, 4, 6, 5, 9, 0};
		bubbleSort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}
	}
}
