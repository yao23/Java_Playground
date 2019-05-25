package com.leetcode.www;

import java.util.List;
import java.util.Stack;

public class ExclusiveTimeOfFunctions { // LC 636
    /**
     * Runtime: 14 ms, faster than 95.85% of Java online submissions for Exclusive Time of Functions.
     * Memory Usage: 37.7 MB, less than 98.70% of Java online submissions for Exclusive Time of Functions.
     *
     *  https://leetcode.com/problems/exclusive-time-of-functions/discuss/293215/Simple-Java-solution-using-Stack
     *
     * @param n
     * @param logs
     * @return
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int currentTime = 0;
        while (i < logs.size()) {
            String[] parts = logs.get(i).split(":");
            int id = Integer.parseInt(parts[0]);
            int time = Integer.parseInt(parts[2]);
            if (parts[1].equalsIgnoreCase("start")) {
                if (!stack.isEmpty()) {
                    int baseId = stack.peek();
                    result[baseId] += time - currentTime - 1;
                }
                stack.push(id);
            } else {
                int currentId = stack.pop();
                result[currentId] += time - currentTime + 1;
            }
            currentTime = time;
            i++;
        }

        return result;
    }
}
