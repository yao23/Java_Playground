package com.leetcode.www;

public class Pow { // LC 50
    public double myPow(double x, int n) { // beats 28.57%
        if (n >= 0) {
            return power(x, n);
        } else {
            return 1.0/power(x, -n);
        }
    }

    private double power(double x, int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        } else {
            double half = myPow(x, n >>> 1);
            double res = 1;
            if ((n & 0x1) == 0) { // even, pay attention to the (n & 0x1)
                res = (half * half);
            } else { // odd
                res = (half * half * x);
            }
            return res;
        }
    }
}
