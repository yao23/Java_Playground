package com.leetcode.www;

public class ReverseWordsInAString {
    public char[] reverse(char[] arr, int i, int j) { // beats 80.56%
        while (i < j) {
            char tmp = arr[i];
            arr[i++] = arr[j];
            arr[j--] = tmp;
        }
        return arr;
    }

    public String reverseWords(String s) {
        // reverse the whole string and convert to char array
        char[] str = reverse(s.toCharArray(), 0, s.length()-1);
        int start = 0, end = 0; // start and end positions of a current word
        for (int i = 0; i < str.length; i++) {
            if (str[i] != ' ') { // if the current char is letter
                str[end++] = str[i]; // just move this letter to the next free pos
            } else if (i > 0 && str[i-1] != ' ') { // if the first space after word
                reverse(str, start, end-1); // reverse the word
                str[end++] = ' '; // and put the space after it
                start = end; // move start position further for the next word
            }
        }
        reverse(str, start, end-1); // reverse the tail word if it's there
        // here's an ugly return just because we need to return Java's String
        // also as there could be spaces at the end of original string
        // we need to consider redundant space we have put there before
        return new String(str, 0, end > 0 && str[end-1] == ' ' ? end-1 : end);
    }

    public String reverseWordsV0(String s) { // not working
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
            // s = "";
            // for (int i = start; i <= end; i++) {
            //     if (strArr[i] != ' ' || strArr[i - 1] != ' ') {
            //         s += strArr[i];
            //     }
            // }
            String str = new String(strArr).substring(start, end + 1);

            // reverse the whole sentence
            // String str = reverse(s.toCharArray(), 0, s.length() - 1);

            strArr = str.toCharArray();
            int left = 0;
            int right = 0;
            String res = "";

            while (right < strArr.length) {
                if (strArr[right] != ' ') {
                    right++;
                } else {
                    String tmp = reverseV0(strArr, left, right - 1);
                    res = assembleStr(tmp, res);
                    right++;
                    left = right;
                }
            }

            // reverse the last word
            String tmp = reverseV0(strArr, left, right - 1);
            res = assembleStr(tmp, res);

            return res;
        }
    }

    private String assembleStr(String tmp, String res) {
        if (res.equals("")) {
            res = tmp;
        } else {
            res = tmp + " " + res;
        }

        return res;
    }

    private String reverseV0(char[] ch, int start, int end) {
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