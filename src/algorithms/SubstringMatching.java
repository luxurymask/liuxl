package algorithms;

public class SubstringMatching {
	public static int bruteForce(String pattern, String string){
		int m = pattern.length();
		int n = string.length();
		
		if(m == 0 || n == 0){
			return -1;
		}
		
		int i = 0, j = 0, k = 0;
		while(i < n){
			if(string.charAt(j) == pattern.charAt(k)){
				j++;
				k++;
			}else{
				i++;
				j = i;
				k = 0;
			}
			if(k == m){
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args){
		String string = "@#$#@dsalkdjflksn";
		String pattern = "lkdj";
		String pattern2 = "wlj";
		System.out.println(bruteForce(pattern, string));
		System.out.println(bruteForce(pattern2, string));
	}
}
