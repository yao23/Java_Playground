package com.leetcode.www;

public class SearchForARange { // LC 34
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int[] res = {nums.length - 1};
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