package com.leetcode.www;

public class ReverseWordsInAString {
    public String reverseWords(String s) {
        if (s == null || s.length() < 2) {
            if (s.equals(" ")) {
                return "";
            } else {
                return s;
            }
        }

        // reverse the whole sentence
        String str = reverse(s.toCharArray(), 0, s.length() - 1);

        char[] strArr = str.toCharArray();
        int left = 0;
        int right = 0;

        while (right < strArr.length) {
            if (strArr[right] != ' ') {
                right++;
            } else {
                reverse(strArr, left, right - 1);
                right++;
                left = right;
            }
        }

        // reverse the last word
        reverse(strArr, left, right - 1);

        if (strArr[0] == ' ') {
            int headZeroIdx = 0;
            while (headZeroIdx < strArr.length && strArr[headZeroIdx] == ' ') {
                headZeroIdx++;
            }

            if (headZeroIdx == strArr.length) {
                return "";
            } else {
                return new String(strArr).substring(headZeroIdx);
            }
        } else {
            return new String(strArr);
        }
    }

    private String reverse(char[] ch, int start, int end) {
        while (start < end) {
            char tmp = ch[start];
            ch[start] = ch[end];
            ch[end] = tmp;
            start++;
            end--;
        }

        return new String(ch);
    }
}
