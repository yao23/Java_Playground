package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class TwoSum { // LC 1
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                int diffIdx = map.get(diff);
                if (diffIdx < i) {
                    return new int[]{diffIdx, i};
                } else {
                    return new int[]{i, diffIdx};
                }
            } else {
                map.put(nums[i], i);
            }
        }

        return new int[]{-1, -1};
    }

    // [3,2,4],6 => [1,2]
    // [2,7,11,15],9 => [0,1]

    // beats 78.45%
}
