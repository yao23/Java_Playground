package com.leetcode.www;

public class WiggleSort { // LC 280
    // One-pass Swap
    public void wiggleSort(int[] nums) { // beats 23.56%
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 0 && nums[i-1] < nums[i]) {  // at even index, check if it's greater than previous number
                swap(nums, i-1, i);
            }
            if (i % 2 != 0 && nums[i-1] > nums[i]) {  // at odd index, check if it's smaller than previous number
                swap(nums, i-1, i);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // The basic idea is to make sure that every odd position is greater than its two adjacent even positions.
    // For example, if the current odd position is i, then we need to make sure the nums[i-1] <= nums[i] and
    // nums[i+1] <= nums[i]. If you do that for the entire array, then the result will satisfy Wiggle Sort's requirement.
    // (It's kind of like a greedy solution IMO, where local optimum leads to global optimum).

    // I also thought about the proof (please tell me if i'm wrong here). I was wondering if there could be a case
    // where a group of 3 numbers couldn't be arranged to meet the requirement. Turns out this is not the case.
    // Let's say we have 3 consecutive numbers in the mid of the array a, b, and c, where b is at an odd index and
    // a, c are at even indices. There are only 4 possible cases:
    // 1. a <= b <= c --> swap(b, c)
    // 2. a <= b >= c --> done
    // 3. a >= b <= c --> swap(a, b), then it could be either case 1 or 2
    // 4. a >= b >= c --> swap(a, b)
    // Whatever the case it is, we will always be able to satisfy the requirement nums[i-1] <= nums[i] and
    // nums[i] >= nums[i+1]. In addition, whether we swap (a, b) or not, it will still maintain the correct order for
    // the previous group (e.g. if we have d, a, b, c where d >= a. If a >= b then we swap(a, b), but still d >= b).
    // Thus, if we do this operation to each [even, odd, even] index group of the array, then we should have the final solution.
    public void wiggleSortV1(int[] nums) { // beats 59.43%
        for (int i = 1; i < nums.length; i++) {
            if (((i & 1) == 0) == (nums[i - 1] < nums[i])) {
                xwap(nums, i);
            }
        }
    }

    private void xwap(int[] a, int i) {
        int t = a[i]; a[i] = a[i - 1]; a[i - 1] = t;
    }

    public void wiggleSortV0(int[] nums) { // beats 23.56%
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 1) {
                if (nums[i - 1] > nums[i]) {
                    swap(nums, i);
                }
            } else if (i != 0 && nums[i - 1] < nums[i]) {
                swap(nums, i);
            }
        }
    }

    private void swap(int[] nums, int i) {
        int tmp = nums[i];
        nums[i] = nums[i - 1];
        nums[i - 1] = tmp;
    }
}

// given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].