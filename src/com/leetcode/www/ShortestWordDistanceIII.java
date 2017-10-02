package com.leetcode.www;

public class ShortestWordDistanceIII { // LC 245
    public int shortestWordDistance(String[] words, String word1, String word2) { // beats 67.31%
        int idx = -1, len = words.length, res = Integer.MAX_VALUE;
        boolean isSame = word1.equals(word2);
        for (int i = 0; i < len; i++) {
            String s = words[i];
            if (s.equals(word1) || s.equals(word2)) {
                if (idx >= 0) {
                    if (isSame) {
                        res = Math.min(res, i - idx);
                    } else {
                        if (!s.equals(words[idx])) {
                            res = Math.min(res, i - idx);
                        }
                    }
                }
                idx = i;
            }
        }
        return res;
    }
}
