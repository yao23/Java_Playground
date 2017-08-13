package com.leetcode.www;

import java.util.Arrays;

public class KDiffPairsInAnArray { // LC 532
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        k = Math.abs(k);

        int left = 0;
        int right = 1;
        int count = 0;

        while (right < nums.length) {
            if (left >= right || nums[right] - nums[left] < k) {
                right++;
            } else if (left > 0 && nums[left] == nums[left - 1] || nums[right] - nums[left] > k) {
                left++;
            } else {
                right++;
                count++;
            }
        }

        return count;
    }
}

// Input: [3, 1, 4, 1, 5], k = 2
// Output: 2
// Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
// Although we have two 1s in the input, we should only return the number of unique pairs.

// Input:[1, 2, 3, 4, 5], k = 1
// Output: 4
// Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).

// Input: [1, 3, 1, 5, 4], k = 0
// Output: 1
// Explanation: There is one 0-diff pair in the array, (1, 1).

// [1,2,3,4,5], -1 => 0