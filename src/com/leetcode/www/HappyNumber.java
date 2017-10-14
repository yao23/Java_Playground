package com.leetcode.www;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber { // LC 202
    public boolean isHappy(int n) { // beats 96.12%
        int x = n;
        int y = n;
        while (x > 1) {
            x = cal(x);
            if (x == 1) {
                return true;
            }
            y = cal(cal(y));
            if (y == 1) {
                return true;
            }

            if (x == y) {
                return false;
            }
        }
        return true;
    }
    private int cal(int n){
        int x = n;
        int s = 0;
        while (x > 0) {
            s = s + (x % 10) * (x % 10);
            x = x/10;
        }
        return s ;
    }

    /**
     *  The idea is to use one hash set to record sum of every digit square of every number occurred. Once the current
     *  sum cannot be added to set, return false; once the current sum equals 1, return true;
     * @param n
     * @return
     */
    public boolean isHappyV0(int n) { // beats 18.79%
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
