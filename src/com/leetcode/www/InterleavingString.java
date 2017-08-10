package com.leetcode.www;

public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) { // beats 92.88%
        if (s1.length() == 0 && s2.length() == 0 && s3.length() == 0) {
            return true;
        }

        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len3 != len1 + len2) {
            return false;
        }
        int[][] valid = new int[len1 + 1][len2 + 1];
        return dfsHelper(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0, valid);
    }

    private boolean dfsHelper(char[] c1, char[] c2, char[] c3, int i, int j, int k, int[][] valid) {
        if (valid[i][j] != 0) {
            return valid[i][j] == 1;
        }
        if (k == c3.length) {
            valid[i][j] = 1;
            return true;
        }

        valid[i][j] = (i < c1.length && c1[i] == c3[k] && dfsHelper(c1, c2, c3, i + 1, j, k + 1, valid) ||
                        j < c2.length && c2[j] == c3[k] && dfsHelper(c1, c2, c3, i, j + 1, k + 1, valid)) ? 1 : -1;

        return valid[i][j] == 1;
    }
}

// "", "", "" => true

// s1 = "aabcc",s2 = "dbbca",
// s3 = "aadbbcbcac", return true.
// s3 = "aadbbbaccc", return false.


