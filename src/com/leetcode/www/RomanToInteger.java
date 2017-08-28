package com.leetcode.www;

public class RomanToInteger { // LC 13
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i > 0) && chartoInt(s.charAt(i - 1)) < chartoInt(s.charAt(i))) {
                result += (chartoInt(s.charAt(i)) - chartoInt(s.charAt(i - 1)) * 2);
            } else {
                result += chartoInt(s.charAt(i));
            }
        }
        return result;
    }
    private int chartoInt(char c) {
        switch(c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public int romanToIntV0(String s) { // beats 34.27%
        int nums[] = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'M':
                    nums[i] = 1000;
                    break;
                case 'D':
                    nums[i] = 500;
                    break;
                case 'C':
                    nums[i] = 100;
                    break;
                case 'L':
                    nums[i] = 50;
                    break;
                case 'X' :
                    nums[i] = 10;
                    break;
                case 'V':
                    nums[i] = 5;
                    break;
                case 'I':
                    nums[i] = 1;
                    break;
            }
        }
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                sum -= nums[i];
            } else {
                sum += nums[i];
            }
        }
        return sum + nums[nums.length - 1];
    }
}
