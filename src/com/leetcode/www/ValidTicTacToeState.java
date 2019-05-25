package com.leetcode.www;

public class ValidTicTacToeState { // LC 794
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Valid Tic-Tac-Toe State.
     * Memory Usage: 33.1 MB, less than 99.89% of Java online submissions for Valid Tic-Tac-Toe State.
     *
     * https://leetcode.com/problems/valid-tic-tac-toe-state/discuss/291681/Without-recursion-Beats-100-Java-0ms-runtime
     *
     * @param board
     * @return
     */
    public boolean validTicTacToe(String[] board) {

        int i, j, countX = 0, countO = 0, flagXWin = 0, flagOWin = 0;
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[i].length(); j++) {
                if(board[i].charAt(j)=='X'){
                    ++countX;
                }
                if(board[i].charAt(j)=='O'){
                    ++countO;
                }
            }
        }

        if (countX < countO) {
            return false;
        }

        // win in row
        if (board[0].equals("XXX")) {
            ++flagXWin;
        }
        if (board[1].equals("XXX")) {
            ++flagXWin;
        }
        if (board[2].equals("XXX")) {
            ++flagXWin;
        }
        if (board[0].equals("OOO")) {
            ++flagOWin;
        }
        if (board[1].equals("OOO")) {
            ++flagOWin;
        }
        if (board[2].equals("OOO")) {
            ++flagOWin;
        }
        // win in col
        if (board[0].charAt(0)=='X' && board[1].charAt(0)=='X' && board[2].charAt(0)=='X') {
            ++flagXWin;
        }
        if (board[0].charAt(1)=='X' && board[1].charAt(1)=='X' && board[2].charAt(1)=='X') {
            ++flagXWin;
        }
        if (board[0].charAt(2)=='X' && board[1].charAt(2)=='X' && board[2].charAt(2)=='X') {
            ++flagXWin;
        }
        if (board[0].charAt(0)=='O' && board[1].charAt(0)=='O' && board[2].charAt(0)=='O') {
            ++flagOWin;
        }
        if (board[0].charAt(1)=='O'  && board[1].charAt(1)=='O' && board[2].charAt(1)=='O') {
            ++flagOWin;
        }
        if (board[0].charAt(2)=='O'  && board[1].charAt(2)=='O' && board[2].charAt(2)=='O') {
            ++flagOWin;
        }
        // win in diagonal
        if (board[0].charAt(0)=='X' && board[1].charAt(1)=='X' && board[2].charAt(2)=='X') {
            ++flagXWin;
        }
        if (board[0].charAt(2)=='X' && board[1].charAt(1)=='X' && board[2].charAt(0)=='X') {
            ++flagXWin;
        }
        if (board[0].charAt(0)=='O' && board[1].charAt(1)=='O' && board[2].charAt(2)=='O') {
            ++flagOWin;
        }
        if (board[0].charAt(2)=='O' && board[1].charAt(1)=='O' && board[2].charAt(0)=='O') {
            ++flagOWin;
        }

        if (countX == countO + 1 && ((flagXWin > 0 && flagOWin == 0) || (flagXWin == 0 && flagOWin == 0))) { // X win
            return true;
        }
        if (countX == countO && ((flagOWin > 0 && flagXWin == 0) || (flagXWin == 0 && flagOWin == 0))) { // O win
            return true;
        }

        return false;
    }
}
