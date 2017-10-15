package com.leetcode.www;

public class PowerOfTwo { // LC 231

    // the range of an integer = -2147483648 (-2^31) ~ 2147483647 (2^31-1),
    // the max possible power of two = 2^30 = 1073741824
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (1073741824 % n == 0);
    }

    // power of 2 has only one bit 1
    public boolean isPowerOfTwoV1(int n) { // beats 11.81%
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     *  a power of two in binary form has and only has one "1".
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwoV0(int n) { // beats 11.81%
        return n > 0 && Integer.bitCount(n) == 1;
    }
}
