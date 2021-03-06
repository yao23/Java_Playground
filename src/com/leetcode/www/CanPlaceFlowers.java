package com.leetcode.www;

public class CanPlaceFlowers { // LC 605
    /**
     * beats 86.77%
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i = 0, count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0)
                    && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i++] = 1;
                count++;
            }
            if (count >= n) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * beats 0.57%
     *
     * DP
     * A Java DP solution runs on
     * K: number of flowers to plant
     * N: length of flower bed
     * O(K) space, O(NK) time
     *
     * dp[i][j]: can put j flowers in first i place, i, j starting from 1
     * dp[i][j] =
     *            dp[i-1][j] || dp[i-2][j-1], if flower[i] == 0
     *            dp[i-2][j], if flower[i] == 1
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowersV2(int[] flowerbed, int n) {
        if (n == 0) { // nothing to plant
            return true;
        }

        if (flowerbed.length == 0) { // no place to plant
            return false;
        }

        boolean[][] dp = new boolean[3][n + 1];
        dp[0][0] = true;

        for (int j = 1; j <= n; j++) { // init: first j flowers put into non-space
            dp[0][j] = false;
        }

        for (int j = 1; j <= n; j++) { // init: first j flowers put into first space
            dp[1][j] = j == 1 && flowerbed[j - 1] == 0;
        }

        for (int i = 1; i < 3; i++) { // init: no flowers put into first i space
            dp[i][0] = true;
        }

        for (int i = 2; i <= flowerbed.length; i++) { // dp
            for (int j = 1; j <= n; j++) {
                if (flowerbed[i - 1] == 0) {
                    dp[i % 3][j] = dp[(i - 1) % 3][j] || dp[(i - 2) % 3][j - 1] && flowerbed[i - 2] == 0;
                } else {
                    dp[i % 3][j] = dp[(i-2) % 3][j];
                }

                //System.out.println(i + ":" + j + ": " + dp[i%3][j]);
            }
        }

        return dp[flowerbed.length % 3][n];
    }

    /**
     * greedy
     *
     * beats 12.95%
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowersV1(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length && count < n; i++) {
            if (flowerbed[i] == 0) {
                //get next and prev flower bed slot values. If i lies at the ends the next and prev are considered as 0.
                int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
                int prev = (i == 0) ? 0 : flowerbed[i - 1];
                if (next == 0 && prev == 0) {
                    flowerbed[i] = 1;
                    count++;
                }
            }
        }

        return count == n;
    }

    /**
     * beats 20.42%
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowersV0(int[] flowerbed, int n) {
        int len = flowerbed.length;
        for (int i = 0; i < len && n > 0; i++) {
            if (isValid(flowerbed, i)) {
                flowerbed[i] = 1;
                n--;
            }
        }
        return (n == 0);
    }

    private boolean isValid(int[] flowerbed, int idx) {
        if (flowerbed[idx] == 1) {
            return false;
        }
        int len = flowerbed.length;
        if (idx == 0) {
            if (len == 1) {
                return true;
            } else {
                return (flowerbed[idx + 1] == 0);
            }
        } else if (idx == len - 1) {
            return (flowerbed[idx - 1] == 0);
        } else {
            return (flowerbed[Math.max(0, idx - 1)] == 0 && flowerbed[Math.min(idx + 1, len -1)] == 0);
        }
    }
}
