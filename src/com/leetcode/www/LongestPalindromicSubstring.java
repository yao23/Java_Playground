package com.leetcode.www;

public class LongestPalindromicSubstring { // LC 5
    private int lo, maxLen;

    public String longestPalindrome(String s) { // beats 91.56%
        int len = s.length();
        if (len < 2) {
            return s;
        }

        for (int i = 0; i < len - 1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i + 1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }

    public String longestPalindromeV1(String s) { // beats 43.63%
        if (s.length() == 0) {
            return "";
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

    public static String longestPalindromeV0(String s) { // beats 23.13% (DP)
        String tmpS = preprocess(s);
        int[] p = new int[tmpS.length()];
        int centerIdx = 0, maxBound = 0;
        for (int i = 1; i < tmpS.length() - 1; i++ ) {
            int j = 2 * centerIdx - i; // i's mirror index in left
            if (maxBound > i) {
                p[i] = Math.min(maxBound - i, p[j]);
            } else {
                p[i] = 0;
            }

            while (tmpS.charAt(i - 1 - p[i]) == tmpS.charAt(i + 1 + p[i])) {
                p[i]++;
            }

            if (i + p[i] > maxBound) {
                maxBound = i + p[i];
                centerIdx = i;
            }
        }
        int maxLen = 0;
        for (int i = 1; i < tmpS.length() - 1; i++) {
            // System.out.print(p[i] + " ");
            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIdx = i;
            }
        }
        int start = (centerIdx - 1 - maxLen) >> 1; // skip the "^"
        // System.out.println("\n start: " + start + " MaxLen: " + MaxLen);
        if (start + maxLen == s.length()) {
            return s.substring(start);
        } else {
            return s.substring(start, start + maxLen);
        }
    }

    private static String preprocess(String s) {
        String res = "";
        res += "^"; // string start
        for (int i = 0; i < s.length(); i++) {
            res = res + "#" + s.charAt(i);
        }
        res += "#";
        res += "$"; // string end
        //System.out.println("Preprocessed string: " + res);
        return res;
    }
}

// Input: "babad", Output: "bab" (Note: "aba" is also a valid answer.)
// Input: "cbbd", Output: "bb"