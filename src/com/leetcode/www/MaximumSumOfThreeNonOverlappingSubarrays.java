package com.leetcode.www;

import java.util.Arrays;

public class MaximumSumOfThreeNonOverlappingSubarrays {
    private static final int NUM_SUBARRAYS = 3;
    private static int max = 0;
    private static int[] resultArr = new int[NUM_SUBARRAYS];

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] m = new int[nums.length];
        Arrays.fill(m, -1);
        solve(0, nums, k, 0, 0, new int[3], m, 0);
        return resultArr;
    }

    private void solve(int depth, int[] nums, int k, int tmpIdx, int resIdx, int[] resArr, int[] m, int sum) {
        if (depth == nums.length - 1) {
            sum += nums[depth];
            if (sum > max) {
                max = sum;
                System.arraycopy(resArr, 0, resultArr, 0, NUM_SUBARRAYS);
            }
        } else {
            if (m[depth] < 0) {
                if (tmpIdx == k) {
                    resIdx++;
                    tmpIdx = 0;
                    if (resIdx == NUM_SUBARRAYS) {
                        if (sum > max) {
                            max = sum;
                            resultArr = Arrays.copyOf(resArr, nums.length);
                        }
                        return;
                    }
                }

                if (tmpIdx == 0) {
                    resArr[resIdx] = depth;
                }

                for (int i = depth; i <= nums.length - (NUM_SUBARRAYS - resIdx) * k; i++) {
                    solve(i + 1, nums, k, tmpIdx + 1, resIdx, resArr, m, sum + nums[i]);
                }
            }
        }
    }
}
