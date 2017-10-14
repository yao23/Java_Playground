package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculatorII { // LC 227
    public int calculate(String s) { // beats 97.05%
        if (s.length() == 0) {
            return 0;
        }
        char operator = '+';
        char partOperator = '*';
        int sum = 0;
        int partVal = 1;
        int curVal = 0;

        for (int i = 0; i <= s.length(); i++) {
            char c = (i == s.length()) ? 0 : s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (c >= '0' && c <= '9') {
                curVal = curVal * 10 + (c - '0');
                continue;
            }
            if (c == 0 || "+-*/".indexOf(c) >= 0) {
                partVal = (partOperator == '*') ? partVal * curVal : partVal / curVal;
                curVal = 0;
                if (c == '+' || c == '-' || c == 0) {
                    sum += (operator == '+') ? partVal : -partVal;
                    partVal = 1;
                    operator = c;
                    partOperator = '*';
                } else {
                    partOperator = c;
                }
            }
        }
        return sum;
    }

    public int calculateV0(String s) { // beats 53.99%
        int len;
        if (s == null || (len = s.length()) == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if ((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) || i == len-1) {
                if (sign == '-') {
                    stack.push(-num);
                }
                if (sign=='+') {
                    stack.push(num);
                }
                if (sign=='*') {
                    stack.push(stack.pop() * num);
                }
                if (sign=='/') {
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }

        int re = 0;
        for (int i : stack) {
            re += i;
        }
        return re;
    }
}

// "3+2*2" = 7
// " 3/2 " = 1
// " 3+5 / 2 " = 5
