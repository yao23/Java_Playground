package com.leetcode.www;

public class ScrambleString { // LC 87
    public boolean isScramble(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 != len2) {
            return false;
        }
        boolean[] dp = new boolean[len1 + 1];
        dp[0] = true;
        for (int len = 1; len <= len1; len++) {
            for (int i = 0; i < len; i++) {
                if (dp[i]) {
                    String right1 = s1.substring(i, len);
                    String right2 = s2.substring(i, len);
                    if (isMatch(right1, right2)) {
                        dp[len] = true;
                    }
                }
            }
        }
        return dp[len1];
    }

    private boolean isMatch(String s1, String s2) {
        // base case when length is 1, otherwise infinite loop to stack overflow
        if (s1.length() == 1) {
            return s1.equals(s2);
        }
        if (s1.equals(s2)) {
            return true;
        } else if (s1.equals(reverseStr(s2))) {
            return true;
        } else {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == s2.charAt(i) && isMatch(s1.substring(i + 1), s2.substring(i + 1))) {
                    continue;
                } else if (isMatch(s1.substring(0, i + 1), s2.substring(0, i + 1)) && isMatch(s1.substring(i + 1),  s2.substring(i + 1))) {
                    continue;
                } else {
                    return false;
                }
            }

            return true;
        }
    }

    private String reverseStr(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}

// choose any non-leaf node and swap its two children

// s1 = "great", s2 = "rgeat" => true
//        great
//        /    \
//       gr    eat
//      / \    /  \
//     g   r  e   at
//                / \
//               a   t
//
// choose "gr"
//
//         rgeat
//        /    \
//       rg    eat
//      / \    /  \
//     r   g  e   at
//                / \
//               a   t
//
// if continue to swap the children of nodes "eat" and "at"
//
//         rgtae
//        /    \
//       rg    tae
//      / \    /  \
//     r   g  ta  e
//            / \
//           t   a
//
// s1 = "great", s2 = "rgtae" => true

// "a", "a" => true
// "aa", "ab" => false