package com.leetcode.www;

import java.util.Stack;

public class DailyTemperatures { // LC 739
    /**
     * Runtime: 40 ms, faster than 75.60% of Java online submissions for Daily Temperatures.
     * Memory Usage: 43.2 MB, less than 84.46% of Java online submissions for Daily Temperatures.
     *
     * https://leetcode.com/problems/daily-temperatures/discuss/293703/O(N)-Java-momentonos-increasing-stack-solution
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int index = stack.pop();
                res[index] = i - index;
            }
            stack.push(i);
        }
        return res;
    }

    // T = [73, 74, 75, 71, 69, 72, 76, 73] => [1, 1, 4, 2, 1, 1, 0, 0]
}
