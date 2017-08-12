package com.leetcode.www;

public class WildcardMatching { // LC 44
    public boolean isMatch(String s, String p) { // beats 58.79%
        int lenS = s.length();
        int lenP = p.length();
        boolean[][] match = new boolean[lenS + 1][lenP + 1];
        match[lenS][lenP] = true;
        for (int i = p.length() - 1; i >= 0; i--) {
            if (p.charAt(i) != '*') {
                break;
            } else {
                match[lenS][i] = true;
            }
        }
        for (int i = lenS - 1; i >= 0; i--) {
            for (int j = lenP - 1; j >= 0; j--) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    match[i][j] = match[i + 1][j + 1];
                } else if(p.charAt(j) == '*') {
                    match[i][j] = match[i + 1][j] || match[i][j + 1]; // * match multiple or empty
                } else {
                    match[i][j] = false;
                }
            }
        }
        return match[0][0];
    }

    public boolean isMatchV0(String s, String p) { // not working
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
