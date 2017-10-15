package com.leetcode.www;

public class ShortestPalindrome { // LC 214
    /**
     * https://leetcode.com/articles/shortest-palindrome/
     *
     * The idea is to use two anchors j and i to compare the String from beginning and end.
     * If j can reach the end, the String itself is Palindrome. Otherwise, we divide the String by j, and get
     * mid = s.substring(0, j) and suffix.
     * We reverse suffix as beginning of result and recursively call shortestPalindrome to get result of mid then
     * append suffix to get result.
     *
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) { // beats 70.27%
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) { j += 1; }
        }
        if (j == s.length()) {
            return s;
        }
        String suffix = s.substring(j);
        return new StringBuffer(suffix).reverse().toString() + shortestPalindrome(s.substring(0, j)) + suffix;
    }

    /**
     * https://discuss.leetcode.com/topic/27261/clean-kmp-solution-with-super-detailed-explanation
     *
     * KMP Algorithm
     */
    public String shortestPalindromeV0(String s) { // beats 52.42%
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int[] table = getTable(temp);

        //get the maximum palindrome part in s starts from 0
        return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
    }

    private int[] getTable(String s){
        //get lookup table
        int[] table = new int[s.length()];

        //pointer that points to matched char in prefix part
        int index = 0;

        //skip index 0, we will not match a string with itself
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(index) == s.charAt(i)) {
                //we can extend match in prefix and postfix
                table[i] = table[i - 1] + 1;
                index ++;
            } else {
                //match failed, we try to match a shorter substring

                //by assigning index to table[i-1], we will shorten the match string length, and jump to the
                //prefix part that we used to match postfix ended at i - 1
                index = table[i - 1];

                while (index > 0 && s.charAt(index) != s.charAt(i)) {
                    //we will try to shorten the match string length until we revert to the beginning of match (index 1)
                    index = table[index-1];
                }

                //when we are here may either found a match char or we reach the boundary and still no luck
                //so we need check char match
                if (s.charAt(index) == s.charAt(i)) {
                    //if match, then extend one char
                    index ++ ;
                }

                table[i] = index;
            }
        }

        return table;
    }
}

// Given "aacecaaa", return "aaacecaaa".
// Given "abcd", return "dcbabcd".