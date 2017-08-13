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

        int start = 0;
        int end = s.length() - 1;
        char[] strArr = s.toCharArray();
        // trim start of string
        while (start <= end && strArr[start] == ' ') {
            start++;
        }

        // trim end of string
        while (end >= 0 && strArr[end] == ' ') {
            end--;
        }

        if (start > end) {
            return "";
        } else if (start == end) {
            return "" + strArr[start];
        } else {
            s = "";
            for (int i = start; i <= end; i++) {
                if (strArr[i] != ' ' || strArr[i - 1] != ' ') {
                    s += strArr[i];
                }
            }
            // s = new String(strArr).substring(start, end + 1);
            // reverse the whole sentence
            String str = reverse(s.toCharArray(), 0, s.length() - 1);

            strArr = str.toCharArray();
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

// "b    a" => "a b"