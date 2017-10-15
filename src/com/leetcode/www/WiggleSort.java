package com.leetcode.www;

public class WiggleSort { // LC 280
    public void wiggleSort(int[] nums) { // beats 23.56%
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 1) {
                if (nums[i - 1] > nums[i]) {
                    swap(nums, i);
                }
            } else if (i != 0 && nums[i - 1] < nums[i]) {
                swap(nums, i);
            }
        }
    }

    private void swap(int[] nums, int i) {
        int tmp = nums[i];
        nums[i] = nums[i - 1];
        nums[i - 1] = tmp;
    }
}

// given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].