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

    /**
     *
     * @param s
     * @param p
     * @return
     *
     * The original post has DP 2d array index from high to low, which is not quite intuitive.

     * Below is another 2D dp solution. Ideal is identical.

     * dp[i][j] denotes whether s[0....i-1] matches p[0.....j-1],

     * First, we need to initialize dp[i][0], i= [1,m]. All the dp[i][0] should be false because p has nothing in it.

     * Then, initialize dp[0][j], j = [1, n]. In this case, s has nothing, to get dp[0][j] = true, p must be '*', '*', '**',etc. Once p.charAt(j-1) != '*', all the dp[0][j] afterwards will be false.

     * Then start the typical DP loop.

     * Though this solution is clear and easy to understand. It is not good enough in the interview. it takes O(mn) time and O(mn) space.

     * Improvement: 1) optimize 2d dp to 1d dp, this will save space, reduce space complexity to O(N). 2) use iterative 2-pointer.
     */
    public boolean isMatchV1(String s, String p) { // beats 61.43% (from beginning to end)
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = false;
        }

        for (int j = 1; j<= n; j++) {
            if(p.charAt(j - 1) == '*'){
                dp[0][j] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
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
