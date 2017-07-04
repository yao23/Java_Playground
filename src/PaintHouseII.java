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

    /**
     *
     * @param costs
     * @return
     *
     * To solve this DP problem:
     *   If there's no constraint, we choose min cost for each house.
     *   Since house[i] and house[i - 1] cannot have the same color j, we should choose 2nd min color for house[i - 1].
     *   If we choose the 3rd min color for house[i - 1], we might miss potential min cost.
     *   min(i) = min(cost[i][j] + 1st min / 2nd min), 0 < j < n.
     *   Since current row only relies on last row for getting mins and avoiding same color, O(1) space is enough.
     *
     * https://discuss.leetcode.com/topic/30659/easiest-o-1-space-java-solution
     */
    public int minCostIIV0(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        } else {
            int min1 = 0, min2 = 0, index1 = -1; // last house cost

            for (int i = 0; i < costs.length; i++) {
                int m1 = Integer.MAX_VALUE, m2 = Integer.MAX_VALUE, idx1 = -1; // cur house cost

                for (int j = 0; j < costs[0].length; j++) {
                    int cost = costs[i][j] + (j != index1 ? min1 : min2);

                    if (cost < m1) { // cost < m1 < m2
                        m2 = m1;
                        m1 = cost;
                        idx1 = j;
                    } else if (cost < m2) { // m1 < cost < m2
                        m2 = cost;
                    }
                }

                min1 = m1;
                min2 = m2;
                index1 = idx1;
            }
            return min1;
        }
    }
}
