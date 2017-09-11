package com.leetcode.www;

public class ValidPalindrome {
    /**
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if( s.length() == 0 )	return true;
        String FilteredStr = GetFiltered(s.toLowerCase());
        return IsPalindrome(FilteredStr);
    }
    private String GetFiltered(String s) {
        StringBuilder tmp = new StringBuilder();
        for( int i = 0; i < s.length(); i++ ) {
            if( ('0' <= s.charAt(i) && s.charAt(i) <= '9') ||
                    ('a' <= s.charAt(i) && s.charAt(i) <= 'z') ) {
                tmp.append(s.charAt(i));
            }
        }
        return tmp.toString();
    }
    private boolean IsPalindrome(String s) {
        for( int i = 0; i < (s.length() / 2 ); i++ ) {
            if( s.charAt(i) != s.charAt(s.length() - 1 - i) )
                return false;
        }
        return true;
    }
}

// "" => true
// "A man, a plan, a canal: Panama" is a palindrome.
// "race a car" is not a palindrome.
