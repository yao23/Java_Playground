package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicateII { // LC 219
    public boolean containsNearbyDuplicate(int[] nums, int k) { // beats 39.90%
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) {
                    return true;
                } else {
                    map.put(nums[i], i);
                }
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }
}

// [], 0 => false
// [1,0,1,1], 1 => true
// [1,2,1], 1 => false