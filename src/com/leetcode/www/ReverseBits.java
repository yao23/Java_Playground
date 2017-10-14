package com.leetcode.www;

public class ReverseBits { // LC 190
    // you need treat n as an unsigned value
    public int reverseBits(int n) { // beats 27.74%
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += n & 1;
            n >>>= 1;   // CATCH: must do unsigned shift
            if (i < 31) { // CATCH: for last digit, don't shift!
                result <<= 1;
            }
        }
        return result;
    }
}
