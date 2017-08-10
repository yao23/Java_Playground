package com.leetcode.www;

public class LongestIncreasingPathInAMatrix { // LC 329
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] maxPath = new int[row][col];

        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                max = Math.max(max, helper(i, j, maxPath, matrix, row, col));
            }
        }

        return max;
    }

    private int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {-1, 0}};

    private int helper(int x, int y, int[][] maxP, int[][] m, int row, int col) {
        if (maxP[x][y] != 0) {
            return maxP[x][y];
        }

        int max = 1;
        for (int[] d : dir) {
            int nextX = x + d[0];
            int nextY = y + d[1];
            if (isValid(nextX, nextY, row, col) && m[nextX][nextY] > m[x][y]) {
                max = Math.max(helper(nextX, nextY, maxP, m, row, col) + 1, max);
            }
        }
        maxP[x][y] = max;
        return max;
    }

    private boolean isValid(int i, int j, int row, int col) {
        return i >= 0 && j >= 0 && i < row && j < col;
    }
}

// nums = [
//  [9,9,4],
//  [6,6,8],
//  [2,1,1]
// ]
// => 4 ([1, 2, 6, 9])

// nums = [
//  [3,4,5],
//  [3,2,6],
//  [2,2,1]
// ]
// => 4 ([3, 4, 5, 6])