package com.leetcode.www;

public class RegularExpressionMatching { // LC 10
    public boolean isMatch(String s, String p) { // beats 98.26%
        if (s == null || p == null) {
            return false;
        }
        int lenS = s.length();
        int lenP = p.length();
        boolean[][] dp = new boolean[lenS + 1][lenP + 1];
        dp[0][0] = true;
        for (int j = 0; j < lenP; j++) {
            if (p.charAt(j) == '*' && dp[0][j - 1]) {
                dp[0][j + 1] = true;
            }
        }

        for (int i = 0; i < lenS; i++) {
            for (int j = 0; j < lenP; j++) {
                if (p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') { // last one not match
                        dp[i + 1][j + 1] = dp[i + 1][j - 1]; // last 2nd
                    } else { // last one match
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]); // last 2nd, last one or next one
                    }
                }
            }
        }

        return dp[lenS][lenP];
    }

    public boolean isMatchV0(String s, String p) { // beats 4.13 (recursion)
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.isEmpty() || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))) {
                return false;
            } else {
                return isMatchV0(s.substring(1), p.substring(1));
            }
        }

        //P.length() >=2
        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
            if (isMatchV0(s, p.substring(2))) {
                return true;
            }
            s = s.substring(1);
        }

        return isMatchV0(s, p.substring(2));
    }
}

// isMatch("aa","a") ? false
// isMatch("aa","aa") ? true
// isMatch("aaa","aa") ? false
// isMatch("aa", "a*") ? true
// isMatch("aa", ".*") ? true
// isMatch("ab", ".*") ? true (* is used for 1 dot . and ".." matches with "ab")
// isMatch("aab", "c*a*b") ? true


/**
 *
 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
 3, If p.charAt(j) == '*':
 here are two sub conditions:
 1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
 2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
 dp[i][j] = dp[i-1][j]    //in this case, "a*" counts as multiple "a" ("aaa...")
 or dp[i][j] = dp[i][j-1]   // in this case, "a*" counts as single "a"
 or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty (no "a", namely "")

 */
