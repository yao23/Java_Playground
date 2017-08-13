package com.leetcode.www;

public class ReverseWordsInAStringII { // LC 186
    public void reverseWords(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        int left = 0;
        int right = 0;

        while (right < s.length) {
            if (s[right] != ' ') {
                right++;
            } else {
                reverse(s, left, right - 1);
                right++;
                left = right;
            }
        }

        // reverse the last word
        reverse(s, left, right - 1);

        // reverse the whole sentence
        reverse(s, 0, s.length - 1);
    }

    private void reverse(char[] ch, int start, int end) {
        while (start < end) {
            char tmp = ch[start];
            ch[start] = ch[end];
            ch[end] = tmp;
            start++;
            end--;
        }
    }
}

// "the sky is blue" => "blue is sky the"
// "a b" => "b a"


// beats 32.72%