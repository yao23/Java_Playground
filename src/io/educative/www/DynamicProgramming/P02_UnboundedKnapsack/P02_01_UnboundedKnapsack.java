package io.educative.www.DynamicProgramming.P02_UnboundedKnapsack;

public class P02_01_UnboundedKnapsack {
    /**
     * Time and Space complexity
     * The time complexity of the above algorithm is exponential O(2^{N+C}), where ‘N’ represents the total number of
     * items. The space complexity will be O(N+C) to store the recursion stack.
     *
     * @param profits
     * @param weights
     * @param capacity
     * @return
     */
    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        return this.knapsackRecursive(profits, weights, capacity, 0);
    }

    private int knapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
        // base checks
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length ||
                currentIndex >= profits.length)
            return 0;

        // recursive call after choosing the items at the currentIndex, note that we recursive call on all
        // items as we did not increment currentIndex
        int profit1 = 0;
        if (weights[currentIndex] <= capacity)
            profit1 = profits[currentIndex]
                    + knapsackRecursive(profits, weights, capacity - weights[currentIndex], currentIndex);

        // recursive call after excluding the element at the currentIndex
        int profit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);

        return Math.max(profit1, profit2);
    }

    /**
     * Top-down Dynamic Programming with Memoization
     *
     * Since our memoization array dp[profits.length][capacity+1] stores the results for all the subproblems, we can
     * conclude that we will not have more than N*C subproblems (where ‘N’ is the number of items and ‘C’ is the
     * knapsack capacity). This means that our time complexity will be O(N*C).
     *
     * The above algorithm will be using O(N*C) space for the memoization array. Other than that we will use O(N) space
     * for the recursion call-stack. So the total space complexity will be O(N*C + N), which is asymptotically
     * equivalent to O(N*C).
     *
     * @param dp
     * @param profits
     * @param weights
     * @param capacity
     * @param currentIndex
     * @return
     */
    private int knapsackRecursiveV2(Integer[][] dp, int[] profits, int[] weights, int capacity,
                                  int currentIndex) {

        // base checks
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length ||
                currentIndex >= profits.length)
            return 0;

        // check if we have not already processed a similar sub-problem
        if(dp[currentIndex][capacity] == null) {
            // recursive call after choosing the items at the currentIndex, note that we recursive call on all
            // items as we did not increment currentIndex
            int profit1 = 0;
            if( weights[currentIndex] <= capacity )
                profit1 = profits[currentIndex] + knapsackRecursive(dp, profits, weights,
                        capacity - weights[currentIndex], currentIndex);

            // recursive call after excluding the element at the currentIndex
            int profit2 = knapsackRecursive(dp, profits, weights, capacity, currentIndex + 1);

            dp[currentIndex][capacity] = Math.max(profit1, profit2);
        }

        return dp[currentIndex][capacity];
    }

    /**
     * Bottom-up Dynamic Programming
     *
     * This means that dp[i][c] will represent the maximum knapsack profit for capacity ‘c’ calculated from the first
     * ‘i’ items.
     *
     * Time and Space complexity
     * The above solution has time and space complexity of O(N*C), where ‘N’ represents total items and ‘C’ is the
     * maximum capacity.
     *
     * @param profits
     * @param weights
     * @param capacity
     * @return
     */
    public int solveKnapsackV2(int[] profits, int[] weights, int capacity) {
        // base checks
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;

        int n = profits.length;
        int[][] dp = new int[n][capacity + 1];

        // populate the capacity=0 columns
        for(int i=0; i < n; i++)
            dp[i][0] = 0;

        // process all sub-arrays for all capacities
        for(int i=0; i < n; i++) {
            for(int c=1; c <= capacity; c++) {
                int profit1=0, profit2=0;
                if(weights[i] <= c)
                    profit1 = profits[i] + dp[i][c-weights[i]];
                if( i > 0 )
                    profit2 = dp[i-1][c];
                dp[i][c] = profit1 > profit2 ? profit1 : profit2;
            }
        }

        printSelectedElements(dp, weights, profits, capacity);

        // maximum profit will be in the bottom-right corner.
        return dp[n-1][capacity];
    }

    private void printSelectedElements(int dp[][], int[] weights, int[] profits, int capacity){
        System.out.print("Selected weights:");
        int totalProfit = dp[weights.length-1][capacity];
        for(int i=weights.length-1; i > 0; i--) {
            if(totalProfit != dp[i-1][capacity]) {
                System.out.print(" " + weights[i]);
                capacity -= weights[i];
                totalProfit -= profits[i];
            }
        }

        if(totalProfit != 0)
            System.out.print(" " + weights[0]);
        System.out.println("");
    }

    /**
     * If you see closely, we need two values from the previous iteration: dp[c] and dp[c-weight[i]]
     *
     * Since our inner loop is iterating over c:0-->capacity, let’s see how this might affect our two required values:
     *
     * When we access dp[c], it has not been overridden yet for the current iteration, so it should be fine.
     * dp[c-weight[i]] might be overridden if “weight[i] > 0”. Therefore we can’t use this value for the current iteration.
     * To solve the second case, we can change our inner loop to process in the reverse direction: c:capacity-->0.
     * This will ensure that whenever we change a value in dp[], we will not need it anymore in the current iteration.
     *
     * @param profits
     * @param weights
     * @param capacity
     * @return
     */
    static int solveKnapsackV3(int[] profits, int[] weights, int capacity) {
        // basic checks
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;

        int n = profits.length;
        int[] dp = new int[capacity + 1];

        // if we have only one weight, we will take it if it is not more than the
        // capacity
        for (int c = 0; c <= capacity; c++) {
            if (weights[0] <= c)
                dp[c] = profits[0];
        }

        // process all sub-arrays for all the capacities
        for (int i = 1; i < n; i++) {
            for (int c = capacity; c >= 0; c--) {
                int profit1 = 0, profit2 = 0;
                // include the item, if it is not more than the capacity
                if (weights[i] <= c)
                    profit1 = profits[i] + dp[c - weights[i]];
                // exclude the item
                profit2 = dp[c];
                // take maximum
                dp[c] = Math.max(profit1, profit2);
            }
        }

        return dp[capacity];
    }

    public static void main(String[] args) {
        P02_01_UnboundedKnapsack ks = new P02_01_UnboundedKnapsack();
        int[] profits = {15, 50, 60, 90};
        int[] weights = {1, 3, 4, 5};
        System.out.println(ks.solveKnapsack(profits, weights, 8));
        System.out.println(ks.solveKnapsack(profits, weights, 6));
    }
}
