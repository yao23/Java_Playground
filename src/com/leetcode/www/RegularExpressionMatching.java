package com.leetcode.www;

public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        int lenS = s.length();
        int lenP = p.length();
        boolean[][] dp = new boolean[lenS + 1][lenP + 1];

        for (int j = 1; j <= lenP; j++) {
            if (p.charAt(j - 1) == '*' && dp[0][j - 2]) {
                dp[0][j] = true;
            }
        }

        for (int i = 0; i <= lenS; i++) {
            for (int j = 0; j <= lenP; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2] || (dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.');
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                }
            }
        }

        return dp[lenS][lenP];
    }
}
