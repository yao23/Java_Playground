package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram { // LC 84
    /**
     * Runtime: 7 ms, faster than 83.46% of Java online submissions for Largest Rectangle in Histogram.
     * Memory Usage: 41.3 MB, less than 63.64% of Java online submissions for Largest Rectangle in Histogram.
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) { // beats 79.53%
        int max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < heights.length; i++) {
            if (stack.isEmpty() || heights[i] > heights[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                    int rightIdx = i - 1;
                    max = updateMaxArea(stack, heights, rightIdx, max);
                }

                stack.push(i);
            }
        }

        int rightIdx = heights.length - 1;
        while (!stack.isEmpty()) {
            max = updateMaxArea(stack, heights, rightIdx, max);
        }
        return max;
    }

    private int updateMaxArea(Deque<Integer> stack, int[] heights, int rightIdx, int max) {
        int topIdx = stack.pop();
        int leftIdx = stack.isEmpty() ? 0 : stack.peek() + 1;
        int area = calculateArea(heights[topIdx], leftIdx, rightIdx);
        if (area > max) {
            max = area;
        }

        return max;
    }

    private int calculateArea(int height, int left, int right) {
        return height * (right - left + 1);
    }

    public int largestRectangleAreaV0(int[] heights) { // TLE for test case 2, too many duplicate calculations
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int area = getArea(heights, i);
            if (area > res) {
                res = area;
            }
        }
        return res;
    }

    private int getArea(int[] heights, int cur) {
        int leftIdx = findLeftIdx(heights, cur);
        int rightIdx = findRightIdx(heights, cur);
        return heights[cur] * (rightIdx - leftIdx + 1);
    }

    private int findLeftIdx(int[] heights, int cur) {
        int idx = cur - 1;
        while (idx >= 0 && heights[cur] <= heights[idx]) {
            idx--;
        }
        return idx + 1;
    }

    private int findRightIdx(int[] heights, int cur) {
        int idx = cur + 1;
        int len = heights.length;
        while (idx < len && heights[cur] <= heights[idx]) {
            idx++;
        }
        return idx - 1;
    }

    // [2,1,5,6,2,3] => 10 (5,6 => 5 + 5)
    // [111..1111] (n = 3000) => 3000

    // beats 79.53%
}
