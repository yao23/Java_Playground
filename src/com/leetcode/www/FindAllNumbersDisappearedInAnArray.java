package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class FindAllNumbersDisappearedInAnArray { // LC 448
    public List<Integer> findDisappearedNumbers(int[] nums) { // beats 64.37%
        List<Integer> ret = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1;
            if (nums[val] > 0) {
                nums[val] = -nums[val];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}

// Input: [4,3,2,7,8,2,3,1]
// Output: [5,6]

