package com.leetcode.www;

public class Sqrt { // LC 69
    public int mySqrt(int x) { // beats 14.13%
        if (x == 0 || x == 1) {
            return x;
        }
        int start = 0, end = x;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid == x / mid) {
                return mid;
            } else if( mid < x / mid ) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return (start + end) / 2;
    }
}

// 0 => 0