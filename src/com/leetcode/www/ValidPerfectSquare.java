package com.leetcode.www;

public class ValidPerfectSquare { // LC 367
    // A square number is 1+3+5+7+...
    public boolean isPerfectSquare(int num) { // beats 20.55%
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
