package algorithms.knapsackproblem;

public class CompleteKnapsack {
	public static int maxValue(int total, int[] weight, int[] value) throws Exception{
		int n = weight.length;
		if(n != value.length) throw new Exception("Wrong arrays.");
		int[] dp = new int[total + 1];
		dp[0] = 0;
		for(int j = 1;j <= total;j++){
			for(int i = 0;i < n;i++){
				if(j >= weight[i]){
					dp[j] = Math.max(dp[j - weight[i]] + value[i], dp[j]);
				}
			}
		}
		return dp[total];
	}
	
	public static void main(String[] args){
		int total = 15;
		int[] weight = new int[]{5, 4, 7, 2, 6};
		int[] value = new int[]{12, 3, 10, 3, 6};
		try {
			System.out.println(maxValue(total, weight, value));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int total2 = 10;
		int[] weight2 = new int[]{2, 2, 6, 5, 4};
		int[] value2 = new int[]{6, 3, 5, 4, 6};
		try {
			System.out.println(maxValue(total2, weight2, value2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
