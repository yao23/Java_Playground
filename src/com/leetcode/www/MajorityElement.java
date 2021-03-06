package com.leetcode.www;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement { // LC 169
    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Majority Element.
     * Memory Usage: 41 MB, less than 83.88% of Java online submissions for Majority Element.
     *
     * sorting
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) { // beats 70.59%
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    /**
     * Runtime: 16 ms, faster than 15.21% of Java online submissions for Majority Element.
     * Memory Usage: 39.1 MB, less than 99.69% of Java online submissions for Majority Element.
     *
     * HashMap
     *
     * @param nums
     * @return
     */
    public int majorityElementV1(int[] nums) { // beats 11.57%
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

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Majority Element.
     * Memory Usage: 41.9 MB, less than 50.37% of Java online submissions for Majority Element.
     *
     * Moore voting algorithm
     *
     * @param nums
     * @return
     */
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