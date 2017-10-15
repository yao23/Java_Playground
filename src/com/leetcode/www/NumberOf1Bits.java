package com.leetcode.www;

public class NumberOf1Bits { // LC 191
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) { // beats 77.67%
        int ones = 0;
        while (n != 0) {
            ones = ones + (n & 1);
            n = n >>> 1;
        }
        return ones;
    }
}
