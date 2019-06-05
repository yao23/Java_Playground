package com.leetcode.www;

public class SquaresOfASortedArray { // LC 977
    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Squares of a Sorted Array.
     * Memory Usage: 40.8 MB, less than 97.07% of Java online submissions for Squares of a Sorted Array.
     *
     * @param A which is an int array
     * @return
     */
    public int[] sortedSquares(int[] A) {
        int[] b = new int[A.length];
        int begin = 0;
        int end = A.length - 1;
        for (int i = A.length - 1; i >= 0; i--) { // largest number is more certain than smaller one
            b[i] = A[begin] * A[begin] > A[end] * A[end] ? A[begin] * A[begin++] : A[end] * A[end--];
        }
        return b;
    }
}
