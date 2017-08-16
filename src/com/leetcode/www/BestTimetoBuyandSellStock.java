package com.leetcode.www;

public class BestTimetoBuyandSellStock { // LC 121
	public static int maxProfit(int[] prices) {
		if (prices.length == 0)  {
			return 0;
		}
		int min_price = prices[0];
		int profit = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < min_price) {
				min_price = prices[i];
			}

			if (prices[i] - min_price > profit) {
				profit = prices[i] - min_price;
			}
		}
		return profit;
	}
	
	public static void main(String[] args) {
		int[] prices = {1, 5, 3, 2, 6};
		System.out.println("Max Profit: " + maxProfit(prices));		
	}

	// beats 50.14%
}
