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
}
