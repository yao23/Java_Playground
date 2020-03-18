package com.leetcode.www;

import java.util.Arrays;

public class MaximumSumOfThreeNonOverlappingSubarrays {
    private static final int NUM_SUBARRAYS = 3;
    private static int max = 0;
    private static int[] resultArr = new int[NUM_SUBARRAYS];

    /**
     * [1,2,1,2,6,7,5,1], 2
     * [1,2,1,2,1,2,1,2,1], 2
     *
     * DP with memorization
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] m = new int[nums.length];
        Arrays.fill(m, -1);
        solve(0, nums, k, 0, 0, new int[3], m, 0);
        return resultArr;
    }

    private static int solve(int depth, int[] nums, int k, int tmpIdx, int resIdx, int[] resArr, int[] m, int sum) {
        if (depth == nums.length) {
            if (sum > max) {
                max = sum;
                System.arraycopy(resArr, 0, resultArr, 0, NUM_SUBARRAYS);
            }
            return 0;
        } else {
            if (m[depth] < 0) {
                if (tmpIdx == k) {
                    resIdx++;
                    tmpIdx = 0;
                    if (resIdx == 3) {
                        if (sum > max) {
                            max = sum;
                            System.arraycopy(resArr, 0, resultArr, 0, NUM_SUBARRAYS);
                        }
                        return 0;
                    }
                }

                int curMax = Integer.MIN_VALUE;
                for (int i = depth; i <= nums.length - (NUM_SUBARRAYS - resIdx) * k; i++) {
                    if (tmpIdx == 0) {
                        resArr[resIdx] = i;
                    }

                    int curSubArrSum = 0;
                    for (int j = 0; j < k; j++) {
                        curSubArrSum += nums[i + j];
                    }
                    int nextMax = solve(i + k, nums, k, tmpIdx + k, resIdx, resArr, m, sum + curSubArrSum);
                    curMax = Math.max(curMax, nextMax + curSubArrSum);
                }

                // index: 0   1   2  3  4  5  6  7
                // input: 1   2   1  2  6  7  5  1
                // m:     23  -1 20 20 19 12  6 -1
                // m is memorization for seedup
                // m[4] should be 19 (6 + 7 + 5 + 1) as non-last subarray [0, 1, 4, 5, 6, 7] not 13 (6 + 7) as last subarray [0, 1, 2, 3, 4, 5]
                if (nums.length - depth < 2 * k || resIdx < NUM_SUBARRAYS - 1) {
                    m[depth] = curMax;
                }
            }

            return m[depth];
        }
    }

    private static int[] solve(int depth, int[] nums, int k, int tmpIdx, int resIdx, int[] resArr, int[] m, int sum) {
        int[] res = new int[NUM_SUBARRAYS];
        int len = nums.length;
        int[][] dp = new int[NUM_SUBARRAYS][len];
        int maxIdx = len - 1;
        for (int i = len - k; i >= len - (NUM_SUBARRAYS - 1) * k; i--) {
            int tmpSum = 0;
            for (int j = 0; j < k; j++) {
                tmpSum += nums[i + j];
            }
            if (tmpSum > dp[NUM_SUBARRAYS][i + 1]) {
                dp[NUM_SUBARRAYS - 1][i] = tmpSum;
                res[NUM_SUBARRAYS - 1] = i;
                maxIdx = i;
            } else {
                dp[NUM_SUBARRAYS - 1][i] = dp[NUM_SUBARRAYS][i + 1];
                res[NUM_SUBARRAYS - 1] = maxIdx;
            }
        }

    }
}
