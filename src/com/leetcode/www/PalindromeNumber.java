package com.leetcode.www;

public class PalindromeNumber {
    public boolean isPalindrome(int x) { // beats 59.23%
        if( x < 0 ) return false;
        int ReverseX = ReverseNum(x);
        if( x == ReverseX )
            return true;
        else
            return false;
    }
    private int ReverseNum(int x) {
        boolean negative = x < 0 ? true : false;
        int x_tmp = Math.abs(x), result = 0;
        while( x_tmp > 0 ) {
            result = result * 10 + x_tmp % 10;
            x_tmp /= 10;
        }
        if( negative )
            result *= (-1);
        return result;
    }
    public boolean isPalindrome2(int x) {
        if( x < 0 )	return false;
        int x_tmp = x, TopDivider = 1;
        int TopDigit = 0, LastDigit = 0;
        boolean flag = true;

        while( x_tmp / 10 > 0 ) {
            TopDivider *= 10;
            x_tmp /= 10;
        }

        x_tmp = x;

        while( x_tmp > 0 ) {
            TopDigit = x_tmp / TopDivider;
            LastDigit = x_tmp % 10;
            if( TopDigit != LastDigit ) {
                flag = false;
                break;
            }
            x_tmp = x_tmp % TopDivider / 10;
            TopDivider /= 100;
        }

        return flag;
    }
}
