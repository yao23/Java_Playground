package com.leetcode.www;

public class WildcardMatching { // LC 44
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int lenS = s.length();
        int lenP = p.length();
        if (lenS == 0 && lenP == 0) {
            return true;
        } else {
            boolean[][] dp = new boolean[lenS + 1][lenP + 1];
            dp[0][0] = true;
            for (int j = 1; j <= lenP; j++) {
                if (dp[0][j - 1] && p.charAt(j) == '?' || p.charAt(j) == '*') {
                    dp[0][j] = true;
                }
            }
            for (int i = 0; i < lenS; i++) {
                for (int j = 0; j < lenP; j++) {
                    if (p.charAt(j) == '*') {
                        dp[i + 1][j  + 1] = dp[i][j];
                    } else {
                        if (p.charAt(j + 1) == s.charAt(i + 1) || p.charAt(j + 1) == '?' || p.charAt(j + 1) == '*') {
                            dp[i + 1][j  + 1] = dp[i][j];
                        } else {
                            dp[i + 1][j  + 1] = false;
                        }
                    }
                }
            }
            return dp[lenS][lenP];
        }
    }
}
