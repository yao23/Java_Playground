package com.leetcode.www;

public class SearchForARange { // LC 34 (FindFirstAndLastPositionOfElementInSortedArray)
    public int[] searchRange(int[] nums, int target) {
        if (nums == null) {
            return new int[]{-1, -1};
        }
        return helper(nums, target, 0, nums.length - 1);
    }

    private int[] helper(int[] nums, int target, int start, int end) {
        int leftIndex = -1;
        int rightIndex = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target == nums[mid]) {
                int[] subLeft =  helper(nums, target, start, mid - 1);
                int[] subRight =  helper(nums, target, mid + 1, end);
                leftIndex = subLeft[0] == -1 ? mid : subLeft[0];
                rightIndex = subRight[1] == -1 ? mid : subRight[1];
                break;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return new int[] {leftIndex, rightIndex};
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Find First and Last Position of Element in Sorted Array.
     * Memory Usage: 38.9 MB, less than 99.92% of Java online submissions for Find First and Last Position of Element in Sorted Array.
     *
     * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/discuss/291888/Java-solution-with-explanation-(faster-than-100-and-memory-less-than-99.65)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRangeV1  (int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int low = 0, high = nums.length - 1, first_position = -1, last_position = -1;

        // Search for the first position of element.
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target == nums[mid]) {
                first_position = mid;
                high = mid - 1;
            } else if (target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        // If no element searched, return [-1, -1].
        if (first_position == -1) {
            return new int[]{-1, -1};
        }

        // Search for the last position of element.
        low = 0; high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (target == nums[mid]){
                last_position = mid;
                low = mid + 1;
            } else if (target < nums[mid]) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return new int[]{first_position, last_position};
    }

    public int[] searchRangeV0(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int[] res = {nums.length, - 1};
        searchRange(nums, target, 0, nums.length - 1, res);
        return (res[0] <= res[1]) ? res : new int[]{-1, -1};
    }

    private void searchRange(int[] nums, int target, int start, int end, int[] res) {
        if (start > end) {
            return;
        }
        int mid = start + (end - start) / 2;
        if (nums[mid] < target) {
            searchRange(nums, target, mid + 1, end, res);
        } else if (nums[mid] > target) {
            searchRange(nums, target, start, mid - 1, res);
        } else {
            if (mid < res[0]) {
                res[0] = mid;
                searchRange(nums, target, start, mid - 1, res);
            }
            if (mid > res[1]) {
                res[1] = mid;
                searchRange(nums, target, mid + 1, end, res);
            }
        }
    }
}

// Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
// beats 31.95%