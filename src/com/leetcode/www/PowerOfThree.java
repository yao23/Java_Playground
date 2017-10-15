package com.leetcode.www;

public class PowerOfThree { // LC 326
    public boolean isPowerOfThree(int n) { // beats 20.52%
        // 1162261467 is 3^19,  3^20 is bigger than int
        return (n > 0 &&  1162261467 % n == 0);
    }
}
