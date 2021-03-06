package com.leetcode.www;

public class ProductOfArrayExceptSelf { // LC 238  (Facebook, FB)
    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Product of Array Except Self.
     * Memory Usage: 48 MB, less than 5.51% of Java online submissions for Product of Array Except Self.
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) { // beats 27.57%
        int[] result = new int[nums.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = 1;
        }
        int left = 1, right = 1;
        for (int i = 0, j = nums.length - 1; i < nums.length - 1; i++, j--) {
            left *= nums[i];
            right *= nums[j];
            result[i + 1] *= left;
            result[j - 1] *= right;
        }
        return result;
    }

    public int[] productExceptSelfV1(int[] nums) { // beats 27.57%
        int[] result = new int[nums.length];
        for (int i = 0, tmp = 1; i < nums.length; i++) { // left to right (previous product)
            result[i] = tmp;
            tmp *= nums[i];
        }
        for (int i = nums.length - 1, tmp = 1; i >= 0; i--) { // right to left (later product)
            result[i] *= tmp;
            tmp *= nums[i];
        }
        return result;
    }

    public int[] productExceptSelfV0(int[] nums) { // beats 27.57%
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) { // previous and last one
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) { // right ones
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}
