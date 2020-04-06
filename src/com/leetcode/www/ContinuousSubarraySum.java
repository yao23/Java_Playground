public class ContinuousSubarraySum {
    /**
     * Runtime: 29 ms, faster than 5.11% of Java online submissions for Continuous Subarray Sum.
     * Memory Usage: 53 MB, less than 5.88% of Java online submissions for Continuous Subarray Sum.
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySumV2(int[] nums, int k) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++)
            sum[i] = sum[i - 1] + nums[i]; // sum[i] - sum from index 0 to i
        for (int start = 0; start < nums.length - 1; start++) {
            for (int end = start + 1; end < nums.length; end++) {
                int summ = sum[end] - sum[start] + nums[start];
                if (summ == k || (k != 0 && summ % k == 0))
                    return true;
            }
        }
        return false;
    }

    /**
     * Memory Limit Exceeded
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySumV1(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 && k != 0) {
            return false;
        }

        int[][] dp = new int[n + 1][n]; // int[i][j] - start index j and length i
        for (int i = 0; i < n; i++) {
            dp[1][i] = nums[i];
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                dp[l][i] = dp[l - 1][i] + nums[i + l - 1];
                if (isKMultiple(dp[l][i], k)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Wrong Answer
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySumV0(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 && k != 0) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (i > 0) {
                if (isKMultiple(sum, k)) {
                    return true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (i < n - 1) {
                if (isKMultiple(sum, k)) {
                    return true;
                }
            }
            sum -= nums[i];
        }
        return false;
    }

    private boolean isKMultiple(int num, int k) {
        if (k == 0) {
            return num == 0;
        } else {
            return num % k == 0;
        }
    }
}
