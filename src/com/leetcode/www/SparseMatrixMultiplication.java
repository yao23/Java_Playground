package com.leetcode.www;

public class SparseMatrixMultiplication { // LC 311
    public int[][] multiply(int[][] A, int[][] B) { // index out of bound exception
        int rowA = A.length;
        if (rowA == 0) {
            return new int[][]{};
        }
        int colA = A[0].length;
        int colB = B[0].length;
        int[][] res = new int[rowA][colB];
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colB; j++) {
                int sum = 0;
                for (int k = 0; k < colA; k++) {
                    sum += (A[i][j] * B[j][k]);
                }
                res[i][j] = sum;
            }
        }
        return res;
    }
}

// [[1,-5]], [[12],[-1]] => 17
