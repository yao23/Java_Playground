package com.leetcode.www;

public class Pow {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        } else {
            double half = myPow(x, n >>> 1);
            double res = 1;
            if ((n & 0x1) == 0) { // even
                res = (half * half);
            } else { // odd
                res = (half * half * x);
            }
            if (n > 0) {
                return res;
            } else {
                return 1.0/res;
            }
        }
    }
}
