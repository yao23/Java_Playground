package com.leetcode.www;

public class MinimumUniqueWordAbbreviation {
    public String minAbbreviation(String target, String[] dictionary) { // beats 98.79%
        char[] c = target.toCharArray();
        char[] tmp = new char[c.length];
        // traverse length from min to max
        for (int l = 1; l <= target.length(); l++) {
            String abbr = minAbbreviation(c, 0, tmp, 0, dictionary, l);
            if (abbr != null) {
                return abbr;
            }
        }
        return null;
    }

    private String minAbbreviation(char[] c, int p, char[] tmp, int t, String[] dictionary, int l){
        if (l == 0) {// all length has been used up
            if (p == c.length && !conflict(tmp, t, dictionary, c.length)) {
                return new String(tmp, 0, t);
            } else {
                return null;
            }
        }
        if (t == 0|| tmp[t - 1] > '9') {// can use abbr
            // c.length - 1 - (end + 1) + 1 >= l - 1 => c.length - end >= l
            // we don't need to check length of abbr = 1,
            // it will have the same length with the one that does not use abbr and has less elements to distinguish a word
            for (int end = p + 1; end <= c.length - l; end++) {
                int s = end - p + 1;
                if (s >= 10) {
                    tmp[t] = (char)(s / 10 + '0');
                    tmp[t + 1] = (char)(s % 10 + '0');
                    String r = minAbbreviation(c, end + 1, tmp, t + 2, dictionary, l - 1);
                    if (r != null) {
                        return r;
                    }
                } else {
                    tmp[t] = (char)(s + '0');
                    String r = minAbbreviation(c, end + 1, tmp, t + 1, dictionary, l - 1);
                    if (r != null) {
                        return r;
                    }
                }
            }
        }
        // use original character
        tmp[t] = c[p];
        return minAbbreviation(c, p + 1, tmp, t + 1, dictionary, l - 1);
    }

    private boolean conflict(char[] abbr, int t, String[] dictionary, int l) {
        char[] pattern = new char[abbr.length];
        int p = 0; // pointer for pattern
        int count = 0;
        for (int i = 0; i < t; i++) {
            char c = abbr[i];
            if (c <= '9') {
                count = count * 10 + c - '0';
            } else{
                if (count != 0) {
                    // store count to pattern. (note that count must be less than 22)
                    pattern[p++] = (char)count;
                    count = 0;
                }
                pattern[p++] = c;
            }
        }
        //if (count != 0) pattern[p++] = (char)count; tailing pattern doesn't need to check
        for (String s : dictionary) {
            if (s.length() != l) {
                continue;
            }
            int j = 0;
            boolean match = true;
            for (int i = 0; i < p; i++) {
                if (pattern[i] < 22) {
                    j += pattern[i]; // pass count characters
                } else if (s.charAt(j) != pattern[i]) {
                    match = false;
                    break;
                } else {
                    j++; // match one character
                }
            }
            if (match) {
                return true;
            }
        }
        return false;
    }
}
