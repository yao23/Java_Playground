package com.leetcode.www;

import java.util.List;

public class ValidWordSquare { // LC 422
    public boolean validWordSquare(List<String> words) {
        int len = words.size();
        if (len == 0) {
            return true;
        } else {
            String firstWord = words.get(0);
            if (len == firstWord.length()) {
                for (int curIdx = 0; curIdx < len; curIdx++) {
                    String str = words.get(curIdx);
                    if (!isCharSame(words, curIdx, str, len)) {
                        return false;
                    }
                }

                // last word length is larger than words length
                // test case 7
                if (words.get(len - 1).length() > len) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    private boolean isCharSame(List<String> words, int curIdx, String str, int len) {
        for (int i = curIdx; i < len; i++) {
            String tmp = words.get(i);
            int strLen = str.length(), tmpLen = tmp.length();
            if (i < strLen && curIdx < tmpLen) {
                char horizontalChar =  str.charAt(i);
                char verticalChar = tmp.charAt(curIdx);
                if (horizontalChar != verticalChar) {
                    return false;
                }
            } else if (i >= strLen && curIdx >= tmpLen) {
                // middle word is shorter than words below
                // test case 8
                int vIdx = i;
                while (vIdx < len) {
                    tmp = words.get(vIdx);
                    if (tmp.length() > curIdx) {
                        return false;
                    }
                    vIdx++;
                }
                return true;
            } else {
                return false;
            }
        }

        return true;
    }
}

// [
//  "abcd",
//  "bnrt",
//  "crmy",
//  "dtye"
// ]
// true

// [
//  "abcd",
//  "bnrt",
//  "crm",
//  "dt"
// ]
// true

// [
//  "ball",
//  "area",
//  "read",
//  "lady"
// ]
// false

// [] => true
// ["a","b","c"] => false
// ["abc"] => false
// ["abc","bde","cefg"] => false
// ["abcd","befd","cf","dda"] => false

// beats 97.94%