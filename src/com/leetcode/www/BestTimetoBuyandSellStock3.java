package com.leetcode.www;

public class BestTimetoBuyandSellStock3 { // LC 123
	public static int maxProfit(int[] prices) {
		if (prices.length == 0) {
			return 0;
		}
		int profit = 0;
		int[] profits = new int[prices.length];
		int MaxUntilNow = Integer.MIN_VALUE;
		int MinPrice = Integer.MAX_VALUE;
		int MaxPrice = Integer.MIN_VALUE;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < MinPrice) {
				MinPrice = prices[i];
			}
			if (prices[i] - MinPrice > MaxUntilNow) {
				MaxUntilNow = prices[i] - MinPrice;
			}
			profits[i] = MaxUntilNow;
		}	
		
		for (int i = 0; i < prices.length; i++) {
			System.out.print(profits[i] + " ");
		}
		
		System.out.println();
		
		for (int i = prices.length-1; i >= 0; i--) {
			if (prices[i] > MaxPrice) {
				MaxPrice = prices[i];
			}
			if (MaxPrice - prices[i] + profits[i] > profit) {
				profit = MaxPrice - prices[i] + profits[i];
			}
		}
		return profit;
	}
	
	public static void main(String[] args) {
		int[] prices = {1, 5, 3, 2, 6};
		System.out.println("Max Profit: " + maxProfit(prices));		
	}

	// beats 98.87%
}
