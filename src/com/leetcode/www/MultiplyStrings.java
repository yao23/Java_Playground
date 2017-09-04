package com.leetcode.www;

public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int[] num = new int[len1 + len2 + 1];
        for( int i = 0; i < len2; i++ ) {
            int carry = 0;
            int a = num2.charAt(len2 - 1 - i) - '0';
            for( int j = 0; j < len1; j++ ) {
                int b = num1.charAt(len1 - 1 - j) - '0';
                num[i + j] += (a * b + carry);
                carry = num[i + j] / 10;
                num[i + j] = num[i + j] % 10;
            }
            num[i + len1] = carry;
        }
        int i = num.length - 1;

        while( i > 0 && num[i] == 0 ) // trim zeroes in top digits
            i--;

        StringBuilder res = new StringBuilder();

        while( i>= 0 )
            res.append(num[i--]);

        return res.toString();
    }
}
