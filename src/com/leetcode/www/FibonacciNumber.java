package com.leetcode.www;

public class FibonacciNumber { // LC 509
    /**
     * Runtime: 9 ms, faster than 30.99% of Java online submissions for Fibonacci Number.
     * Memory Usage: 32 MB, less than 60.05% of Java online submissions for Fibonacci Number.
     *
     * @param N
     * @return
     */
    public int fib(int N) {
        if (N == 0) {
            return 0;
        } else if (N == 1) {
            return 1;
        } else {
            return fib(N - 1) + fib(N - 2);
        }
    }
}
