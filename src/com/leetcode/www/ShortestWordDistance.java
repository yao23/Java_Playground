package com.leetcode.www;

public class ShortestWordDistance { // LC 243
    public int shortestDistance(String[] words, String word1, String word2) { // beats 79.02%
        int index = -1, minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (index != -1 && !words[index].equals(words[i])) {
                    minDistance = Math.min(minDistance, i - index);
                }
                index = i;
            }
        }
        return minDistance;
    }

    public int shortestDistanceV0(String[] words, String word1, String word2) { // beats 79.02%
        int len = words.length;
        int res = Integer.MAX_VALUE;
        int idx1 = -1, idx2 = -1;
        for (int i = 0; i < len; i++) {
            String s = words[i];
            if (s.equals(word1)) {
                idx1 = i;
                if (idx2 >= 0) {
                    res = Math.min(res, Math.abs(idx2 - idx1));
                }
            } else if (s.equals(word2)) {
                idx2 = i;
                if (idx1 >= 0) {
                    res = Math.min(res, Math.abs(idx2 - idx1));
                }
            } else {
                continue;
            }
        }

        return res;
    }
}

// ["a","c","b","a"], "a", "b" => 1
