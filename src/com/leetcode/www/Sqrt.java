package com.leetcode.www;

public class Sqrt { // LC 69
    public int mySqrtV0(int x) { // beats 14.13%
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

    public int mySqrtV1(int x) { // beats 64.54%
        if (x == 0) {
            return 0;
        }
        int h = 0;
        while ((long)(1 << h)*(long)(1 << h) <= x) { // firstly, find the most significant bit
            h++;
        }
        h--;
        int b = h-1;
        int res = (1 << h);
        while (b >= 0) { // find the remaining bits
            if ((long)(res | (1 << b))*(long)(res |(1 << b)) <= x) {
                res |= (1 << b);
            }
            b--;
        }
        return res;
    }

    public int mySqrt(int x) { // beats 64.54%
        if (x < 4) {
            return x == 0 ? 0 : 1;
        }
        int res = 2 * mySqrt(x / 4);
        if ((res + 1) * (res + 1) <= x && (res + 1) * (res + 1) >= 0) {
            return res + 1;
        }
        return res;
    }
}

// 0 => 0