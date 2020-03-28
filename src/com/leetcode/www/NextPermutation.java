package com.leetcode.www;

public class NextPermutation { // LC 31
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Next Permutation.
     * Memory Usage: 39.3 MB, less than 50.00% of Java online submissions for Next Permutation.
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) { // beasts 40.19%
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

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Next Permutation.
     * Memory Usage: 40.2 MB, less than 47.00% of Java online submissions for Next Permutation.
     *
     * @param nums
     */
    public void nextPermutationV0(int[] nums) { // beats 57.12%
        if (nums == null || nums.length <= 1) {
            return;
        }
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--; // find 1st id i that breaks descending order
        }
        // if not entirely descending
        if (i >= 0) {
            // start from the end
            int j = nums.length - 1;
            // find rightmost first larger id j
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(i, j, nums);
        }
        // reverse the descending sequence
        reverseArray(i + 1, nums.length - 1, nums);
    }
}

// 1,2,3 → 1,3,2
// 3,2,1 → 1,2,3
// 1,1,5 → 1,5,1