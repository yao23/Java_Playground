package com.leetcode.www;

public class NumberOf1Bits { // LC 191
    // you need to treat n as an unsigned value

    /**
     * https://leetcode.com/articles/number-1-bits/
     *
     * bit-wise AND of n and n − 1 flips the least-significant 1-bit in n to 0
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) { // beats 77.67%
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }

    public int hammingWeightV0(int n) { // beats 77.67%
        int ones = 0;
        while (n != 0) {
            ones = ones + (n & 1);
            n = n >>> 1;
        }
        return ones;
    }
}
