package com.leetcode.www;

public class SudokuSolver { // LC 37
    public void solveSudoku(char[][] board) { // beast 57.67%
        solveSudokuRecursive(board);
    }
    private boolean solveSudokuRecursive(char[][] board) {
        int[] pairs = getFirstEmpty(board);
        if (pairs[0] == -1 && pairs[1] == -1) {
            return true;
        }
        for (int i = 1; i <= 9; i++) {
            board[pairs[0]][pairs[1]] = (char)(i + '0');
            if (isValid(board, pairs[0], pairs[1]) && solveSudokuRecursive(board)) {
                return true;
            }
            board[pairs[0]][pairs[1]] = '.'; //backtrack
        }
        return false;
    }
    private int[] getFirstEmpty(char[][] board) {
        int[] pairs = null;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.' ) {
                    pairs = new int[]{i, j};
                    return pairs;
                }
            }
        }
        pairs = new int[]{-1, -1};
        return pairs;
    }
    private boolean isValid(char[][] board, int x, int y) {
        for (int i = 0; i < 9; i++) {
            if (i != y && board[x][i] == board[x][y]) { // col
                return false;
            }
            if (i != x && board[i][y] == board[x][y]) { // row
                return false;
            }
        }
        int xIdx = (x / 3) * 3; // offset in row index
        int yIdx = (y / 3) * 3; // offset in col index
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i + xIdx != x && j + yIdx != y && board[i + xIdx][j + yIdx] == board[x][y]) {
                    return false;    // nine grids have same one existed
                }
            }
        }
        return true;
    }
}
