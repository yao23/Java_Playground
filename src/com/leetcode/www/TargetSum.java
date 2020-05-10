import java.util.Arrays;

public class TargetSum { // LC 494 (FB)
    // https://leetcode.com/discuss/interview-question/357345/
    // https://leetcode.com/problems/target-sum/solution/

    int count = 0;

    /**
     * Brute Force
     *
     * Time complexity : O(2^n). Size of recursion tree will be 2^n. n refers to the size of nums array.
     * Space complexity : O(n). The depth of the recursion tree can go upto n.
     *
     * Runtime: 496 ms, faster than 16.95% of Java online submissions for Target Sum.
     * Memory Usage: 37.5 MB, less than 40.00% of Java online submissions for Target Sum.
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        calculate(nums, 0, 0, S);
        return count;
    }
    public void calculate(int[] nums, int i, int sum, int S) {
        if (i == nums.length) {
            if (sum == S) {
                count++;
            }
        } else {
            calculate(nums, i + 1, sum + nums[i], S);
            calculate(nums, i + 1, sum - nums[i], S);
        }
    }

    public class Solution {
        int count = 0;

        /**
         * Recursion with Memoization
         *
         * Thus, for every call to calculate(nums, i, sum, S), we store the result obtained in memo[i][sum + 1000].
         * The factor of 1000 has been added as an offset to the sum value to map all the sums possible to positive
         * integer range. By making use of memoization, we can prune the search space to a good extent.
         *
         * Time complexity : O(l*n). The memo array of size l*n has been filled just once. Here, l refers to the range
         * of sum and n refers to the size of nums array.
         * Space complexity : O(n). The depth of recursion tree can go upto n.
         *
         * Runtime: 28 ms, faster than 57.49% of Java online submissions for Target Sum.
         * Memory Usage: 51.9 MB, less than 6.00% of Java online submissions for Target Sum.
         *
         * @param nums
         * @param S
         * @return
         */
        public int findTargetSumWays(int[] nums, int S) {
            int[][] memo = new int[nums.length][2001];
            for (int[] row: memo) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }
            return calculate(nums, 0, 0, S, memo);
        }
        public int calculate(int[] nums, int i, int sum, int S, int[][] memo) {
            if (i == nums.length) {
                if (sum == S) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                if (memo[i][sum + 1000] != Integer.MIN_VALUE) {
                    return memo[i][sum + 1000];
                }
                int add = calculate(nums, i + 1, sum + nums[i], S, memo);
                int subtract = calculate(nums, i + 1, sum - nums[i], S, memo);
                memo[i][sum + 1000] = add + subtract;
                return memo[i][sum + 1000];
            }
        }
    }

    public class SolutionV1 {
        /**
         * 2D Dynamic Programming
         * The idea behind this approach is as follows. Suppose we can find out the number of times a particular sum,
         * say sum_i is possible upto a particular index, say i, in the given nums array, which is given by say count_i.
         * Now, we can find out the number of times the sum sum_i + nums[i] can occur easily as count_i. Similarly,
         * the number of times the sum sum_i - nums[i] occurs is also given by count_i.
         *
         * Thus, if we know all the sums sum_j's which are possible upto the j^{th} index by using various assignments,
         * along with the corresponding count of assignments, count_j, leading to the same sum, we can determine all
         * the sums possible upto the (j+1)^{th} index along with the corresponding count of assignments leading to the
         * new sums.
         *
         * Based on this idea, we make use of a dp to determine the number of assignments which can lead to the given sum.
         * dp[i][j] refers to the number of assignments which can lead to a sum of j upto the i^{th} index.
         * To determine the number of assignments which can lead to a sum of sum + nums[i] upto the (i+1)^{th} index,
         * we can use dp[i][sum + nums[i]] = dp[i][sum + nums[i]] + dp[i-1][sum].
         * Similarly, dp[i][sum - nums[i]] = dp[i][sum + nums[i]] + dp[i-1][sum].
         * We iterate over the dp array in a rowwise fashion
         * i.e. Firstly we obtain all the sums which are possible upto a particular index along with the corresponding
         * count of assignments and then proceed for the next element(index) in the nums array.
         *
         * But, since the sum can range from -1000 to +1000, we need to add an offset of 1000 to the sum indices
         * (column number) to map all the sums obtained to positive range only.
         *
         * At the end, the value of dp[n-1][S+1000] gives us the required number of assignments.
         * Here, n refers to the number of elements in the nums array.
         *
         * Time complexity : O(l*n). The entire nums array is traversed 2001(constant no.: l) times.
         * n refers to the size of nums array. l refers to the range of sum possible.
         * Space complexity : O(l*n). dp array of size l*n is used.
         *
         * Runtime: 32 ms, faster than 56.37% of Java online submissions for Target Sum.
         * Memory Usage: 51.4 MB, less than 6.00% of Java online submissions for Target Sum.
         *
         * @param nums
         * @param S
         * @return
         */
        public int findTargetSumWays(int[] nums, int S) {
            int[][] dp = new int[nums.length][2001];
            dp[0][nums[0] + 1000] = 1;
            dp[0][-nums[0] + 1000] += 1;
            for (int i = 1; i < nums.length; i++) {
                for (int sum = -1000; sum <= 1000; sum++) {
                    if (dp[i - 1][sum + 1000] > 0) {
                        dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                        dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                    }
                }
            }
            return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
        }
    }
}
