package com.leetcode.www;

import java.util.Arrays;

public class MaximumSumOfThreeNonOverlappingSubarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] res = new int[3];
        int[] m = new int[nums.length];
        Arrays.fill(m, -1);
        solve(0, nums, k, 0, 0, res, m);
        return res;
    }

    private int solve(int depth, int[] nums, int k, int tmpIdx, int resIdx, int[] resArr, int[] m) {
        if (resIdx == 3) {
            return 0;
        } else {
            if (depth == nums.length) {
                return 0;
            }
            if (m[depth] >= 0) {
                return m[depth];
            } else {
                int res1 = solve(depth + 1, nums, k, tmpIdx, resIdx, resArr, m);

                if (tmpIdx == 0) {
                    resArr[resIdx] = depth;
                    resIdx++;
                }
                int res2 = nums[depth] + solve(depth + 1, nums, k, tmpIdx + 1, resIdx, resArr, m);
                m[depth] = Math.max(res1, res2);
                return m[depth];
            }
        }
    }
}
