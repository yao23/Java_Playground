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

    /**
     * Runtime: 647 ms, faster than 5.00% of Java online submissions for Maximum Sum of 3 Non-Overlapping Subarrays.
     * Memory Usage: 44 MB, less than 5.88% of Java online submissions for Maximum Sum of 3 Non-Overlapping Subarrays.
     *
     * @param nums
     * @param k
     * @return
     */
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

    /**
     * Runtime: 381 ms, faster than 5.00% of Java online submissions for Maximum Sum of 3 Non-Overlapping Subarrays.
     * Memory Usage: 43.5 MB, less than 5.88% of Java online submissions for Maximum Sum of 3 Non-Overlapping Subarrays.
     *
     * @param nums
     * @param k
     * @return
     */
    private static int[] solveV3(int[] nums, int k) {
        int[] res = new int[NUM_SUBARRAYS];
        int len = nums.length;
        int[][] dp = new int[NUM_SUBARRAYS][len];
        int[] lastRowIdx = new int[len]; // record last row index connected with 2nd row to form tmp max sum
        int[] subArrSum = new int[len];
        int tmpSum = getKNumsSum(nums, k, len - k);
        for (int i = len - k; i >= (NUM_SUBARRAYS - 1) * k; i--) { // last row
            if (i < len - k) {
                tmpSum += (nums[i] - nums[i + k]);
            }
            dp[NUM_SUBARRAYS - 1][i] = tmpSum;
            subArrSum[i] = tmpSum;
        }
        for (int i = NUM_SUBARRAYS - 2; i >= 0; i--) {
            int offset = NUM_SUBARRAYS - i;
            int thisRowMax = 0;
            if (subArrSum[len - offset * k] > 0) {
                tmpSum = subArrSum[len - offset * k];
            } else {
                tmpSum = getKNumsSum(nums, k, len - offset * k);
            }
            for (int j = len - offset * k; j >= i * k; j--) {
                if (j < len - offset * k) {
                    tmpSum += (nums[j] - nums[j + k]);
                    subArrSum[j] = tmpSum;
                }
                int tmpMax = 0;
                int tmpMaxIdx = 0;

                for (int m = j + k; m <= len - (offset - 1) * k; m++) {
                    if (dp[i + 1][m] > tmpMax) {
                        tmpMax = dp[i + 1][m];
                        tmpMaxIdx = m;
                    }
                }

                int sumThisAndNextRow = tmpSum + tmpMax;

                if (i == NUM_SUBARRAYS - 2) { // only record for 2nd row
                    lastRowIdx[j] = tmpMaxIdx;
                    dp[i][j] = sumThisAndNextRow;
                } else { // 1st row
                    if (sumThisAndNextRow >= thisRowMax) {
                        thisRowMax = sumThisAndNextRow;
                        res[0] = j;
                        res[1] = tmpMaxIdx;
                        res[2] = lastRowIdx[tmpMaxIdx];
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

    /**
     * Runtime: 2 ms, faster than 99.58% of Java online submissions for Maximum Sum of 3 Non-Overlapping Subarrays.
     * Memory Usage: 43.9 MB, less than 5.88% of Java online submissions for Maximum Sum of 3 Non-Overlapping Subarrays.
     *
     * Time Complexity: O(N), where N is the length of the array.
     * Every loop is bounded in the number of steps by N, and does O(1) work.
     *
     * Space complexity: O(N). W, left, and right all take O(N) memory.
     * @param nums
     * @param K
     * @return
     */
    public int[] maxSumOfThreeSubarraysV2(int[] nums, int K) {
        // W is an array of sums of windows (trick: reuse this array for all interval sum)
        int[] W = new int[nums.length - K + 1];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= K) sum -= nums[i-K];
            if (i >= K-1) W[i-K+1] = sum;
        }

        int[] left = new int[W.length]; // max subarray sum from left side (same as first row calculation in line 113)
        int best = 0;
        for (int i = 0; i < W.length; i++) {
            if (W[i] > W[best]) best = i;
            left[i] = best;
        }

        int[] right = new int[W.length];
        best = W.length - 1;
        for (int i = W.length - 1; i >= 0; i--) { // max subarray sum from right side (same as last row in line 89)
            if (W[i] >= W[best]) best = i;
            right[i] = best;
        }

        int[] ans = new int[]{-1, -1, -1};
        for (int j = K; j < W.length - K; j++) {
            int i = left[j - K], k = right[j + K];
            if (ans[0] == -1 || W[i] + W[j] + W[k] >
                    W[ans[0]] + W[ans[1]] + W[ans[2]]) {
                ans[0] = i;
                ans[1] = j;
                ans[2] = k;
            }
        }
        return ans;
    }
}
