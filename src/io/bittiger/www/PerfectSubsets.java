package io.bittiger.www;

import java.util.Arrays;

public class PerfectSubsets {
    private int counter = 0;
    public int getPerfectCount(int[]   nums) {
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
