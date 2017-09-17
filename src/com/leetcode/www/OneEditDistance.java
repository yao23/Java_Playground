package com.leetcode.www;

public class OneEditDistance { // LC 161
    public boolean isOneEditDistance(String s, String t) { // beats 49.94%
        if (s == null || t == null) {
            return false;
        }

        if (s.length() > t.length()) {
            return isOneEditDistance(t, s);
        }

        int i = 0, j = 0;

        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) != t.charAt(j)) {
                // we try to replace s[i] with s[j] or insert s[j] to s[i]
                // then compare the rest and see if they are the same
                return s.substring(i + 1).equals(t.substring(j + 1)) ||
                        s.substring(i).equals(t.substring(j + 1));
            }

            i++;
            j++;
        }

        return t.length() - j == 1;
    }

    public boolean isOneEditDistanceV1(String s, String t) { // beats 12.49%
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) {// s has the same length as t, so the only possibility is replacing one char in s and t
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else if (s.length() < t.length()) { // t is longer than s, so the only possibility is deleting one char from t
                    return s.substring(i).equals(t.substring(i + 1));
                } else { // s is longer than t, so the only possibility is deleting one char from s
                    return t.substring(i).equals(s.substring(i + 1));
                }
            }
        }
        //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t
        return Math.abs(s.length() - t.length()) == 1;
    }

    /**
     *
     * @param s
     * @param t
     * @return
     *
     * DP based solution, similar like Edit Distance
     */
    public boolean isOneEditDistanceV0(String s, String t) { // TLE for test case 128 (very long string), pass 127/131
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
