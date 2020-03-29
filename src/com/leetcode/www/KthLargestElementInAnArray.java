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

    /**
     * Runtime: 7 ms, faster than 28.18% of Java online submissions for Kth Largest Element in an Array.
     * Memory Usage: 41.3 MB, less than 5.18% of Java online submissions for Kth Largest Element in an Array.
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestV0(int[] nums, int k) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int idx = pivotSort(nums, l, r);
            if (idx == k - 1) {
                return nums[idx];
            } else if (idx < k - 1) {
                l = idx + 1;
            } else {
                r = idx - 1;
            }
        }
        return nums[l];
    }

    private int pivotSort(int[] nums, int l, int r) {
        int pivot = nums[l];
        while (l < r) {
            while (l < r && nums[r] < pivot) {
                r--;
            }
            nums[l] = nums[r];
            while (l < r && nums[l] >= pivot) {
                l++;
            }
            nums[r] = nums[l];
        }
        nums[l] = pivot;
        return l;
    }
}

// [3,2,1,5,6,4] and k = 2, return 5.

// beast 32.56%