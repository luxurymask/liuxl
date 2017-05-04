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
	
	public static void main(String[] args){
		int[] array = {1, 2, 3, 4, 5, 6};
		System.out.println(binarySearch(array, 0, 0, 5));
	}
}
