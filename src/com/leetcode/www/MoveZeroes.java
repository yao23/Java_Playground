package com.leetcode.www;

public class MoveZeroes { // LC 283
    public void moveZeroes(int[] nums) { // not working ([12,1,0,3,0] for test case 1)
        int l = 0, r = nums.length - 1;
        while (l < r) {
            while (l != 0 && l < r) {
                l++;
            }
            while (r == 0 && l < r) {
                r--;
            }
            if (l < r) {
                swap(nums, l, r);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

// [0,1,0,3,12] => [1,3,12,0,0]
