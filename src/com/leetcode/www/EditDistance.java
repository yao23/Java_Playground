package com.leetcode.www;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        return match(word1, word2, 0, 0);
    }

    private int match(String word1, String word2, int i, int j) { // TLE (test case 2)
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) {
            return word1.length() - i;
        }

        int res;
        if (word1.charAt(i) == word2.charAt(j)) {
            res = match(word1, word2, i + 1, j + 1);
        } else {
            int insert = match(word1, word2, i, j + 1);
            int delete = match(word1, word2, i + 1, j);
            int replace = match(word1, word2, i + 1, j + 1);
            res = Math.min(Math.min(insert, delete), replace) + 1;
        }

        return res;
    }
}

// "head", "sad" => 2 (delete, replace)
// "dinitrophenylhydrazine", "acetylphenylhydrazine" =>