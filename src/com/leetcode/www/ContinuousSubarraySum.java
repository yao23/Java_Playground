public class ContinuousSubarraySum {
    /**
     * Memory Limit Exceeded
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
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
