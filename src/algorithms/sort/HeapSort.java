package algorithms.sort;

/**
 * 堆排序
 * 时间复杂度：
 * 最好：O(nlgn), 平均：O(nlgn), 最差：O(nlgn)
 * 空间复杂度：
 * O(1), IN-SPACE.
 * 不稳定的排序。
 *----------------------------------------------------------------------------
 * KEY TRICK：
 * 一棵完全二叉树可以用数组来表示。
 * 对于数组上任一位置i上的元素（i从0开始）：
 * 其左儿子位置为2 * i + 1，右儿子位置为2 * (i + 1)，父亲位置为(i - 1) / 2（向下取整）。
 * ---------------------------------------------------------------------------
 */
public class HeapSort {
	
	public static void heapSort(int[] array){
		//将数组整理成堆。要点是，只需要从倒数第二层具有子节点的最右元素到堆顶节点逐个下滤就可以将乱序数组整理成堆。
		//倒数第二层最右元素index为(array.length / 2 - 1)。
		for(int i = array.length / 2 - 1;i >= 0;i--){
			percolateDown(array, i, array.length);
		}
		
		//整理成大顶堆后，逐个将堆顶的元素与数组未排序部分的末尾元素交换并通过堆顶元素下滤再次将未排序部分构建成大顶堆。
		for(int j = array.length - 1;j > 0;j--){
			exchange(array, 0, j);
			percolateDown(array, 0, j);
		}
	}
	
	/**
	 * 下滤操作。
	 * @param array
	 * @param i 初始hole位置。
	 * @param n 数组未排序部分长度。
	 */
	public static void percolateDown(int[] array, int i, int n){
		int temp = array[i];
		int child;
		//下滤循环终止到倒数第二层，即循环条件为i处节点至少有左儿子。
		for(;2 * i + 1 < n;i = child){
			//左孩子
			child = 2 * i + 1;
			//大顶堆
			//判断是否有右孩子并判断右孩子是否比做孩子大。
			if(child != n - 1 && array[child + 1] > array[child]){
				child++;
			}
			
			//下滤
			if(array[child] > temp){
				array[i] = array[child];
			}else{
				break;
			}
		}
		//填补hole。i最终值为要下滤数的最终位置。
		array[i] = temp;
	}
	
	public static void exchange(int[] array, int i, int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args){
		int[] array = {1, 3, 7, 8, 2, 4, 6, 5, 9, 0};
		heapSort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}
	}
}
