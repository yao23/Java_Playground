package com.leetcode.www;

public class ExcelSheetColumnTitle { // LC 168
    // iteration
    public String convertToTitle(int n) { // beats 5.34%
        StringBuilder result = new StringBuilder();

        while (n > 0) {
            n--;
            result.insert(0, (char)('A' + n % 26));
            n /= 26;
        }

        return result.toString();
    }

    // recursion
    public String convertToTitleV0(int n) { // beats 5.34%
        return n == 0 ? "" : convertToTitle(--n / 26) + (char)('A' + (n % 26));
    }
}

// 1 -> A
// 2 -> B
// 3 -> C
// ...
// 26 -> Z
// 27 -> AA
// 28 -> AB
