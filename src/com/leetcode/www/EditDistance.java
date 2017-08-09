package com.leetcode.www;

public class EditDistance { // LC 72
    public int minDistance(String word1, String word2) {
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