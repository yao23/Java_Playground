package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class SimplifyPath { // LC 71
    public String simplifyPath(String path) { // beats 60.79%
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
        Iterator it = stack.descendingIterator();
        while(it.hasNext()) { // do something with it.next()
            String s = it.next().toString();
            res.append("/" + s);
        }
        if (res.length() == 0) {
            res.append("/");
        }
        return res.toString();
    }
}

// path = "/home/", => "/home"
// path = "/a/./b/../../c/", => "/c"
// "/abc/..." => "/abc/..."