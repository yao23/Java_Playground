package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;

public class BurstBalloons {
    public int maxCoins(int[] nums) { // beats 53.06% (DP)
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
