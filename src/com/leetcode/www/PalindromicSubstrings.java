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

    public int countSubstringsV0(String s) { // beats 41.67%
        int res = 0, n = s.length();
        for (int i = 0; i < n; i++){
            for (int j = 0; i - j >= 0 && i + j < n && s.charAt(i - j) == s.charAt(i + j); j++) {
                res++; //substring s[i-j, ..., i+j]
            }
            for (int j = 0; i - 1 - j >= 0 && i + j < n && s.charAt(i - 1 -j) == s.charAt(i + j); j++) {
                res++; //substring s[i-1-j, ..., i+j]
            }
        }
        return res;
    }
}
