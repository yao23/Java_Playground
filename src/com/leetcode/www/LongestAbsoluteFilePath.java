package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class LongestAbsoluteFilePath { // LC 388
    public int lengthLongestPath(String input) { // beats 85.42%
        String[] paths = input.split("\n");
        int[] stack = new int[paths.length + 1];
        int maxLen = 0;
        for (String s : paths) {
            int lev = s.lastIndexOf("\t") + 1, curLen = stack[lev + 1] = stack[lev] + s.length() - lev + 1;
            if (s.contains(".")) {
                maxLen = Math.max(maxLen, curLen - 1);
            }
        }
        return maxLen;
    }

    public int lengthLongestPathV1(String input) { // beats 42.41%
        String[] strs = input.split("\n");
        int max = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(-1, 0);
        for (int i = 0; i < strs.length; i++){
            int level =  strs[i].lastIndexOf('\t')  + 1;
            int length = map.get(level - 1) + strs[i].length() - level + (level > 0 ? 1 : 0);
            if (strs[i].indexOf('.') == -1) {
                map.put(level, length);
            } else {
                max = Math.max(length, max);
            }
        }
        return max;
    }

    public int lengthLongestPathV0(String input) { // beats 26.45%
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // "dummy" length
        int maxLen = 0;
        for (String s:input.split("\n")) {
            int lev = s.lastIndexOf("\t") + 1; // number of "\t"
            while (lev + 1 < stack.size()) {
                stack.pop(); // find parent
            }
            int len = stack.peek() + s.length() - lev + 1; // remove "/t", add"/"
            stack.push(len);
            // check if it is file
            if (s.contains(".")) {
                maxLen = Math.max(maxLen, len - 1);
            }
        }
        return maxLen;
    }
}
