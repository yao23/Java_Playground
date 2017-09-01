package com.leetcode.www;

public class RemoveElement { // LC 27
    public int removeElement(int[] nums, int val) { // beats 53.30%
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1 && nums[0] == val) {
            nums[0] = 0;
            return 0;
        }
        int current = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                continue;
            }

            nums[current] = nums[i];
            current++;
        }
        return current;
    }
}

// [3,2,2,3], val = 3 => [2,2]