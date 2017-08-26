package com.leetcode.www;

public class LongestPalindromicSubstring { // LC 5
    public String longestPalindrome(String s) { // beats 43.63%
        if (s.length() == 0) {
            return "";	// try dynamic programming (summary)
        } else if (s.length() == 1)	{
            return s;
        } else {
            String longestStr = "";
            for (int i = 0; i < s.length() - 1; i++) {
                String oddStr = expandAroundCenter(s, i, i);
                // System.out.println(OddStr);
                if (oddStr.length() > longestStr.length()) {
                    longestStr = oddStr;
                }
                String evenStr = expandAroundCenter(s, i, i + 1);
                // System.out.println(EvenStr);
                if (evenStr.length() > longestStr.length()) {
                    longestStr = evenStr;
                }
            }
            return longestStr;
        }
    }

    private static String expandAroundCenter(String s, int l, int r) {
        int l_tmp = l, r_tmp = r;
        boolean FirstTime = true;

        while (l_tmp >= 0 && r_tmp < s.length() && s.charAt(l_tmp) == s.charAt(r_tmp) ) {
            if( FirstTime ) {
                FirstTime = false;
            }
            l_tmp--;
            r_tmp++;
        }

        if( !FirstTime ) {
            l_tmp++;
            r_tmp--;
        } else {
            r_tmp--;
        }

        return s.substring(l_tmp, r_tmp + 1);
    }
}

// Input: "babad", Output: "bab" (Note: "aba" is also a valid answer.)
// Input: "cbbd", Output: "bb"