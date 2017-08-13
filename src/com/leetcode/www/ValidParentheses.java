package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParentheses { // LC 20
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        // s length is odd
        if ((s.length() & 1) == 1) {
            return false;
        }
        char[] str = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : str) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.offerLast(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    char left = stack.pollLast();
                    if (ch == ')' && left == '(' ||
                            ch == ']' && left == '[' ||
                            ch == '}' && left == '{') {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
        }

        return stack.isEmpty();
    }
}

// "[" => false

// beats 33.53%
