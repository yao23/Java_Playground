package com.leetcode.www;

public class DivideTwoIntegers { // LC 29
    /**
     * bit operation left shift as multiplication, right shift as division
     *
     * (0, 1) => 0
     * (1, 0) => Integer.MAX_VALUE
     * (Integer.MIN_VALUE, -1) => Integer.MAX_VALUE
     *
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Divide Two Integers.
     * Memory Usage: 37 MB, less than 6.06% of Java online submissions for Divide Two Integers.
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            } else if (divisor == -1) { // Input:-2147483648, -1, Output:2147483648 Expected:2147483647 (test case 3)
                return Integer.MAX_VALUE;
            }
        }

        long curDividend = Math.abs((long) dividend);
        long curDivisor = Math.abs((long) divisor);
        int res = 0;

        while (curDividend >= curDivisor) {
            long cur = curDivisor; // avoid overflow when left shift
            int mul = 1;

            while (curDividend >= (cur << 1)) {
                mul <<= 1;
                cur <<= 1;
            }

            curDividend -= cur;
            res += mul;
        }

        if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) {
            return -res;
        } else {
            return res;
        }
    }
}

