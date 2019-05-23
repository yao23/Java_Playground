package com.leetcode.www;

public class BattleshipsInABoard { // LC 419
    /**
     * Runtime: 1 ms, faster than 98.22% of Java online submissions for Battleships in a Board.
     * Memory Usage: 40 MB, less than 90.64% of Java online submissions for Battleships in a Board.
     *
     * https://leetcode.com/problems/battleships-in-a-board/discuss/297094/Java-single-pass-O(1)-memory-1ms-execution-time
     *
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        int count = 0;

        boolean in = false;

        // scan first row
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'X') { // separate with battleships in previous column
                if (!in) {
                    in = true;
                    count++;
                }
            } else {
                in = false;
            }
        }

        // scan the rest
        for (int i = 1; i < board.length; i++) {
            in = false;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X' && board[i-1][j] != 'X') { // separate with battleships in previous row
                    if (!in) {
                        in = true;
                        count++;
                    }
                } else {
                    in = false;
                }

            }
        }

        return count;
    }

    /**
     * Runtime: 1 ms, faster than 98.22% of Java online submissions for Battleships in a Board.
     * Memory Usage: 40.6 MB, less than 89.47% of Java online submissions for Battleships in a Board.
     *
     * https://leetcode.com/problems/battleships-in-a-board/discuss/296699/Java-Single-Pass-Constant-Space-Solution
     *
     * @param board
     * @return
     */
    public int countBattleshipsV0(char[][] board) {
        int count = 0;
        for (int r = 0; r < board.length; r++) {
            boolean found = false;
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c]=='X' && !found && (r == 0 || board[r-1][c] != 'X')) {
                    found = true;
                    count++;
                } else if (board[r][c]=='.' && found) {
                    found = false;
                }
            }
        }
        return count;
    }
}
