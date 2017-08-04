package algorithms.substringmatching;

/**
 * 暴力谁不会
 * @author liuxl
 *
 */
public class BruteForce {
	/**
	 * brute force.
	 * @param pattern
	 * @param target
	 * @return
	 */
	public static int bruteForce(String pattern, String target){
		int m = pattern.length();
		int n = target.length();
		
		if(m == 0 || n == 0){
			return -1;
		}
		
		int i = 0, j = 0, k = 0;
		while(i < n){
			if(target.charAt(j) == pattern.charAt(k)){
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
