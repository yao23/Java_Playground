package com.leetcode.www;

public class MoveZeroes { // LC 283
    /**
     * Shift non-zero values as far forward as possible
     * Fill remaining space with zeros
     * @param nums
     */
    public void moveZeroes(int[] nums) { // beats 87.71%
        if (nums == null || nums.length == 0) {
            return;
        }

        int insertPos = 0;
        for (int num: nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

// [0,1,0,3,12] => [1,3,12,0,0]
