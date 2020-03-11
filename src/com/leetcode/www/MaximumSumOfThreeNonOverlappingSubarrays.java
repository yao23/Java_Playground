package com.leetcode.www;

import java.util.Arrays;

public class MaximumSumOfThreeNonOverlappingSubarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] res1 = new int[3];
        int[] res2 = new int[3];
        int[] m = new int[nums.length];
        Arrays.fill(m, -1);
        solve(0, nums, k, 0, 0, 0, 0, res1, res2, m);
        if (res1[2] == 0) {
            return res2;
        } else {
            return res1;
        }
    }

    private int solve(int depth, int[] nums, int k, int tmpIdx1, int tmpIdx2, int resIdx1, int resIdx2, int[] resArr1,  int[] resArr2, int[] m) {
        if (depth == nums.length) {
            return 0;
        } else {
            if (m[depth] < 0) {
                if (tmpIdx1 == k) {
                    tmpIdx1 = 0;
                }
                if (tmpIdx2 == k) {
                    tmpIdx2 = 0;
                }
                int res1 = 0;
                if (resIdx2 < 3 ) {
                    if (tmpIdx2 == 0) {
                        resArr2[resIdx2] = depth;
                        resIdx2++;
                    }

                    res1 = solve(depth + 1, nums, k, tmpIdx1, tmpIdx2 + 1, resIdx1, resIdx2, resArr1, resArr2, m);
                }

                int res2 = 0;
                if (resIdx1 < 3) {
                    if (tmpIdx1 == 0) {
                        resArr1[resIdx1] = depth;
                        resIdx1++;
                    }

                    res2 = nums[depth] + solve(depth + 1, nums, k, tmpIdx1 + 1, tmpIdx2, resIdx1, resIdx2, resArr1, resArr2, m);
                }

                m[depth] = Math.max(res1, res2);
            }
            return m[depth];
        }
    }
}
