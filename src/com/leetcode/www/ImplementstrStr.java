package com.leetcode.www;

/**
 * Created by liyao on 7/10/17.
 */
public class ImplementstrStr { // LC 28
    public int strStr(String haystack, String needle) {
        int firstIdx = -1;
        if (haystack.length() < needle.length()) {
            return firstIdx;
        }
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            boolean isSame = true;
            for (int j = 0; j < needle.length(); j++) {
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    isSame = false;
                    break;
                }
            }
            if (isSame) {
                firstIdx = i;
                return firstIdx;
            }
        }

        return firstIdx;
    }

    // "","" => 0
    // "1","" => 0
    // "","1" => -1
    // "abba","ba" => 2
    // "abba","b" => 1
    // "abba","bc" => -1

    // beats 41.72%
}
