package com.leetcode.www;

import java.util.*;

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

    public String simplifyPathV0(String path) { // beats 28.84%
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if (!skip.contains(dir)) {
                stack.push(dir);
            }
        }
        String res = "";
        for (String dir : stack) {
            res = "/" + dir + res;
        }
        return res.isEmpty() ? "/" : res;
    }
}

// path = "/home/", => "/home"
// path = "/a/./b/../../c/", => "/c"
// "/abc/..." => "/abc/..."