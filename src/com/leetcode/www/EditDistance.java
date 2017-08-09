package com.leetcode.www;

public class EditDistance { // LC 72
    public int minDistance(String word1, String word2) { // beats 92.86% (DP)
        if (word1 == null || word2 == null) {
            return 0;
        }
        int len1 = word1.length();
        int len2 = word2.length();
        if (len1 == 0 || len2 == 0) {
            return (len1 == 0) ? len2 : len1;
        }

        int[][] count = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            count[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            count[0][j] = j;
        }
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    count[i + 1][j + 1] = count[i][j];
                } else {
                    count[i + 1][j + 1] = Math.min(Math.min(count[i][j], count[i][j + 1]), count[i + 1][j]) + 1;
                }
            }
        }

        return count[len1][len2];
    }

    public int minDistanceV0(String word1, String word2) { // memorized search
        int[][] count = new int[word1.length()][word2.length()];
        return match(word1, word2, 0, 0, count);
    }

    private int match(String word1, String word2, int i, int j, int[][] count) { // beats 92.86%
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) {
            return word1.length() - i;
        }

        if (count[i][j] != 0) {
            return count[i][j];
        }

        int res;
        if (word1.charAt(i) == word2.charAt(j)) {
            res = match(word1, word2, i + 1, j + 1, count);
        } else {
            int insert = match(word1, word2, i, j + 1, count);
            int delete = match(word1, word2, i + 1, j, count);
            int replace = match(word1, word2, i + 1, j + 1, count);
            res = Math.min(Math.min(insert, delete), replace) + 1;
        }

        count[i][j] = res;
        return res;
    }

    private int matchV0(String word1, String word2, int i, int j) { // TLE (test case 2)
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) {
            return word1.length() - i;
        }

        int res;
        if (word1.charAt(i) == word2.charAt(j)) {
            res = matchV0(word1, word2, i + 1, j + 1);
        } else {
            int insert = matchV0(word1, word2, i, j + 1);
            int delete = matchV0(word1, word2, i + 1, j);
            int replace = matchV0(word1, word2, i + 1, j + 1);
            res = Math.min(Math.min(insert, delete), replace) + 1;
        }

        return res;
    }
}

// "head", "sad" => 2 (delete, replace)
// "dinitrophenylhydrazine", "acetylphenylhydrazine" => 6