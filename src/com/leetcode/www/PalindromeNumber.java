package com.leetcode.www;

public class PalindromeNumber { // LC 9
    public boolean isPalindrome(int x) { // beats 59.23%
        if (x < 0) {
            return false;
        }
        int reverseX = reverseNum(x);
        return x == reverseX;
    }

    private int reverseNum(int x) {
        boolean negative = x < 0;
        int x_tmp = Math.abs(x), result = 0;
        while (x_tmp > 0) {
            result = result * 10 + x_tmp % 10;
            x_tmp /= 10;
        }
        if (negative) {
            result *= (-1);
        }
        return result;
    }

    public boolean isPalindromeV0(int x) { // beast 92.10%
        if (x < 0) {
            return false;
        }
        int x_tmp = x, topDivider = 1;
        int topDigit = 0, lastDigit = 0;
        boolean flag = true;

        while (x_tmp / 10 > 0) {
            topDivider *= 10;
            x_tmp /= 10;
        }

        x_tmp = x;

        while (x_tmp > 0) {
            topDigit = x_tmp / topDivider;
            lastDigit = x_tmp % 10;
            if (topDigit != lastDigit) {
                flag = false;
                break;
            }
            x_tmp = x_tmp % topDivider / 10;
            topDivider /= 100;
        }

        return flag;
    }
}
