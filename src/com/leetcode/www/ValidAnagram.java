package com.leetcode.www;

public class ValidAnagram { // LC 242
    public boolean isAnagram(String s, String t) { // beats 87.54%
        int[] charsMap = new int['z' - 'a' + 1];

        for (char c: s.toCharArray()) {
            int pos = c - 'a';
            charsMap[pos]++;
        }

        for(char c: t.toCharArray()) {
            int pos = c - 'a';
            charsMap[pos]--;
        }

        for (int count: charsMap) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isAnagramV0(String s, String t) { // beats 68.93%
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            alphabet[t.charAt(i) - 'a']--;
        }
        for (int i : alphabet) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
