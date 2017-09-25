package algorithms.math;

public class MaxCommonDivisor {
	public static int maxCommonDivisor(int[] array){
		if(array.length == 0) return -1;
		int maxCommonDivisor = array[0];
		for(int i = 1;i < array.length;i++){
			maxCommonDivisor = euclid(maxCommonDivisor, array[i]);
		}
		return maxCommonDivisor;
	}
	
	public static int euclid(int a, int b){
		int smaller = a > b ? b : a;
		int bigger = a > b ? a : b;
		while(smaller != 0){
			int remainder = bigger % smaller;
			bigger = smaller;
			smaller = remainder;
		}
		
		return bigger;
	}
	
	public static int substraction(int a, int b){
		if(a > b) return substraction(a - b, b);
		if(a < b) return substraction(a, b - a);
		return a;
	}
	
	public static int exaustion(int a, int b){
		for(int i = Math.min(a, b);i > 0;i--){
			if((a % i) == 0 && (b % i) == 0) return i;
		}
		return 1;
	}
	
	public static void main(String[] args){
		int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		System.out.println(maxCommonDivisor(array));
		System.out.println(euclid(12, 8));
		System.out.println(substraction(120, 8));
		System.out.println(exaustion(5, 8));
	}
}
