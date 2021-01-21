package com.leetcode.www;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liyao on 7/12/17.
 */
public class CoinChange { // LC 322
    /**
     * Runtime: 10 ms, faster than 84.43% of Java online submissions for Coin Change.
     * Memory Usage: 34.8 MB, less than 99.99% of Java online submissions for Coin Change.
     *
     * [1], 0 => 0
     * [1,2,5], 11 => 3 (5 + 5 + 1)
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount <= 0) {
            return 0;
        }
        int[] minNum = new int[amount + 1];
        for (int i = 1; i <= amount; i++) { // start from 1, not 0, minNum[0] = 0
            minNum[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i && minNum[i - coins[j]] != Integer.MAX_VALUE) {
                    minNum[i] = Math.min(minNum[i], 1 + minNum[i - coins[j]]);
                }
            }
        }

        if (minNum[amount] == Integer.MAX_VALUE) {
            return  -1;
        } else {
            return minNum[amount];
        }
    }

    /**
     * TLE for [3,7,405,436], 8839
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeV0(int[] coins, int amount) {
        int[] result = new int[] { Integer.MAX_VALUE};
        Arrays.sort(coins);
        helper(coins, amount, 0, new ArrayList<Integer>(), result);
        return (result[0] < Integer.MAX_VALUE) ? result[0] : -1;
    }

    private void helper(int[] coins, int amount, int depth, List<Integer> tmpRes, int[] result) {
        if (amount == 0) {
            if (tmpRes.size() < result[0]) {
                result[0] = tmpRes.size();
            }
        } else if (amount < 0) {
            return;
        } else {
            for (int i = depth; i < coins.length; i++) {
                tmpRes.add(coins[i]);
                helper(coins, amount - coins[i], i, tmpRes, result);
                tmpRes.remove(tmpRes.size() - 1);
            }
        }
    }



    // [3,7,405,436], 8839 => 25

    // beats 70.94%
}
