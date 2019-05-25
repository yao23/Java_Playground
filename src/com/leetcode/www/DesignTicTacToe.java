package com.leetcode.www;

public class DesignTicTacToe { // LC 348
    class TicTacToe {
        /**
         * Runtime: 47 ms, faster than 74.49% of Java online submissions for Design Tic-Tac-Toe.
         * Memory Usage: 46.5 MB, less than 38.43% of Java online submissions for Design Tic-Tac-Toe.
         *
         * https://leetcode.com/problems/design-tic-tac-toe/discuss/298802/Java-O(1)-Easy-to-understand-Solution-46-ms
         */
        int r[][];
        int c[][];
        int d[][];
        int n;
        /** Initialize your data structure here. */
        public TicTacToe(int n) {
            this.n = n;
            r = new int[n][2];
            c = new int[n][2];
            d = new int[2][2];
        }

        /** Player {player} makes a move at ({row}, {col}).
         @param row The row of the board.
         @param col The column of the board.
         @param player The player, can be either 1 or 2.
         @return The current winning condition, can be either:
         0: No one wins.
         1: Player 1 wins.
         2: Player 2 wins. */
        public int move(int row, int col, int player) {
            int p = player - 1;
            r[row][p]++;
            c[col][p]++;
            if (row == col) { // diagonal: [0,0], [1,1], [2,2]
                d[0][p]++;
            }
            if (row + col == n-1) { // diagonal: [0,2], [1,1], [2,0]
                d[1][p]++;
            }
            if (r[row][p] == n || c[col][p] == n || d[0][p] == n || d[1][p] == n) {
                return player;
            }
            return 0;
        }
    }

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
}
