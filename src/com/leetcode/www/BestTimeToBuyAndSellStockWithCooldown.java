package com.leetcode.www;

public class BestTimeToBuyAndSellStockWithCooldown { // LC 309
    /**
     * https://discuss.leetcode.com/topic/31015/very-easy-to-understand-one-pass-o-n-solution-with-no-extra-space/2
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) { // beats 93.05%
        if (prices.length<2) return 0;
        int buy = -prices[0], sell = 0, cooldown = 0;
        for(int i=1; i<prices.length; i++) {
            int temp = buy;
            buy = Math.max(buy, cooldown-prices[i]);
            cooldown = Math.max(sell, cooldown);
            sell = Math.max(sell, temp+prices[i]);
        }
        return sell>cooldown?sell:cooldown;
    }

    /**
     * https://discuss.leetcode.com/topic/32836/o-n-java-solution-3ms/2
     *
     * @param prices
     * @return
     */
    // DP
    public int maxProfitV1(int[] prices) { // beats 43.43%
        if (prices == null || prices.length < 2)  {
            return 0;
        }
        int[] s0 = new int[2];
        int[] s1 = new int[2];
        int[] s2 = new int[2];
        s0[0] = 0;
        s1[0] = -prices[0];
        s2[0] = Integer.MIN_VALUE;

        for (int i = 1; i < prices.length; ++i) {
            s0[i % 2] = Math.max(s0[(i - 1) % 2], s2[(i - 1) % 2]);
            s1[i % 2] = Math.max(s1[(i - 1) % 2], s0[(i - 1) % 2] - prices[i]);
            s2[i % 2] = s1[(i - 1) % 2] + prices[i];
        }

        return Math.max(s0[(prices.length - 1) % 2], s2[(prices.length - 1) % 2]);
    }

    /**
     * beats 20.41%
     *
     * @param prices
     * @return
     */
    public int maxProfitV0(int[] prices) {
        int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(prev_sell - price, prev_buy);
            prev_sell = sell;
            sell = Math.max(prev_buy + price, prev_sell);
        }
        return sell;
    }
}

// prices = [1, 2, 3, 0, 2]
// maxProfit = 3
// transactions = [buy, sell, cooldown, buy, sell]
