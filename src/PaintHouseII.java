/**
 * Created by liyao on 7/3/17.
 */
public class PaintHouseII {
    public int minCostII(int[][] costs) {
        if(costs.length == 0) {
            return 0;
        } else {
            int n = costs.length, k = costs[0].length;
            int[][] dp = new int[n][k];

            int min1 = -1, min2 = -1;

            for (int i = 0; i < n; i++) {
                int last1 = min1, last2 = min2;
                min1 = -1; min2 = -1;

                for (int j = 0; j < k; j++) {
                    if(last1 == -1) {
                        dp[i][j] = costs[i][j];
                    } else if(j == last1) {
                        dp[i][j] = dp[i - 1][last2] + costs[i][j];
                    } else {
                        dp[i][j] = dp[i - 1][last1] + costs[i][j];
                    }

                    if (min1 == -1 || dp[i][j] < dp[i][min1]) {
                        min2 = min1;
                        min1 = j;
                    } else if(min2 == -1 || dp[i][j] < dp[i][min2]) {
                        min2 = j;
                    }
                }
            }

            return dp[n - 1][min1];
        }
    }
}
