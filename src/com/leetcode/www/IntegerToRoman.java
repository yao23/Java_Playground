package com.leetcode.www;

public class IntegerToRoman { // LC 12
    public String intToRoman(int num) { // beats 42.96%
        StringBuilder result = new StringBuilder();
        String[][] InttoRoman = {
                {"", "M", "MM", "MMM", "", "", "", "", "", ""}, // 0 - 3000
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}, // 0 - 900
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}, // 0 - 90
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}  // 0 - 9
        };
        int digit, i = 0, scale = 1000, num_tmp = num;

        while (num_tmp > 0) {
            digit = num_tmp / scale;
            result.append(InttoRoman[i][digit]);
            i++;
            num_tmp %= scale;
            scale /= 10;
        }

        return result.toString();
    }

    public String intToRomanV0(int num) { // beats 28.04%
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }
}
