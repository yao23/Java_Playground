package com.leetcode.www;

public class PowerOfThree { // LC 326
    // Find the maximum integer that is a power of 3 and check if it is a multiple of the given input.
    public boolean isPowerOfThree(int n) { // beats 13.45%
        int maxPowerOfThree = (int)Math.pow(3, (int)(Math.log(0x7fffffff) / Math.log(3)));
        return n > 0 && maxPowerOfThree % n == 0;
    }

    // Integer Limitations
    public boolean isPowerOfThreeV0(int n) { // beats 20.52%
        // 1162261467 is 3^19,  3^20 is bigger than int
        return (n > 0 &&  1162261467 % n == 0);
    }
}
