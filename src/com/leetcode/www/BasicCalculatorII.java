package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculatorII { // LC 227
    public int calculate(String s) { // beats 53.99%
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
