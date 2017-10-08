package com.leetcode.www;

public class SumOfSquareNumbers { // LC 633
    // sqrt function
    public boolean judgeSquareSum(int c) { // beats 36.68%
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }
}
