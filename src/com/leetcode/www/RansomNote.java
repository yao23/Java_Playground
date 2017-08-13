package com.leetcode.www;

public class RansomNote { // LC 383
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || magazine == null) {
            return false;
        }
        char[] chRan = ransomNote.toCharArray();
        char[] chMag = magazine.toCharArray();
        int[] map = new int[26];

        for (char ch : chMag) {
            map[ch - 'a']++;
        }

        for (char ch : chRan) {
            map[ch - 'a']--;
            if (map[ch - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }
}

// canConstruct("a", "b") -> false
// canConstruct("aa", "ab") -> false
// canConstruct("aa", "aab") -> true
// "a", "b" => false

// beats 96.54%