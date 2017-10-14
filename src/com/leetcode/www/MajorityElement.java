package com.leetcode.www;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement { // LC 169
    // sorting
    public int majorityElement(int[] nums) { // beats 70.59%
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    // Hashtable
    public int majorityElement2(int[] nums) { // beats 11.57%
        Map<Integer, Integer> myMap = new HashMap<>();
        int ret = 0;
        for (int num: nums) {
            if (!myMap.containsKey(num)) {
                myMap.put(num, 1);
            } else {
                myMap.put(num, myMap.get(num) + 1);
            }
            if (myMap.get(num) > nums.length / 2) {
                ret = num;
                break;
            }
        }
        return ret;
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