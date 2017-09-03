package com.leetcode.www;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int index = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            reverseArray(0, nums.length - 1, nums);
        } else {
            int biggerIndex = findBig(nums[index - 1], index, nums);
            swap(biggerIndex, index - 1, nums);
            reverseArray(index, nums.length - 1, nums);
        }
    }
    private int findBig(int sentinal, int index, int[] num) {
        int bigIndex = index;
        int bigValue = num[index];
        for (int i = index + 1; i < num.length; i++) {
            if (sentinal < num[i] && num[i] <= bigValue) {
                bigValue = num[i];
                bigIndex = i;
            }
        }
        return bigIndex;
    }
    private void reverseArray(int start, int end, int[] num) {
        while (start < end) {
            swap(start, end, num);
            start++;
            end--;
        }
    }
    private void swap(int m, int n, int[] num) {
        int tmp = num[m];
        num[m] = num[n];
        num[n] = tmp;
    }
}
