package com.leetcode.www;

public class SpiralMatrixII { // LC 59
    public int[][] generateMatrix(int n) { // beats 46.64%
        if (n <= 0) {
            return new int[0][];
        }
        int row = n, col = n, count = 1, start = 0;
        int[][] result = new int[n][n];
        while (start * 2 < row && start * 2 < col) {
            count = generateMatrix(row, col, start, count, n * n, result);
            start++;
        }
        return result;
    }

    private static int generateMatrix(int row, int col, int start, int count, int n, int[][] result) {
        int endX = col - 1 - start;
        int endY = row - 1 - start;
        for (int i = start; i <= endX && count <= n; i++) {
            result[start][i] = count++;
        }
        if (start < endY) {
            for (int i = start + 1; i <= endY && count <= n; i++) {
                result[i][endX] = count++;
            }
        }
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start && count <= n; i--) {
                result[endY][i] = count++;
            }
        }
        if (start < endX && start < endY - 1) {
            for (int i = endY - 1; i > start && count <= n; i--) {
                result[i][start] = count++;
            }
        }
        return count;
    }
}
