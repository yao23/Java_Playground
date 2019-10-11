package io.educative.www.DynamicProgramming.P03_FibonacciNumbers;

class P03_01_FibonacciNumbers {
    /**
     * The time complexity of the above algorithm is exponential O(2^n) as we are making two recursive calls in the
     * same function. The space complexity is O(n) which is used to store the recursion stack.
     * @param n
     * @return
     */
    public int CalculateFibonacci(int n) {
        if(n < 2)
            return n;
        return CalculateFibonacci(n-1) + CalculateFibonacci(n-2);
    }

    /**
     * Top-down Dynamic Programming with Memoization
     *
     * @param n
     * @return
     */
    public int CalculateFibonacciV1(int n) {
        int dp[] = new int[n + 1];
        return CalculateFibonacciRecursive(dp, n);
    }

    public int CalculateFibonacciRecursiveV1(int[] dp, int n) {
        if (n < 2)
            return n;
        if (dp[n] == 0)
            dp[n] = CalculateFibonacciRecursive(dp, n - 1) + CalculateFibonacciRecursive(dp, n - 2);
        return dp[n];
    }

    /**
     * Bottom-up Dynamic Programming
     *
     * time and space complexity O(n)
     *
     * @param n
     * @return
     */
    public int CalculateFibonacciV2(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    /**
     * Memory optimization
     *
     * time complexity of O(n) but a constant space complexity O(1)
     *
     * @param n
     * @return
     */
    public int CalculateFibonacciV3(int n) {
        if (n < 2)
            return n;
        int n1 = 0, n2 = 1, temp;
        for (int i = 2; i <= n; i++) {
            temp = n1 + n2;
            n1 = n2;
            n2 = temp;
        }
        return n2;
    }

    public static void main(String[] args) {
        P03_01_FibonacciNumbers fib = new P03_01_FibonacciNumbers();
        System.out.println("5th Fibonacci is ---> " + fib.CalculateFibonacci(5));
        System.out.println("6th Fibonacci is ---> " + fib.CalculateFibonacci(6));
        System.out.println("7th Fibonacci is ---> " + fib.CalculateFibonacci(7));
    }
}
