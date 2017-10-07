package com.leetcode.www;

public class ValidPerfectSquare { // LC 367
    // binary search
    public boolean isPerfectSquare(int num) { // beats 37.97%, O(logn)
        int low = 1, high = num;
        while (low <= high) {
            long mid = (low + high) >>> 1; // use long to avoid mid*mid from overflow
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                low = (int) mid + 1;
            } else {
                high = (int) mid - 1;
            }
        }
        return false;
    }

    // A square number is 1+3+5+7+...
    public boolean isPerfectSquareV0(int num) { // beats 20.55%,  O(sqrt(n))
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }
}

// Input: 16, Returns: True
// Input: 14, Returns: False
