package com.leetcode.www;

import java.util.Arrays;

public class CombinationSumIV { // LC 377
    /**
     * nums = [1, 2, 3], target = 4 => 7
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * / (3, 1)
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) { // recursion
        int[] count = new int[target + 1];
        Arrays.fill(count, -1);
        count[0] = 1;
        return helper(nums, target, count);
    }

    private int helper(int[] nums, int remain, int[] count) {
        if (count[remain] != -1) {
            return count[remain];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (remain >= nums[i]) {
                res += helper(nums, remain - nums[i], count); // go to next level
            }
        }
        count[remain] = res;
        return res;
    }

    public int combinationSum4V1(int[] nums, int target) { // iterative, beats 32.91%
        int[] count = new int[target + 1];
        count[0] = 1;
        for (int i = 1; i < count.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) {
                    count[i] += count[i - nums[j]];
                }
            }
        }

        return count[target];
    }
}










// beats 84.97%


