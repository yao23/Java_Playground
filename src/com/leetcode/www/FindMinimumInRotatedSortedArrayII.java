package com.leetcode.www;

public class FindMinimumInRotatedSortedArrayII { // LC 154
    public int findMin(int[] nums) { // beats 6.61
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        int start = 0, end = nums.length - 1;

        // only need to add the following while loop on top of the solution
        // for Part I
        // if two line segments have overlap, remove the overlap.
        // so, the problem can be solved as Part I
        while (nums[end] == nums[start] && end > start) {
            end--;
        }

        while (start < end) {
            //if the linear monotonically increasing in [start, end]
            if (nums[start] < nums[end]) {
                return nums[start];
            }

            int mid = start + (end - start) / 2;
            if (nums[mid] >= nums[start]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return nums[start];
    }

    public int findMinV0(int[] nums) { // beats 6.61%
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] < nums[r]) {
                r = mid;
            } else if (nums[mid] > nums[r]){
                l = mid + 1;
            } else { // nums[mid] = nums[r] no idea, but we can eliminate nums[r];
                r--;
            }
        }
        return nums[l];
    }
}

// [1] => 1