package com.leetcode.www;

/**
 * Created by liyao on 6/18/17.
 */
public class FindMinimumInRotatedSortedArray { // LC 153
    public int findMin(int[] nums) { // beats 50.67%
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int left = 0, right = nums.length - 1;
        while (left < right - 1) {  // while (left < right-1) is a useful technique
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (nums[left] > nums[right]) {
            return nums[right];
        }
        return nums[left];
    }

    public int findMinV0(int[] nums) {
        int min = Integer.MIN_VALUE;
        int len = nums.length;
        if (len == 0) {
            return min;
        } else if (len == 1) {
            return nums[0];
        } else {
            min = Integer.MAX_VALUE;
            int start = 0, end = len - 1;
            if (nums[start] <= nums[end]) { // in order
                return nums[start];
            } else { // rotated
                while (start + 1 < end) {
                    int mid = start + (end - start) / 2;
                    if (nums[start] <= nums[mid] && nums[mid] <= nums[end]) { // in order
                        return Math.min(nums[start], min);
                    } else {
                        if (nums[start] < nums[mid]) { // left part is in order
                            min = Math.min(nums[start], min);
                            start = mid;
                        } else { // right part is in order
                            min = Math.min(nums[mid], min);
                            end = mid;
                        }
                    }
                }

                int tmpMin = Math.min(nums[start], nums[start + 1]);
                if (tmpMin < min) {
                    return tmpMin;
                } else {
                    return min;
                }
            }
        }
    }

    // [] =>
    // [1] => 1
    // [6,7,0,1,2,3,4,5] => 0
    // [5,6,7,0,1,2,3,4] => 0
    // [4,5,6,7,8,9,0,1,2] => 0

    // beats 3.88%
}
