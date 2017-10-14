package com.leetcode.www;

public class MajorityElement { // LC 169
    public int majorityElement(int[] nums) { // beats 70.59%
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
