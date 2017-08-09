package com.leetcode.www;

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int[] localMax = new int[nums.length];
        localMax[0] = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            localMax[i] = nums[i] + (localMax[i - 1] > 0 ? localMax[i - 1] : 0);
            max = Math.max(max, localMax[i]);
        }

        return max;
    }
}

// [-2,1,-3,4,-1,2,1,-5,4] => 6 ([4,-1,2,1])

// beats 40.95%