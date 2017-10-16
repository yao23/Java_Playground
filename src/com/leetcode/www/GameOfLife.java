package com.leetcode.www;

public class GameOfLife { // LC 289
    // use the 1st bit to represent next generation
    // use the 2nd bit to present current generation
    public void gameOfLife(int[][] board) { // beats 83.10%
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int neighbors = getNeighbour(board, i, j);
                if (board[i][j] == 1) {
                    if (neighbors == 2 || neighbors == 3) {
                        board[i][j] = 3;
                    }
                } else {
                    if (neighbors == 3) {
                        board[i][j] = 2;
                    }
                }
            }
        }
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                board[i][j] >>= 1;
            }
        }
    }

    private int getNeighbour(int[][] board, int row, int col){
        int cnt = 0;
        for (int i = row - 1; i <= row + 1; ++i) {
            for (int j = col - 1; j <= col + 1; ++j) {
                if (i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
                    cnt += board[i][j] & 1;
                }
            }
        }
        cnt -= board[row][col] & 1;
        return cnt;
    }

    public void gameOfLifeV0(int[][] board) { // beats 11.75%
        if (board == null || board.length == 0) {
            return;
        }
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = liveNeighbors(board, m, n, i, j);

                // In the beginning, every 2nd bit is 0;
                // So we only need to care about when will the 2nd bit become 1.
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;  // Get the 2nd state.
            }
        }
    }

    private int liveNeighbors(int[][] board, int m, int n, int i, int j) {
        int lives = 0;
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                lives += board[x][y] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }
}
