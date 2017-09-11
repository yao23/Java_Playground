package com.leetcode.www;

public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        if (s.length() == 0 || s == null) {
            return 0;
        }
        if (t.length() == 0 || t == null) {
            return 1;
        }
        int sLen = s.length(), tLen = t.length();
        if (sLen < tLen) {
            return 0;
        }
        int[][] numDisSub = new int[sLen + 1][tLen + 1];
        for (int i = 0; i < sLen + 1; i++) {
            numDisSub[i][0] = 1;
        }
        for (int i = 1; i < tLen + 1; i++) {
            numDisSub[0][i] = 0;
        }
        for (int i = 1; i < sLen + 1; i++) {
            for (int j = 1; j < tLen + 1; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    numDisSub[i][j] = numDisSub[i - 1][j - 1] + numDisSub[i - 1][j];
                } else {
                    numDisSub[i][j] = numDisSub[i - 1][j];
                }
            }
        }
        return numDisSub[sLen][tLen];
    }
}
