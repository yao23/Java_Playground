package io.educative.www.DynamicProgramming.P03_FibonacciNumbers;

class P03_02_Staircase {

    /**
     * The time complexity of the above algorithm is exponential O(3^n) as we are making three recursive call in the
     * same function. The space complexity is O(n) which is used to store the recursion stack.
     *
     * @param n
     * @return
     */
    public int CountWays(int n) {
        if( n == 0)
            return 1; // base case, we don't need to take any step, so there is only one way

        if(n == 1)
            return 1; // we can take one step to reach the end, and that is the only way

        if(n == 2)
            return 2; // we can take one step twice or jump two steps to reach at the top

        // if we take 1 step, we are left with 'n-1' steps;
        int take1Step = CountWays(n-1);
        // similarly, if we took 2 steps, we are left with 'n-2' steps;
        int take2Step = CountWays(n-2);
        // if we took 3 steps, we are left with 'n-3' steps;
        int take3Step = CountWays(n-3);

        return take1Step + take2Step + take3Step;
    }

    /**
     * Top-down Dynamic Programming with Memoization
     *
     * @param n
     * @return
     */
    public int CountWaysV1(int n) {
        int dp[] = new int[n+1];
        return CountWaysRecursive(dp, n);
    }

    /**
     * Since our memoization array dp[n+1] stores the results for all the subproblems, we can conclude that we will not
     * have more than n+1 subproblems (where ‘n’ represents the total number of steps). This means that our time
     * complexity will be O(N). The space complexity will also be O(n); this space will be used to store the recursion-stack.
     * @param dp
     * @param n
     * @return
     */
    public int CountWaysRecursiveV1(int[] dp, int n) {
        if( n == 0)
            return 1; // base case, we don't need to take any step, so there is only one way

        if(n == 1)
            return 1; // we can take one step to reach the end, and that is the only way

        if(n == 2)
            return 2; // we can take one step twice or jump two steps to reach at the top

        if(dp[n] == 0) {
            // if we take 1 step, we are left with 'n-1' steps;
            int take1Step = CountWaysRecursive(dp, n-1);
            // similarly, if we took 2 steps, we are left with 'n-2' steps;
            int take2Step = CountWaysRecursive(dp, n-2);
            // if we took 3 steps, we are left with 'n-3' steps;
            int take3Step = CountWaysRecursive(dp, n-3);
            dp[n] = take1Step + take2Step + take3Step;
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
        dp[2] = 2;

        for(int i=3; i<=n; i++)
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];

        return dp[n];
    }

    /**
     * Memory optimization
     *
     * @param n
     * @return
     */
    public int CountWaysV3(int n) {
        if (n < 2) return 1;
        if (n == 2) return 2;
        int n1=1, n2=1, n3=2, temp;
        for(int i=3; i<=n; i++) {
            temp = n1 + n2 + n3;
            n1 = n2;
            n2 = n3;
            n3 = temp;
        }
        return n3;
    }

    public static void main(String[] args) {
        P03_02_Staircase sc = new P03_02_Staircase();
        System.out.println(sc.CountWays(3));
        System.out.println(sc.CountWays(4));
        System.out.println(sc.CountWays(5));
    }
}
