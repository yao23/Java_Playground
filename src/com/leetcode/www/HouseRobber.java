package com.leetcode.www;

/**
 * Created by liyao on 7/5/17.
 */
public class HouseRobber { // LC 198
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber.
     * Memory Usage: 38.4 MB, less than 5.26% of Java online submissions for House Robber.
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = nums[0];

        if (n > 1) {
            dp[1] = Math.max(dp[0], nums[1]); // input: [2. 1], output: 2
        }
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    public int robV0(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        } else {
            int[] results = new int[len]; // max in cur position
            int max = 0;
            for (int i = 0; i < len; i++) {
                int preMax = 0;
                for (int j = 0; j < i - 1; j++) {
                    if (results[j] > preMax) {
                        preMax = results[j];
                    }
                }
                results[i] = (nums[i] + preMax);

                if (results[i] > max) {
                    max = results[i];
                }
            }

            return max;
        }
    }

    public int robV1(int[] nums) { // beats 40.29%
        int preNo = 0, preYes = 0; // previous one rob no and yes
        for (int n : nums) {
            int tmp = preNo;
            preNo = Math.max(preNo, preYes);
            preYes = tmp + n;
        }

        return Math.max(preNo, preYes);
    }

    // [] => 0
    // [1] => 1
    // [1,2] => 2
    // [1,2,3] => 4
    // [1,5,3] => 5
    // [2,7,9,3,1] => 12

    // beats 4.82%
}