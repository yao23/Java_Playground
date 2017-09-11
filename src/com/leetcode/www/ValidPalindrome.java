package com.leetcode.www;

public class ValidPalindrome { // LC 125
    /**
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) { // beats 25.70%
        if (s.length() == 0) {
            return true;
        }
        String filteredStr = getFiltered(s.toLowerCase());
        return isPalindromeStr(filteredStr);
    }
    private String getFiltered(String s) {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (('0' <= s.charAt(i) && s.charAt(i) <= '9') || ('a' <= s.charAt(i) && s.charAt(i) <= 'z')) {
                tmp.append(s.charAt(i));
            }
        }
        return tmp.toString();
    }
    private boolean isPalindromeStr(String s) {
        for (int i = 0; i < (s.length() / 2 ); i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindromeV0(String word) { // not working (TLE) for last test case
        //Strip out non-alphanumeric characters from string
        String cleanWord = word.replaceAll("[^a-zA-Z0-9]","");
        //Check for palindrome quality recursively
        return checkPalindrome(cleanWord.toLowerCase());
    }
    private boolean checkPalindrome(String word) {
        if (word.length() < 2) {
            return true;
        }
        char first  = word.charAt(0);
        char last   = word.charAt(word.length() - 1);
        if (first != last ) {
            return false;
        } else {
            return checkPalindrome(word.substring(1, word.length() - 1));
        }
    }
}

// "" => true
// "A man, a plan, a canal: Panama" is a palindrome.
// "race a car" is not a palindrome.
