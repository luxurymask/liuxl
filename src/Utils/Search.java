package Utils;

public class Search {
	public static int binarySearch(int[] A, int k, int left, int right){
		if(left > right) return -1;
		int m = left + (right - left) / 2; //防止栈溢出。
		if(A[m] == k) return m;
		if(A[m] < k) return binarySearch(A, k, m + 1, right);
		if(A[m] > k) return binarySearch(A, k, left, m - 1);
		return -2;
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
	
	public static void main(String[] args){
		int[] array = {1, 2, 3, 4, 5, 6};
		System.out.println(binarySearch(array, 0, 0, 5));
		int[] array2 = {1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6};
		System.out.println(findTheLastIndex(array2, 1, 0, 10));
	}
}
