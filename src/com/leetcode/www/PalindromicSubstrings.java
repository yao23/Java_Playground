package com.leetcode.www;

public class PalindromicSubstrings { // LC 647
    int count = 0;

    public int countSubstrings(String s) { // beats 89.41%
        if (s == null || s.length() == 0) {
            return 0;
        }

        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }

        return count;
    }

    private void extendPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
    }
}
