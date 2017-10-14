package com.leetcode.www;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber { // LC 202
    /**
     *  The idea is to use one hash set to record sum of every digit square of every number occurred. Once the current
     *  sum cannot be added to set, return false; once the current sum equals 1, return true;
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        Set<Integer> inLoop = new HashSet<>();
        int squareSum, remain;
        while (inLoop.add(n)) {
            squareSum = 0;
            while (n > 0) {
                remain = n % 10;
                squareSum += remain * remain;
                n /= 10;
            }
            if (squareSum == 1) {
                return true;
            } else {
                n = squareSum;
            }

        }
        return false;
    }
}
