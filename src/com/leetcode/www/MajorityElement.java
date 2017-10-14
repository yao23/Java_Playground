package com.leetcode.www;

import java.util.Arrays;

public class MajorityElement { // LC 169
    // sorting
    public int majorityElement(int[] nums) { // beats 70.59%
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    public int majorityElementV0(int[] nums) { // beats 70.59%
        int major = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++){
            if (count == 0) {
                count++;
                major = nums[i];
            } else if (major == nums[i]) {
                count++;
            } else {
                count--;
            }

        }
        return major;
    }
}

// [1] => 1