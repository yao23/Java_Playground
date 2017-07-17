
public class BestTimetoBuyandSellStock2 {
	public static int maxProfit(int[] prices) {
		if (prices.length == 0) {
			return 0;
		}
		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i - 1] < prices[i]) {
				profit += (prices[i] - prices[i - 1]);
			}
		}
		return profit;
	}
	
	public static void main(String[] args) {
		int[] prices = {1, 5, 3, 2, 6};
		System.out.println("Max Profit: " + maxProfit(prices));		
	}

	// beats 61.89%
}
