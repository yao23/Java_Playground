package com.leetcode.www;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KDiffPairsInAnArray { // LC 532
    public int findPairs(int[] nums, int k) { // beats 29.75%
        if (nums == null || nums.length == 0 || k < 0)   return 0;

        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                //count how many elements in the array that appear more than twice.
                if (entry.getValue() >= 2) {
                    count++;
                }
            } else {
                if (map.containsKey(entry.getKey() + k)) {
                    count++;
                }
            }
        }

        return count;
    }

    public int findPairsV1(int[] nums, int k) {
        int ans = 0;
        Arrays.sort(nums);
        for (int i = 0, j = 0; i < nums.length; i++) {
            for (j = Math.max(j, i + 1); j < nums.length && (long) nums[j] - nums[i] < k; j++) ;
            if (j < nums.length && (long) nums[j] - nums[i] == k) ans++;
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) i++;
        }
        return ans;
    }

    public int findPairsV0(int[] nums, int k) { // not working for test case 5 ([1,1,1,1,1], 0 => 1)
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

// [1,1,1,1,1], 0 => 1