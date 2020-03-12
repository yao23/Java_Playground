package com.leetcode.www;

import java.util.Arrays;

public class MaximumSumOfThreeNonOverlappingSubarrays {
    private static int max = 0;
    private static int[] resultArr = new int[3];
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
                resultArr = Arrays.copyOf(resArr, nums.length);
            }
        } else {
            if (m[depth] < 0) {
                if (tmpIdx == k) {
                    if (resIdx == 3) {
                        sum += nums[depth];
                        if (sum > max) {
                            max = sum;
                            resultArr = Arrays.copyOf(resArr, nums.length);
                        }
                    }
                    resIdx++;
                    tmpIdx = 0;
                }

                if (tmpIdx == 0) {
                    resArr[resIdx] = depth;
                }

                for (int i = depth; i <= nums.length - (3 - resIdx) * k; i++) {
                    solve(i + 1, nums, k, tmpIdx + 1, resIdx, resArr, m, sum + nums[i]);
                }
            }
        }
    }
}
