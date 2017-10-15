package com.leetcode.www;

public class TwoSumII { // Input array is sorted
    // binary search
    public int[] twoSum(int[] numbers, int target) { // beats 99.27%
        if (numbers == null || numbers.length == 0) {
            return new int[2];
        }
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            if (numbers[start] + numbers[end] == target) {
                return new int[]{start + 1, end + 1};
            } else if (numbers[start] + numbers[end] > target) {
                // move end forward to the last value that numbers[end] <= target - numbers[start]
                end = largestSmallerOrLastEqual(numbers, start, end, target - numbers[start]);
            } else {
                // move start backward to the first value that numbers[start] >= target - numbers[end]
                start = smallestLargerOrFirstEqual(numbers, start, end, target - numbers[end]);
            }
        }
        return new int[2];
    }
    private int largestSmallerOrLastEqual(int[] numbers, int start, int end, int target) {
        int left = start;
        int right = end;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
    private int smallestLargerOrFirstEqual(int[] numbers, int start, int end, int target) {
        int left = start;
        int right = end;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    // two pointers
    public int[] twoSumV0(int[] numbers, int target) { // beats 39.70%
        int[] res = new int[]{-1, -1};
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                res[0] = l + 1;
                res[1] = r + 1;
                return res;
            } else if (sum < target) {
                l++;
            } else {
                r--;
            }
        }
        return res;
    }
}

// Input: numbers={2, 7, 11, 15}, target=9
// Output: index1=1, index2=2
