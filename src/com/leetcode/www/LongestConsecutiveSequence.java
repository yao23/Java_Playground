package com.leetcode.www;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence { // LC 128
    public int longestConsecutive(int[] nums) {
        int max = 0;
        if (nums.length == 0) {
            return max;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            int len = 1;
            int tmp = nums[i];
            tmp--;
            while (set.contains(tmp)) {
                len++;
                set.remove(tmp);
                tmp--;
            }
            tmp = nums[i];
            tmp++;
            while (set.contains(tmp)) {
                len++;
                set.remove(tmp);
                tmp++;
            }
            if (len > max) {
                max = len;
            }
        }
        return max;
    }
}

// [100, 4, 200, 1, 3, 2] => 4 (1,2,3,4)

// beats 36.60%