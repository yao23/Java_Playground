package io.educative.www.DynamicProgramming.P02_UnboundedKnapsack;

class P02_04_MinimumCoinChange {

    public int countChange(int[] denominations, int total) {
        int result = this.countChangeRecursive(denominations, total, 0);
        return (result == Integer.MAX_VALUE ? -1 : result);
    }

    /**
     * The time complexity of the above algorithm is exponential O(2^{C+T}), where ‘C’ represents total coin
     * denominations and ‘T’ is the total amount that we want to make change. The space complexity will be O(C+T).
     * @param denominations
     * @param total
     * @param currentIndex
     * @return
     */
    private int countChangeRecursive(int[] denominations, int total, int currentIndex) {
        // base check
        if (total == 0)
            return 0;

        if(denominations.length == 0 || currentIndex >= denominations.length)
            return Integer.MAX_VALUE;

        // recursive call after selecting the coin at the currentIndex
        // if the coin at currentIndex exceeds the total, we shouldn't process this
        int count1 = Integer.MAX_VALUE;
        if( denominations[currentIndex] <= total ) {
            int res = countChangeRecursive(denominations, total - denominations[currentIndex], currentIndex);
            if(res != Integer.MAX_VALUE){
                count1 = res + 1;
            }
        }

        // recursive call after excluding the coin at the currentIndex
        int count2 = countChangeRecursive(denominations, total, currentIndex + 1);

        return Math.min(count1, count2);
    }

    public int countChangeV1(int[] denominations, int total) {
        Integer[][] dp = new Integer[denominations.length][total + 1];
        int result = this.countChangeRecursive(dp, denominations, total, 0);
        return (result == Integer.MAX_VALUE ? -1 : result);
    }

    /**
     * Top-down Dynamic Programming with Memoization
     * @param dp
     * @param denominations
     * @param total
     * @param currentIndex
     * @return
     */
    private int countChangeRecursiveV1(Integer[][] dp, int[] denominations, int total, int currentIndex) {
        // base check
        if (total == 0)
            return 0;

        if(denominations.length == 0 || currentIndex >= denominations.length)
            return Integer.MAX_VALUE;

        // check if we have not already processed a similar sub-problem
        if(dp[currentIndex][total] == null) {
            // recursive call after selecting the coin at the currentIndex
            // if the coin at currentIndex exceeds the total, we shouldn't process this
            int count1 = Integer.MAX_VALUE;
            if( denominations[currentIndex] <= total ) {
                int res = countChangeRecursive(dp, denominations, total - denominations[currentIndex], currentIndex);
                if(res != Integer.MAX_VALUE){
                    count1 = res + 1;
                }
            }

            // recursive call after excluding the coin at the currentIndex
            int count2 = countChangeRecursive(dp, denominations, total, currentIndex + 1);
            dp[currentIndex][total] = Math.min(count1, count2);
        }
        return dp[currentIndex][total];
    }

    /**
     * Bottom-up Dynamic Programming
     *
     * time and space complexity of O(C*T), where ‘C’ represents total coin denominations and ‘T’ is the total amount
     * that we want to make change.
     *
     * @param denominations
     * @param total
     * @return
     */
    public int countChange(int[] denominations, int total) {
        int n = denominations.length;
        int[][] dp = new int[n][total + 1];

        for(int i=0; i < n; i++)
            for(int j=0; j <= total; j++)
                dp[i][j] = Integer.MAX_VALUE;

        // populate the total=0 columns, as we don't need any coin to make zero total
        for(int i=0; i < n; i++)
            dp[i][0] = 0;

        for(int i=0; i < n; i++) {
            for(int t=1; t <= total; t++) {
                if(i > 0)
                    dp[i][t] = dp[i-1][t]; //exclude the coin
                if(t >= denominations[i]) {
                    if(dp[i][t-denominations[i]] != Integer.MAX_VALUE)
                        dp[i][t] = Math.min(dp[i][t], dp[i][t-denominations[i]]+1); // include the coin
                }
            }
        }

        // total combinations will be at the bottom-right corner.
        return (dp[n-1][total] == Integer.MAX_VALUE ? -1 : dp[n-1][total]);
    }

    public static void main(String[] args) {
        P02_04_MinimumCoinChange cc = new P02_04_MinimumCoinChange();
        int[] denominations = {1, 2, 3};
        System.out.println(cc.countChange(denominations, 5));
        System.out.println(cc.countChange(denominations, 11));
        System.out.println(cc.countChange(denominations, 7));
        denominations = new int[]{3, 5};
        System.out.println(cc.countChange(denominations, 7));
    }
}