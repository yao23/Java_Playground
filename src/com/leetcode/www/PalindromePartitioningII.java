package com.leetcode.www;

public class PalindromePartitioningII {
    public int minCut(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len];
        boolean[][] palindromeMatrix = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            palindromeMatrix[i][i] = true;
        }
        // palindromeMatrix[i][j] checks string from i to j palindrome or not
        // substring length l starts from 2 to len
        for (int l = 2; l <= len; l++) {
            for (int i = 0; i < len - l + 1; i++) {
                int j = i + l - 1;
                if (l == 2) {
                    palindromeMatrix[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    palindromeMatrix[i][j] = (s.charAt(i) == s.charAt(j) && palindromeMatrix[i + 1][j - 1]);
                }
            }
        }
        for (int i = len - 1; i >= 0; i--) {
            if (palindromeMatrix[i][len - 1]) {
                dp[i] = 0;
            } else {
                int MinCut = Integer.MAX_VALUE;
                for (int k = i + 1; k < len; k++) {
                    if (palindromeMatrix[i][k - 1]) {
                        MinCut = Math.min(MinCut, 1 + dp[k]);
                    }
                }
                dp[i] = MinCut;
            }
        }
        return dp[0]; // dp[i] means from index i to end, min cut number
    }
}
