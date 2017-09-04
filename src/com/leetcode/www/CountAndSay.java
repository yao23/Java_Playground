package com.leetcode.www;

public class CountAndSay { // LC 38
    public String countAndSay(int n) { // beats 14.84%
        String result = "1";
        for (int i = 1; i < n; i++) {
            result = countAndCreate(result);
        }
        return result;
    }
    private String countAndCreate(String result) {
        String tmp = "";
        int last = 0, len = result.length();
        for (int i = 0; i < len; i++) {
            if (result.charAt(i) != result.charAt(last)) {
                tmp = tmp + (i - last) + result.charAt(last);
                last = i;
            }
        }
        if( last < len ) {
            tmp = tmp + (len - last) + result.charAt(last);
        }
        return tmp;
    }
}
