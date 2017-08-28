package algorithms.knapsackproblem;

public class BooleanKnapsack {
	public static int maxValue(int total, int[] weight, int[] value) throws Exception {
		int n = weight.length;
		if (n != value.length)
			throw new Exception("Wrong arrays.");
		int[][] dp = new int[n + 1][total + 1];
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 0;
		}
		for (int j = 0; j <= total; j++) {
			dp[0][j] = 0;
		}

		for (int j = 1; j <= total; j++) {
			for (int i = 1; i <= n; i++) {
				if (j >= weight[i - 1]) {
					dp[i][j] = Math.max(dp[i - 1][j - weight[i - 1]] + value[i - 1], dp[i - 1][j]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[n][total];
	}

	public static int maxValueOptimized(int total, int[] weight, int[] value) throws Exception {
		int n = weight.length;
		if (n != value.length)
			throw new Exception("Wrong arrays.");
		int[] dp = new int[total + 1];
		for (int i = 0; i < n; i++) {
			for (int j = total; j >= weight[i]; j--) {
				dp[j] = Math.max(dp[j - weight[i]] + value[i], dp[j]);
			}
		}
		return dp[total];
	}

	public static void main(String[] args) {
		int total = 15;
		int[] weight = new int[] { 5, 4, 7, 2, 6 };
		int[] value = new int[] { 12, 3, 10, 3, 6 };

		int total2 = 10;
		int[] weight2 = new int[] { 2, 2, 6, 5, 4 };
		int[] value2 = new int[] { 6, 3, 5, 4, 6 };

		int total3 = 10;
		int[] weight3 = new int[] { 1, 3, 8, 2, 4 };
		int[] value3 = new int[] { 2, 4, 10, 5, 7 };

		try {
			System.out.println(maxValue(total, weight, value));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			System.out.println(maxValue(total2, weight2, value2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			System.out.println(maxValue(total3, weight3, value3));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			System.out.println(maxValueOptimized(total, weight, value));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			System.out.println(maxValueOptimized(total2, weight2, value2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			System.out.println(maxValueOptimized(total3, weight3, value3));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
