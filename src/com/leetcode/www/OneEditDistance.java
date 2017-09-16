package com.leetcode.www;

public class OneEditDistance { // LC 161
    public boolean isOneEditDistance(String s, String t) { // TLE for test case 128 (very long string), pass 127/131
        int lenS = s.length();
        int lenT = t.length();
        if (Math.abs(lenS - lenT) > 1) {
            return false;
        } else {
            int[][] dp = new int[lenS + 1][lenT + 1];
            for (int i = 0; i <= lenS; i++) {
                dp[i][0] = i;
            }
            for (int i = 0; i <= lenT; i++) {
                dp[0][i] = i;
            }
            for (int i = 1; i <= lenS; i++) {
                for (int j = 1; j <= lenT; j++) {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]) + 1);
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    }
                }
            }
            return dp[lenS][lenT] == 1;
        }
    }
}

// "", "" => false
