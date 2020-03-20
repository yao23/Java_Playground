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
                // m is memorization for speedup
                // m[4] should be 19 (6 + 7 + 5 + 1) as non-last subarray [0, 1, 4, 5, 6, 7] not 13 (6 + 7) as last subarray [0, 1, 2, 3, 4, 5]
                if (nums.length - depth < 2 * k || resIdx < NUM_SUBARRAYS - 1) {
                    m[depth] = curMax;
                }
            }

            return m[depth];
        }
    }

    private static int[] solveV2(int[] nums, int k) {
        int[] res = new int[NUM_SUBARRAYS];
        int len = nums.length;
        int[][] dp = new int[NUM_SUBARRAYS][len];
        int[] lastRowIdx = new int[len]; // record last row index connected with 2nd row to form tmp max sum
        for (int i = len - k; i >= (NUM_SUBARRAYS - 1) * k; i--) { // last row
            int tmpSum = getKNumsSum(nums, k, i);
            dp[NUM_SUBARRAYS - 1][i] = tmpSum;
        }
        for (int i = NUM_SUBARRAYS - 2; i >= 0; i--) {
            int offset = NUM_SUBARRAYS - i;
            int thisRowMax = 0;
            for (int j = len - offset * k; j >= i * k; j--) {
                int tmpSum = getKNumsSum(nums, k, j);
                int tmpMax = 0;
                int tmpMaxIdx = 0;

                for (int m = j + k; m <= len - (offset - 1) * k; m++) {
                    if (dp[i + 1][m] > tmpMax) {
                        tmpMax = dp[i + 1][m];
                        tmpMaxIdx = m;
                    }
                }

                tmpSum += tmpMax;

                if (i == NUM_SUBARRAYS - 2) { // only record for 2nd row
                    lastRowIdx[j] = tmpMaxIdx;
                    dp[i][j] = tmpSum;
                } else { // 1st row
                    if (tmpSum >= thisRowMax) {
                        thisRowMax = tmpSum;
                        res[NUM_SUBARRAYS - 3] = j;
                        res[NUM_SUBARRAYS - 2] = tmpMaxIdx;
                        res[NUM_SUBARRAYS - 1] = lastRowIdx[tmpMaxIdx];
                    }
                }
            }
        }

        return res;
    }

    private static int getKNumsSum(int[] nums, int k, int idx) {
        int sum = 0;
        for (int j = 0; j < k; j++) {
            sum += nums[idx + j];
        }
        return sum;
    }
}
