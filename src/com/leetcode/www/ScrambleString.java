package com.leetcode.www;

public class ScrambleString { // LC 87
    public boolean isScramble(String s1, String s2) { // beats 64.52% (recursion)
        if (s1.equals(s2)) {
            return true;
        }

        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) {
                return false;
            }
        }

        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            // root is non-left node, so it has to check 1st level left and right child (test case 5, "abc", "bca")
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i))
                    && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                return true;
            }
        }
        return false;
    }

    public boolean isScrambleV1(String s1, String s2) { // beats 86.81% (iterative)
        if (s1.length() != s2.length()) {
            return false;
        }
        int len = s1.length();
        /**
         * Let F(i, j, k) = whether the substring S1[i..i + k - 1] is a scramble of S2[j..j + k - 1] or not
         * Since each of these substrings is a potential node in the tree, we need to check for all possible cuts.
         * Let q be the length of a cut (hence, q < k), then we are in the following situation:
         *
         * S1 [   x1    |         x2         ]
         *    i         i + q                i + k - 1
         *
         * here we have two possibilities:
         *
         * S2 [   y1    |         y2         ]
         *    j         j + q                j + k - 1
         *
         * or
         *
         * S2 [       y1        |     y2     ]
         *    j                 j + k - q    j + k - 1
         *
         * which in terms of F means:
         *
         * F(i, j, k) = for some 1 <= q < k we have:
         *  (F(i, j, q) AND F(i + q, j + q, k - q)) OR (F(i, j + k - q, q) AND F(i + q, j, k - q))
         *
         * Base case is k = 1, where we simply need to check for S1[i] and S2[j] to be equal
         * */
        boolean [][][] F = new boolean[len][len][len + 1];
        for (int k = 1; k <= len; ++k) {
            for (int i = 0; i + k <= len; ++i) {
                for (int j = 0; j + k <= len; ++j) {
                    if (k == 1) {
                        F[i][j][k] = s1.charAt(i) == s2.charAt(j);
                    } else {
                        for (int q = 1; q < k && !F[i][j][k]; ++q) {
                            F[i][j][k] = (F[i][j][q] && F[i + q][j + q][k - q]) || (F[i][j + k - q][q] && F[i + q][j][k - q]);
                        }
                    }
                }
            }
        }
        return F[0][0][len];
    }

    public boolean isScrambleV0(String s1, String s2) { // not working for test case 5 ("abc", "bca")
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
// "abc", "bca" => true

/**
 * root is non-leaf node, swap("a, "bc"), then it's "bca"
 */