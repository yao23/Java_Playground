package com.leetcode.www;

import java.util.Stack;

public class DailyTemperatures { // LC 739
    /**
     * Runtime: 40 ms, faster than 75.60% of Java online submissions for Daily Temperatures.
     * Memory Usage: 43.2 MB, less than 84.46% of Java online submissions for Daily Temperatures.
     *
     *     // T = [73, 74, 75, 71, 69, 72, 76, 73] => [1, 1, 4, 2, 1, 1, 0, 0]
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

    /**
     * Runtime: 376 ms, faster than 5.07% of Java online submissions for Daily Temperatures.
     * Memory Usage: 41.7 MB, less than 98.65% of Java online submissions for Daily Temperatures.
     *
     * @param T
     * @return
     */
    public int[] dailyTemperaturesV0(int[] T) {
        int ans[] = new int[T.length];
        int frontIndex = 0, backIndex = 0;
        while (backIndex < T.length && frontIndex < T.length) {
            if (frontIndex == T.length-1) {
                ans[backIndex] = 0;
                backIndex++;
                frontIndex = backIndex;
            } else {
                frontIndex++;
                if (T[backIndex] < T[frontIndex]) {
                    ans[backIndex] = frontIndex - backIndex;
                    backIndex++;
                    frontIndex = backIndex;
                }

            }
        }
        return ans;
    }


}
