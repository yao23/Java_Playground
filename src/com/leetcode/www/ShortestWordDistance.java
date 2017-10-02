package com.leetcode.www;

public class ShortestWordDistance { // LC 243
    public int shortestDistance(String[] words, String word1, String word2) { // not working for test case 1
        int len = words.length;
        int idx1 = -1, idx2 = -1;
        for (int i = 0; i < len && (idx1 < 0 || idx2 < 0); i++) {
            String s = words[i];
            if (s.equals(word1)) {
                idx1 = i;
            } else if (s.equals(word2)) {
                idx2 = i;
            } else {
                continue;
            }
        }

        return Math.abs(idx2 - idx1);
    }
}

// ["a","c","b","a"], "a", "b" => 1
