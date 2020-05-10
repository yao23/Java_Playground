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
}
