package com.leetcode.www;

public class SingleNumberII { // LC 137
    public int singleNumber(int[] nums) { // beats 71.50%
        int ones = 0, twos = 0, threes = 0;
        for (int i = 0; i < nums.length; i++) {
            twos |= (ones & nums[i]);
            ones ^= nums[i];
            threes = ~(ones & twos);
            ones &= threes;
            twos &= threes;
        }
        return ones;
    }
}
