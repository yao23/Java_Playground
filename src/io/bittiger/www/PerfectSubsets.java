package io.bittiger.www;

import java.util.Arrays;

public class PerfectSubsets {
    private int counter = 0;
    public int getPerfectCount(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Arrays.sort(nums);
        helper(nums,   0,   0);
        return   counter;
    }
    private void helper(int[] nums, int pos, int preSum) {
        if (pos == nums.length) {
            return;
        }
        if (preSum == nums[pos]) {
            counter++;
        }
        for (int i = pos; i < nums.length; i++) {
            helper(nums,   i + 1,   preSum + nums[i]);
        }
    }
}

/**
 * “perfect subset” means the largest number in it equals to the sum of other numbers.
 * [2 3 1 6] -> 2 + 3 + 1 = 6   This is perfect
 * [3 1 4] -> 3 + 1 = 4   This is perfect.
 * [1 2] -> 1 != 2   This is not perfect.
 * eg. Intput: [2 7 1 3 5]
 * perfet subsets will be [1, 2, 3], [2, 3, 5], [2, 5, 7]  so the return value will be 3.
 */