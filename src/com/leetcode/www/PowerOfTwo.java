package com.leetcode.www;

public class PowerOfTwo { // LC 231
    /**
     *  a power of two in binary form has and only has one "1".
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) { // beats 11.81%
        return n>0 && Integer.bitCount(n) == 1;
    }
}
