package com.leetcode.www;

public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        SolveSudokuRecursive(board);
    }
    private boolean SolveSudokuRecursive(char[][] board) {
        int[] pairs = GetFirstEmpty(board);
        if( pairs[0] == -1 && pairs[1] == -1 )
            return true;
        for( int i = 1; i <= 9; i++ ) {
            board[pairs[0]][pairs[1]] = (char)(i + '0');
            if( IsValid(board, pairs[0], pairs[1]) &&
                    SolveSudokuRecursive(board) )
                return true;
            board[pairs[0]][pairs[1]] = '.'; //backtrack
        }
        return false;
    }
    private int[] GetFirstEmpty(char[][] board) {
        int[] pairs = null;
        for( int i = 0; i < 9; i++ ) {
            for( int j = 0; j < 9; j++ ) {
                if( board[i][j] == '.' ) {
                    pairs = new int[]{i, j};
                    return pairs;
                }
            }
        }
        pairs = new int[]{-1, -1};
        return pairs;
    }
    private boolean IsValid(char[][] board, int x, int y) {
        for( int i = 0; i < 9; i++ ) {
            if( i != y && board[x][i] == board[x][y] )
                return false;
            if( i != x && board[i][y] == board[x][y] )
                return false;
        }
        int XIdx = (x / 3) * 3; // offset in row index
        int YIdx = (y / 3) * 3; // offset in col index
        for( int i = 0; i < 3; i++ ) {
            for( int j = 0; j < 3; j++ ) {
                if(i + XIdx != x && j + YIdx != y &&
                        board[i + XIdx][j + YIdx] == board[x][y] )
                    return false;	// nine grids have same one existed
            }
        }
        return true;
    }
}
