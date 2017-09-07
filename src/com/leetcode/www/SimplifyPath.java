package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {
    public String simplifyPath(String path) {
        String[] paths = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (String s : paths) {
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (s.length() > 0 && !s.equals(".")) {
                stack.push(s);
            }
        }
        StringBuilder res = new StringBuilder();
        for (String s : stack) {
            res.append("/" + s);
        }
        if (res.length() == 0) {
            res.append("/");
        }
        return res.toString();
    }
}
