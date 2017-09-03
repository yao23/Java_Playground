package com.leetcode.www;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku { // LC 36
    public boolean isValidSudoku(char[][] board) { // beats 60.24%
        int[][] table = new int[27][9];
        int[][] squareIndex = new int[][] {	{0, 1, 2}, {3, 4, 5}, {6, 7, 8}	};
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                } if (!Character.isDigit(board[i][j])) {
                    return false;
                }
                int val = Character.getNumericValue(board[i][j]);
                if (table[i][val - 1] != 0) {
                    return false;
                }
                table[i][val - 1] = 1;
                if (table[j + 9][val - 1] != 0) {
                    return false;
                }
                table[j + 9][val - 1] = 1;
                int sNdx = squareIndex[i / 3][j / 3];
                if (table[sNdx + 18][val - 1] != 0) {
                    return false;
                }
                table[sNdx + 18][val - 1] = 1;
            }
        }
        return true;
    }

    /**
     * https://discuss.leetcode.com/topic/27436/short-simple-java-using-strings
     *
     * @param board
     * @return
     */
    public boolean isValidSudokuV0(char[][] board) { // beats 50.66%
        Set seen = new HashSet();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char number = board[i][j];
                if (number != '.') {
                    if (!seen.add(number + " in row " + i) || !seen.add(number + " in column " + j) ||
                            !seen.add(number + " in block " + i / 3 + "-" + j / 3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
