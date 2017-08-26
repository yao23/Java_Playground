package com.leetcode.www;

public class ReverseInteger { // LC 7
    public int reverse(int x) { // beats 41.73%
        int result = 0;

        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result) {
                return 0;
            }
            result = newResult;
            x = x / 10;
        }

        return result;
    }

    public int reverseV0(int x) { // beats 47.45%
        long rev= 0;
        while (x != 0) {
            rev= rev * 10 + x % 10;
            x = x / 10;
            if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return (int)rev;
    }

    public int reverseV1(int x) { // beats 28.82%
        long result =0;
        while (x != 0) {
            result = (result * 10) + (x % 10);
            if (result > Integer.MAX_VALUE) {
                return 0;
            }
            if (result < Integer.MIN_VALUE) {
                return 0;
            }
            x = x / 10;
        }
        return (int)result;
    }
}

// Example1: x = 123, return 321
// Example2: x = -123, return -321