package com.leetcode.www;

public class ValidPalindrome { // LC 125
    /**
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     *
     * @param s
     * @return
     */


    /**
     * simply build an array that map all possible char into integer(if not alphanumeric,mark it as zero)
     * this will help to speed up the process a lot.
     *
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Valid Palindrome.
     * Memory Usage: 39.9 MB, less than 22.32% of Java online submissions for Valid Palindrome.
     */
    private static final char[]charMap = new char[256];
    static{
        for (int i = 0; i < 10; i++) {
            charMap[i + '0'] = (char)(1 + i);  // numeric
        }
        for (int i = 0; i < 26; i++) {
            charMap[i + 'a'] = charMap[i + 'A'] = (char)(11 + i);  //alphabetic, ignore cases
        }
    }
    public boolean isPalindrome(String s) { // beats 98.64%
        char[]pChars = s.toCharArray();
        int start = 0, end = pChars.length - 1;
        char cS, cE;
        while (start < end) {
            cS = charMap[pChars[start]];
            cE = charMap[pChars[end]];
            if (cS != 0 && cE != 0) {
                if (cS != cE) {
                    return false;
                }
                start++;
                end--;
            } else {
                if (cS == 0) {
                    start++;
                }
                if (cE == 0) {
                    end--;
                }
            }
        }
        return true;
    }

    public boolean isPalindromeV2(String s) { // beats 25.70%
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

    public boolean isPalindromeV1(String s) { // beats 56.52%
        if (s.isEmpty()) {
            return true;
        }
        int head = 0, tail = s.length() - 1;
        char cHead, cTail;
        while (head <= tail) {
            cHead = s.charAt(head);
            cTail = s.charAt(tail);
            if (!Character.isLetterOrDigit(cHead)) {
                head++;
            } else if(!Character.isLetterOrDigit(cTail)) {
                tail--;
            } else {
                if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
                    return false;
                }
                head++;
                tail--;
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
