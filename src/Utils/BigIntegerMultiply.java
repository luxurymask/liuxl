package Utils;

public class BigIntegerMultiply {
	public static String multiply(String num1, String num2){
		char[] array1 = num1.toCharArray();
		char[] array2 = num2.toCharArray();
		int[] result = new int[array1.length + array2.length];
		
		for(int i = array1.length - 1;i >= 0;i--){
			for(int j = array2.length - 1;j >= 0;j--){
				result[array1.length + array2.length - 2 - i - j] += Character.getNumericValue(array1[i]) * Character.getNumericValue(array2[j]);
			}
		}
		
		int cache = 0;
		StringBuffer sb = new StringBuffer();
		for(int k = 0;k < result.length;k++){
			result[k] += cache;
			cache = result[k] / 10;
			result[k] %= 10;
			sb.insert(0, result[k]);
		}
		
		if(sb.charAt(0) == '0'){
			sb.delete(0, 1);
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		System.out.println(multiply("1378246590", "1378246590"));
	}
}
