package com.leetcode.www;

public class MultiplyStrings { // 43
    /**
     *
     * @param num1
     * @param num2
     * @return
     *
     * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

     * Note:

     * The length of both num1 and num2 is < 110.
     * Both num1 and num2 contains only digits 0-9.
     * Both num1 and num2 does not contain any leading zero.
     * You must not use any built-in BigInteger library or convert the inputs to integer directly.

     */
    public String multiply(String num1, String num2) { // beats 97.37%
        int len1 = num1.length();
        int len2 = num2.length();
        int[] num = new int[len1 + len2 + 1];
        for (int i = 0; i < len2; i++) {
            int carry = 0;
            int a = num2.charAt(len2 - 1 - i) - '0';
            for (int j = 0; j < len1; j++) {
                int b = num1.charAt(len1 - 1 - j) - '0';
                num[i + j] += (a * b + carry);
                carry = num[i + j] / 10;
                num[i + j] = num[i + j] % 10;
            }
            num[i + len1] = carry;
        }
        int i = num.length - 1;

        // trim zeroes in top digits
        while (i > 0 && num[i] == 0) {
            i--;
        }
        StringBuilder res = new StringBuilder();

        while (i >= 0) {
            res.append(num[i--]);
        }
        return res.toString();
    }
}
