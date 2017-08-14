package com.leetcode.www;

public class KthLargestElementInAnArray { // LC 215
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return Integer.MIN_VALUE;
        }

        // change to 0 based kth smallest
        return findKthLargest(nums, 0, nums.length - 1, nums.length - k);
    }

    // quick select: kth smallest
    private int findKthLargest(int[] nums, int start, int end, int k) {
        if (start > end) {
            return Integer.MIN_VALUE;
        }

        int pivot = nums[end]; // take nums[end] as the pivot
        int pos = start;

        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) {
                swap(nums, pos, i);
                pos++;
            }
        }

        // set pivot to the partition position
        swap(nums, pos, end);

        if (pos == k) { // found
            return nums[pos];
        } else { // not found, go to next level
            return (pos < k) ? findKthLargest(nums, pos + 1, end, k) : findKthLargest(nums, start, pos - 1, k);
        }

    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        } else {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }
    }
}

// [3,2,1,5,6,4] and k = 2, return 5.

// beast 32.56%