package com.leetcode.www;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate { // LC 217
    public boolean containsDuplicate(int[] nums) { // beats 68.65%
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (!set.add(i)) { // if there is same
                return true;
            }
        }
        return false;
    }

    public boolean containsDuplicateV0(int[] nums) { // beats 27.96%
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }
}
