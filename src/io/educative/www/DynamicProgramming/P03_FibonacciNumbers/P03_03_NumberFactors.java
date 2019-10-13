package io.educative.www.DynamicProgramming.P03_FibonacciNumbers;

class P03_03_NumberFactors {
    /**
     * The time complexity of the above algorithm is exponential O(3^n). The space complexity is O(n) which is used to
     * store the recursion stack.
     *
     * @param n
     * @return
     */
    public int CountWays(int n) {
        if( n == 0)
            return 1; // base case, we don't need to subtract any thing, so there is only one way

        if(n == 1)
            return 1; // we can subtract 1 to be left with zero, and that is the only way

        if(n == 2)
            return 1; // we can subtract 1 twice to get zero and that is the only way

        if(n == 3)
            return 2; // '3' can be expressed as {1,1,1}, {3}

        // if we subtract 1, we are left with 'n-1'
        int subtract1 = CountWays(n-1);
        // if we subtract 3, we are left with 'n-3'
        int subtract3 = CountWays(n-3);
        // if we subtract 4, we are left with 'n-4'
        int subtract4 = CountWays(n-4);

        return subtract1 + subtract3 + subtract4;
    }

    /**
     * Top-down Dynamic Programming with Memoization
     *
     * @param n
     * @return
     */
    public int CountWaysV1(int n) {
        int dp[] = new int[n + 1];
        return CountWaysRecursiveV1(dp, n);
    }

    public int CountWaysRecursiveV1(int[] dp, int n) {
        if (n == 0)
            return 1; // base case, we don't need to subtract any thing, so there is only one way

        if (n == 1)
            return 1; // we can take subtract 1 to be left with zero, and that is the only way

        if (n == 2)
            return 1; // we can subtract 1 twice to get zero and that is the only way

        if (n == 3)
            return 2; // '3' can be expressed as {1,1,1}, {3}

        if (dp[n] == 0) {
            // if we subtract 1, we are left with 'n-1'
            int subtract1 = CountWaysRecursive(dp, n - 1);
            // if we subtract 3, we are left with 'n-3'
            int subtract3 = CountWaysRecursive(dp, n - 3);
            // if we subtract 4, we are left with 'n-4'
            int subtract4 = CountWaysRecursive(dp, n - 4);
            dp[n] = subtract1 + subtract3 + subtract4;
        }

        return dp[n];
    }

    /**
     * Bottom-up Dynamic Programming
     *
     * time and space complexity of O(n)
     *
     * @param n
     * @return
     */
    public int CountWaysV2(int n) {
        int dp[] = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;

        for(int i=4; i<=n; i++)
            dp[i] = dp[i-1] + dp[i-3] + dp[i-4];

        return dp[n];
    }

    public static void main(String[] args) {
        P03_03_NumberFactors en = new P03_03_NumberFactors();
        System.out.println(en.CountWays(4));
        System.out.println(en.CountWays(5));
        System.out.println(en.CountWays(6));
    }
}
