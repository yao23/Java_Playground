package com.leetcode.www;

public class FibonacciNumber { // LC 509
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Fibonacci Number.
     * Memory Usage: 31.8 MB, less than 65.65% of Java online submissions for Fibonacci Number.
     *
     * @param N
     * @return
     */
    public int fib(int N) { // iterative
        if (N == 0 || N == 1) {
            return N;
        }
        int[] result = new int[N];
        result[0] = 0;
        result[1] = 1;
        for (int i = 2; i < N; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[N - 1] + result[N - 2];
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Fibonacci Number.
     * Memory Usage: 31.8 MB, less than 65.25% of Java online submissions for Fibonacci Number.
     *
     * @param N
     * @return
     */
    public int fibV1(int N) {
        if (N == 0 || N == 1) {
            return N;
        }
        int f1 = 0;
        int f2 = 1;
        int sum = 0;
        for(int i = 2; i <= N; i++){
            sum = f1 + f2;
            f1 = f2;
            f2 = sum;
        }
        return sum;
    }

    /**
     * Runtime: 9 ms, faster than 30.99% of Java online submissions for Fibonacci Number.
     * Memory Usage: 32 MB, less than 60.05% of Java online submissions for Fibonacci Number.
     *
     * @param N
     * @return
     */
    public int fibV0(int N) { // recursive
        if (N == 0) {
            return 0;
        } else if (N == 1) {
            return 1;
        } else {
            return fib(N - 1) + fib(N - 2);
        }
    }
}
