package com.leetcode.www;

public class ExcelSheetColumnTitle { // LC 168
    public String convertToTitle(int n) { // beats 5.34%
        return n == 0 ? "" : convertToTitle(--n / 26) + (char)('A' + (n % 26));
    }
}

// 1 => "A"
