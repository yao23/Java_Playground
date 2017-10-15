package com.leetcode.www;

public class TwoSumII { // Input array is sorted
    public int[] twoSum(int[] numbers, int target) { // beats 39.70%
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
