package com.leetcode.www;

import java.util.Arrays;

public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
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
}

// nums = [1, 2, 3], target = 4 => 7
// (1, 1, 1, 1)
// (1, 1, 2)
// (1, 2, 1)
// (1, 3)
// (2, 1, 1)
// (2, 2)
// (3, 1)

// beats 84.97%


