package Utils;

@Deprecated
public class Search {
	public static int binarySearch(int[] A, int k, int left, int right){
		if(left > right) return -1;
		int m = left + (right - left) / 2; //防止栈溢出。
		if(A[m] == k) return m;
		if(A[m] < k) return binarySearch(A, k, m + 1, right);
		if(A[m] > k) return binarySearch(A, k, left, m - 1);
		return -2;
	}
	
	public static int binarySearchNonRecursively(int[] A, int k, int left, int right){
		while(left <= right){
			int m = left + (right - left) / 2;
			if(A[m] == k) return m;
			if(A[m] < k) left = m + 1;
			if(A[m] > k) right = m - 1;
		}
		return -1;
	}
	
	/**
	 * 有重复，返回最大index。
	 * @param A
	 * @param k
	 * @param left
	 * @param right
	 * @return
	 */
	public static int findTheLastIndex(int[] A, int k, int left, int right){
		if(left > right) return -1;
		int m = left + (right - left) / 2;
		if(A[m] == k && (m == A.length - 1 || A[m + 1] != A[m])) return m;
		if(A[m] > k) return findTheLastIndex(A, k, left, m - 1);
		return findTheLastIndex(A, k, m + 1, right);
	}
	
	public static int findTheLastIndexNonRecursively(int[] A, int k, int left, int right){
		while(left <= right){
			int m = left + (right - left) / 2;
			if(A[m] == k && (m == A.length - 1 || A[m + 1] != A[m])) return m;
			if(A[m] > k){
				right = m - 1;
			}else{
				left = m + 1;
			}
		}
		return -1;
	}
	
	public static void main(String[] args){
		int[] array = {1, 2, 3, 4, 5, 6};
		System.out.println(binarySearch(array, 0, 0, 5));
		System.out.println(binarySearch(array, 5, 0, 5));
		int[] array2 = {1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6};
		System.out.println(findTheLastIndex(array2, 1, 0, 10));
		System.out.println(findTheLastIndexNonRecursively(array2, 3, 0, 10));
	}
}
