package com.leetcode.www;

public class ZigZagConversion { // LC 6
    public String convert(String s, int nRows) { // 97.48%
        if (s == null || s.length() == 0 || nRows <= 1) {
            return s;
        } else {
            return GenerateStr(s, nRows);
        }
    }
    private String GenerateStr(String s, int nRows) {
        int len = s.length();
        StringBuilder stringBuilder = new StringBuilder();
        int diff = 2 * nRows - 2; // ignore 2 elements in top and bottom row
        for (int i = 0; i < nRows && i < len; i++ ) {
            if (i == 0 || i == nRows - 1) {
                stringBuilder.append(s.charAt(i));
                int index = i;
                while (index + diff < len) {
                    stringBuilder.append(s.charAt(index + diff));
                    index = index + diff;
                }
            } else {
                stringBuilder.append(s.charAt(i));
                int index = i;
                while (2 * nRows - 2 * i - 2 + index < len || index + diff < len) {
                    if (2 * nRows - 2 * i - 2 + index < len) { // exact distance
                        stringBuilder.append(s.charAt(2 * nRows - 2 * i - 2 + index));
                    }
                    if (index + diff < len) {
                        stringBuilder.append(s.charAt(index + diff));
                    }
                    index += diff;
                }
            }
        }
        return stringBuilder.toString();
    }
}

// "PAYPALISHIRING", 3 => "PAHNAPLSIIGYIR
// P   A   H   N
// A P L S I I G
// Y   I   R