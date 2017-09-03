package com.leetcode.www;

public class ValidSudoku { // LC 36
    public boolean isValidSudoku(char[][] board) { // beats 60.24%
        int[][] table = new int[27][9];
        int[][] squareIndex = new int[][] {	{0, 1, 2}, {3, 4, 5}, {6, 7, 8}	};
        for( int i = 0; i < 9; i++ ) {
            for( int j = 0; j < 9; j++ ){
                if( board[i][j] == '.' )
                    continue;
                if( !Character.isDigit(board[i][j]) )
                    return false;
                int val = Character.getNumericValue(board[i][j]);
                if( table[i][val-1] != 0 )
                    return false;
                table[i][val-1] = 1;
                if( table[j+9][val-1] != 0 )
                    return false;
                table[j+9][val-1] = 1;
                int sNdx = squareIndex[i/3][j/3];
                if( table[sNdx+18][val-1] != 0 )
                    return false;
                table[sNdx+18][val-1] = 1;
            }
        }
        return true;
    }
}
