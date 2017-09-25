package algorithms.math;

public class MinCommonMultiple {
	public static int minCommonMultiple(int[] array){
		if(array.length == 0) return -1;
		int minCommonMultiple = array[0];
		for(int i = 1;i < array.length;i++){
			minCommonMultiple = minCommonMultiple(minCommonMultiple, array[i]);
		}
		return minCommonMultiple;
	}
	
	public static int minCommonMultiple(int a, int b){
		int maxCommonDivisor = MaxCommonDivisor.euclid(a, b);
		return a / maxCommonDivisor * b / maxCommonDivisor * maxCommonDivisor;
	}
	
	public static void main(String[] args){
		System.out.println(minCommonMultiple(33, 24));
		int[] array = new int[]{100, 99, 98, 97, 96, 95, 94, 93, 92, 91};
		System.out.println(minCommonMultiple(array));
	}
}
