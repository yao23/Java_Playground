package com.leetcode.www;

public class MaximumProductSubarray { // LC 152
    /**
     * Max product could be smallest * smallest
     *
     * Track both max and min
     *
     * max[i] = Max(num[i], num[i] * max[i - 1], num[i] * min[i - 1])
     *
     * min[i] = Max(num[i], num[i] * max[i - 1], num[i] * min[i - 1])
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        int min = nums[0];
        int globalMax = max;

        for (int i = 1; i < nums.length; i++) {
            int tmp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(tmp * nums[i], min * nums[i]), nums[i]);
            globalMax = Math.max(globalMax, max);
        }

        return globalMax;
    }
}

// [-2] => -2
// [2,3,-2,4] => 6 ([2,3])
// [-4,-3,-2] => 12 ([-4,-3])

// beats 11.93%




