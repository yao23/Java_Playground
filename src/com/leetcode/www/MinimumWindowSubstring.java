package com.leetcode.www;

public class MinimumWindowSubstring { // LC 76
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || t.length() > s.length()) {
            return "";
        }
        int[] map = new int[128];
        int count = t.length(), start = 0, end = 0, minLen = Integer.MAX_VALUE, startIdx = 0;

        for (char c : t.toCharArray()) {
            map[c]++;
        }

        char[] chS = s.toCharArray();
        while (end < chS.length) {
            if (map[chS[end++]]-- > 0) {
                count--;
            }
            while (count == 0) {
                if (end - start < minLen) {
                    startIdx = start;
                    minLen = end - start;
                }
                if (map[chS[start++]]++ == 0) {
                    count++;
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : new String(chS, startIdx, minLen);
    }
}

// S = "ADOBECODEBANC", T = "ABC" => "BANC"
// "a", "a" => "a"

// beats 91.41%