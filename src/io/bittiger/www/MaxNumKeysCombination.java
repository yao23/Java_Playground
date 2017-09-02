package io.bittiger.www;

public class MaxNumKeysCombination {
    public int maxA(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j <= i - 3; j++) {
                dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
            }
        }
        return   dp[n];
    }

    public int maxAV0(int N) {
        if (N <= 6) {
            return N;
        } else {
            int[] dp = new int[N + 1];
            for (int i = 0; i <= N; i++) {
                dp[i] = i;
            }
            for (int i = 3; i < N - 2; i++) {
                int num = dp[i] * 2;
                int j = i + 3;
                dp[j] = Math.max(dp[j], num);
                while (j < N) {
                    num += dp[i];
                    j++;
                    dp[j] = Math.max(dp[j], num);
                }
            }
            return dp[N];
        }
    }
}

// http://articles.leetcode.com/ctrla-ctrlc-ctrlv/
// http://www.geeksforgeeks.org/how-to-print-maximum-number-of-a-using-given-four-keys/
// http://www.cnblogs.com/grandyang/p/7448390.html
