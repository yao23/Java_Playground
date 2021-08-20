package com.leetcode.www;

/**
 * Created by liyao on 6/10/17.
 */
public class ContainerWithMostWater { // LC 11
    /**
     * optimal solution
     *
     * [1,1] => 1
     * [1,2,4,3] => 4
     * [3,2,1,3] => 9
    *
     * beats 33.22%
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int result = 0;
        int left = 0, right = height.length - 1;

        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            int curArea = (right - left) * minHeight;
            if (curArea > result) {
                result = curArea;
            }

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return result;
    }

    /**
     * Time Limit Exceeded
     *
     * @param height
     * @return
     */
    public int maxAreaV0(int[] height) {
        int result = 0;
        int len = height.length;

        for (int i = 0; i < len - 1; i++) {
            for (int j = i+1; j < len; j++) {
                int minHeight = Math.min(height[i], height[j]);
                int curArea = (j - i) * minHeight;
                if (curArea > result) {
                    result = curArea;
                }
            }
        }

        return result;
    }

}