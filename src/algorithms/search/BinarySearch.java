package algorithms.search;

/**
 * 二分查找
 * 时间复杂度：
 * 最好：O(1), 平均：O(lgn), 最差：O(lgn)
 * --------------------------------------------------------------------------------------------------------
 * 当数组中有重复值，查找k的最大index时，只需要在找到时继续二分查找即可，查找时注意mid右边没有值或者mid已经是k最大index的情况。
 * --------------------------------------------------------------------------------------------------------
 */
public class BinarySearch {
	public static int binarySearch(int[] array, int k, int left, int right){
		if(left <= right){
			int mid = left + (right - left) / 2;
			if(array[mid] == k) return mid;
			if(array[mid] < k) return binarySearch(array, k, mid + 1, right);
			if(array[mid] > k) return binarySearch(array, k, left, mid - 1);
		}
		return -1;
	}
	
	public static int binarySearchNonRecursively(int[] array, int k, int left, int right){
		while(left <= right){
			int mid = left + (right - left) / 2;
			if(array[mid] == k) return mid;
			if(array[mid] < k) left = mid + 1;
			if(array[mid] > k) right = mid - 1;
		}
		return -1;
	}
	
	public static int findTheLastIndex(int[] array, int k, int left, int right){
		if(left <= right){
			int mid = left + (right - left) / 2;
			if(array[mid] == k && (mid == right || array[mid + 1] != array[mid])) return mid;
			if(array[mid] > k) return findTheLastIndex(array, k, left, mid - 1);
			return findTheLastIndex(array, k, mid + 1, right);
		}
		return -1;
	}
	
	public static int findTheLastIndexNonRecursively(int[] array, int k, int left, int right){
		while(left <= right){
			int mid = left + (right - left) / 2;
			if(array[mid] == k && (mid == right || array[mid + 1] != array[mid])) return mid;
			if(array[mid] > k){
				right = mid - 1;
				continue;
			}
			left = mid + 1;
		}
		return -1;
	}
	
	public static void main(String[] args){
		int[] array = {1, 3, 4, 5, 6, 7, 8, 9};
		System.out.println(binarySearch(array, 2, 0, 8));
		System.out.println(binarySearchNonRecursively(array, 2, 0, 8));

		int[] array2 = {1, 1, 3, 3, 4, 5, 6, 6, 6, 7, 8, 9, 9};
		System.out.println(findTheLastIndex(array2, 9, 0, 12));
		System.out.println(findTheLastIndexNonRecursively(array2, 4, 0, 12));
	}
}
