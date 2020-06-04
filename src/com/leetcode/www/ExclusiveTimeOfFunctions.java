package com.leetcode.www;

import java.util.List;
import java.util.Stack;

public class ExclusiveTimeOfFunctions { // LC 636 (Facebook)
    /**
     * Runtime: 14 ms, faster than 95.85% of Java online submissions for Exclusive Time of Functions.
     * Memory Usage: 37.7 MB, less than 98.70% of Java online submissions for Exclusive Time of Functions.
     *
     * Stack
     *
     * https://leetcode.com/problems/exclusive-time-of-functions/discuss/293215/Simple-Java-solution-using-Stack
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

    /**
     * Runtime: 14 ms, faster than 95.85% of Java online submissions for Exclusive Time of Functions.
     * Memory Usage: 37.2 MB, less than 99.40% of Java online submissions for Exclusive Time of Functions.
     *
     * https://leetcode.com/problems/exclusive-time-of-functions/discuss/275895/Stack
     *
     * @param n
     * @param logs
     * @return
     */
    public int[] exclusiveTimeV0(int n, List<String> logs) {
        // Init stack of function ids
        Stack<Integer> stack = new Stack<>();
        int prevTime = 0;
        int[] result = new int[n];

        for (String log : logs) {
            String[] splits = log.split(":");
            int curId = Integer.parseInt(splits[0]);
            int curTime = Integer.parseInt(splits[2]);
            // Push function id to stack

            if (stack.isEmpty()) {
                stack.push(curId);
                continue;
            }

            // If start, curTime - prevTime inc stack.peek() function time
            int totalTime = 0;
            if (splits[1].equals("start")) {
                totalTime = curTime - prevTime;
                prevTime = curTime;

                result[stack.peek()] += totalTime;
                stack.push(curId);
            } else {
                // Else, curTime - prevTime + 1 inc stack.pop() function time
                totalTime = curTime - prevTime + 1;
                // Increase by one because only inner-most function is longer by one, others should be with totalTime = curTime - prevTime;
                prevTime = curTime + 1;

                result[stack.pop()] += totalTime;
            }
        }

        return result;
    }
}
