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
}

// Example1: x = 123, return 321
// Example2: x = -123, return -321