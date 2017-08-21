package algorithms.LCS;

public class LongestCommonSubsequenceLength {
	
	public static int getLength(String s1, String s2){
		int m = s1.length();
		int n = s2.length();
		return getLengthBottomUp(s1, s2, m, n);
	}
	
	public static int getLengthRecursively(String s1, String s2, int i, int j){
		if(i < 0 || j < 0) return 0;
		char c1 = s1.charAt(i - 1);
		char c2 = s2.charAt(j - 1);
		if(c1 == c2){
			return getLengthRecursively(s1, s2, i - 2, j - 2) + 1;
		}else{
			return Math.max(getLengthRecursively(s1, s2, i - 1, j - 2), getLengthRecursively(s1, s2, i - 2, j - 1));
		}
	}
	
	public static int getLengthBottomUp(String s1, String s2, int m, int n){
		int[][] c = new int[m + 1][n + 1];
		for(int i = 0;i <= m;i++){
			c[i][0] = 0;
		}
		for(int j = 0;j <= n;j++){
			c[0][j] = 0;
		}
		for(int i = 1;i <= m;i++){
			for(int j = 1;j <= n;j++){
				char c1 = s1.charAt(i - 1);
				char c2 = s2.charAt(j - 1);
				if(c1 == c2){
					c[i][j] = c[i - 1][j - 1] + 1;
				}else{
					c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
				}
			}
		}
		return c[m][n];
	}
	
	public static void main(String[] args){
		System.out.println(getLength("sea", "eat"));
		System.out.println(getLength("dinitrophenylhydrazine", "benzalphenylhydrazone"));
	}
}
