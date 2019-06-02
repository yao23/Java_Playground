package com.leetcode.www;

public class Pow { // LC 50
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Pow(x, n).
     * Memory Usage: 32.7 MB, less than 52.89% of Java online submissions for Pow(x, n).
     *
     * @param x
     * @param n
     * @return
     */
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

    public double myPowV1(double x, int n) { // beats 40.51%
        if (n < 0) {
            return (1 / x) * myPowV1(1 / x, -(n + 1));
        }
        if (n == 0) {
            return 1;
        }
        if (n == 2) {
            return x * x;
        }
        if (n % 2 == 0) {
            return myPowV1(myPow(x, n / 2), 2);
        } else {
            return x * myPowV1(myPow(x, n / 2), 2);
        }
    }

    public double myPowV0(double x, int n) { // beast 78.25%
        if (n == 0) {
            return 1;
        }
        if (x == 1) {
            return 1;
        }
        if (x == -1) {
            if (n % 2 == 0) {
                return 1;
            } else {
                return -1;
            }
        }
        if (n == Integer.MIN_VALUE) {
            return 0;
        }
        if (n < 0) {
            n = -n;
            x = 1/x;
        }
        double ret = 1.0;
        while (n > 0) {
            if ((n & 1) != 0) {
                ret *= x;
            }
            x = x * x;
            n = n >> 1;
        }
        return ret;
    }
}

// 8.88023, 3 => 700.28148
// 2.00000, -2147483648 => 0.00000