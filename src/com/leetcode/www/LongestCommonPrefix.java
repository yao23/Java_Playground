package com.leetcode.www;

public class LongestCommonPrefix { // LC 14
    public String longestCommonPrefix(String[] strs) { // beast 88.28%
        if (strs == null || strs.length == 0) {
            return "";
        }
        String pre = strs[0];
        int i = 1;
        while (i < strs.length) {
            while (strs[i].indexOf(pre) != 0) {
                pre = pre.substring(0, pre.length() - 1); // shorten the word
            }
            i++;
        }
        return pre;
    }

    public String longestCommonPrefixV0(String[] strs) { // beats 26.72%
        String commonPrefix = "";
        if (strs.length == 0) {
            return commonPrefix;
        }
        int prefixIndex = 0;

        while (true) {
            char c = ' ';
            int i;
            for (i = 0; i < strs.length; i++ ) {
                if (prefixIndex == strs[i].length()) {
                    break;
                }
                if (i == 0) {
                    c = strs[i].charAt(prefixIndex);
                }
                if (c != strs[i].charAt(prefixIndex)) {
                    break;
                }
            }
            if (i != strs.length) {
                break;
            }
            commonPrefix += c;
            prefixIndex++;
        }

        return commonPrefix;
    }
}
