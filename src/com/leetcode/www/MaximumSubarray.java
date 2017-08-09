package com.leetcode.www;

public class MaximumSubarray { // LC 53
    public int maxSubArray(int[] nums) { // beats 24.96%
        int tmp = checkConerCase(nums);
        if (tmp < 0) {
            return tmp;
        }
        int lastSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            lastSum = Math.max(nums[i], nums[i] + lastSum);
            maxSum = Math.max(maxSum, lastSum);
        }

        return maxSum;
    }

    public int maxSubArrayV0(int[] nums) {

        int[] localMax = new int[nums.length];
        localMax[0] = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            localMax[i] = nums[i] + (localMax[i - 1] > 0 ? localMax[i - 1] : 0);
            max = Math.max(max, localMax[i]);
        }

        return max;
    }

    private int checkConerCase(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        } else {
            return 0;
        }
    }
}

// [-2,1,-3,4,-1,2,1,-5,4] => 6 ([4,-1,2,1])

// beats 40.95%