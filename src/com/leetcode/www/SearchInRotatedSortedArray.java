package com.leetcode.www;

/**
 * Created by liyao on 6/15/17.
 */
public class SearchInRotatedSortedArray { // LC 33 (Facebook)
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array.
     * Memory Usage: 38.1 MB, less than 46.54% of Java online submissions for Search in Rotated Sorted Array.
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) { // beats 11.68%
        int start = 0, end = nums.length - 1, mid = -1;
        while(start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] < nums[end] || nums[mid] < nums[start]) {
                // If we know for sure right side is sorted or left side is unsorted
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else if (nums[mid] > nums[start] || nums[mid] > nums[end]) {
                // If we know for sure left side is sorted or right side is unsorted
                if (target < nums[mid] && target >= nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                // If we get here, that means nums[start] == nums[mid] == nums[end], then shifting out
                // any of the two sides won't change the result but can help remove duplicate from
                // consideration, here we just use end-- but left++ works too
                end--;
            }
        }

        return false;
    }

    public boolean searchV2(int[] nums, int target) { // beats 11.68%
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            // System.out.format("start=%d,mid=%d,end=%d\n",start,mid,end);
            if (nums[mid] == target) return true;

            // need to handle: 1,3,1,1,1
            while (nums[start] == nums[mid] && start != mid) {
                start++;
            }
            while (nums[mid] == nums[end] && mid != end) {
                end--;
            }

            // the following is the same as problem I
            if (nums[start] <= nums[mid]) {
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return false;
    }

    public boolean searchV1(int[] nums, int target) { // not working for [1,1,3,1],3 => true
        int len = nums.length;
        if (len == 0) {
            return false;
        } else if (len == 1) {
            if (nums[0] == target) {
                return true;
            } else {
                return false;
            }
        } else {
            int start = 0, end = len - 1;

            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] == target) {
                    return true;
                }
                if (nums[start] < nums[mid]) { // left part is in order
                    if (nums[mid] < target) {
                        start = mid;
                    } else {
                        if (nums[start] == target) {
                            return true;
                        } else if (nums[start] < target) { // target maybe locate in left part
                            end = mid;
                        } else { // target maybe locate in right part
                            start = mid;
                        }
                    }
                } else { // right part is in order
                    if (nums[mid] < target) {
                        if (nums[end] == target) {
                            return true;
                        } else if (nums[end] < target) { // target maybe locate in left part
                            end = mid;
                        } else { // target maybe locate in right part
                            start = mid;
                        }
                    } else {
                        end = mid;
                    }
                }
            }

            if (nums[start] == target) {
                return true;
            } else if (nums[start + 1] == target) {
                return true;
            } else {
                return false;
            }
        }
    }

    public int searchV0(int[] nums, int target) {
        int index = -1;
        int len = nums.length;
        if (len == 0) {
            return index;
        } else if (len == 1) {
            if (nums[0] == target) {
                return 0;
            } else {
                return index;
            }
        } else {
            int start = 0, end = len - 1;

            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[start] < nums[mid]) { // left part is in order
                    if (nums[mid] < target) {
                        start = mid;
                    } else {
                        if (nums[start] == target) {
                            return start;
                        } else if (nums[start] < target) { // target maybe locate in left part
                            end = mid;
                        } else { // target maybe locate in right part
                            start = mid;
                        }
                    }
                } else { // right part is in order
                    if (nums[mid] < target) {
                        if (nums[end] == target) {
                            return end;
                        } else if (nums[end] < target) { // target maybe locate in left part
                            end = mid;
                        } else { // target maybe locate in right part
                            start = mid;
                        }
                    } else {
                        end = mid;
                    }
                }
            }

            if (nums[start] == target) {
                return start;
            } else if (nums[start + 1] == target) {
                return start + 1;
            } else {
                return index;
            }
        }
    }

    // [],5 => -1
    // [1],5 => -1
    // [5],5 => 0
    // [4,5,6,7,0,1,2],5 => 1
    // [4,5,6,7,0,1,2],2 => 6
    // [4,5,0,1,2],2 => 4
    // [4,5,0,1,2],-1 => -1

    // beats 53.55%
}
