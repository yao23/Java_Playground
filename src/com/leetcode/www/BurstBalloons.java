package com.leetcode.www;

public class BurstBalloons { // LC 312
    public int maxCoins(int[] nums) { // beats 94.12% (Memorized Search)
        int len = nums.length;
        if (len == 0) {
            return 0;
        } else if (len == 1) {
            return nums[0];
        } else {
            int[] numbers = new int[len + 2];
            int n = 1;
            for (int x : nums) {
                if (x > 0) {
                    numbers[n] = x;
                    n++;
                }
            }
            numbers[0] = numbers[n++] = 1;

            int[][] memo = new int[n][n];
            return burst(memo, numbers, 0, n - 1);
        }
    }

    private int burst(int[][] memo, int[] nums, int left, int right) {
        if (left + 1 == right) {
            return 0;
        }
        if (memo[left][right] > 0) {
            return memo[left][right];
        }
        int ans = 0;
        for (int i = left + 1; i < right; ++i) {
            ans = Math.max(ans, nums[left] * nums[i] * nums[right]
                    + burst(memo, nums, left, i) + burst(memo, nums, i, right));
        }
        memo[left][right] = ans;
        return ans;
    }

    public int maxCoinsV0(int[] nums) { // beats 53.06% (DP)
        int len = nums.length;
        if (len == 0) {
            return 0;
        } else if (len == 1) {
            return nums[0];
        } else {
            int[] numbers = new int[len + 2];
            int n = 1;
            for (int x : nums) {
                if (x > 0) {
                    numbers[n++] = x;
                }
            }
            numbers[0] = numbers[n++] = 1;


            int[][] dp = new int[n][n];
            for (int k = 2; k < n; ++k)
                for (int left = 0; left < n - k; ++left) {
                    int right = left + k;
                    for (int i = left + 1; i < right; ++i) {
                        dp[left][right] = Math.max(dp[left][right],
                                numbers[left] * numbers[i] * numbers[right] + dp[left][i] + dp[i][right]);
                    }
                }

            return dp[0][n - 1];
        }
    }
}
