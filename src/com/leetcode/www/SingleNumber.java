package com.leetcode.www;

public class SingleNumber { // LC 136
    public int singleNumber(int[] nums) { // beats 32.11%
        int result = nums[0];
        for( int i = 1; i < nums.length; i++ )
            result ^= nums[i];
        return result;
    }
}

// [1] => 1
