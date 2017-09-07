package com.leetcode.www;

public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        if( r == 0 || c == 0 ) return;
        boolean FirstRowZero = false, FirstColZero = false;
        for( int i = 0; i < r; i++ ) {
            if( matrix[i][0] == 0 ) {
                FirstColZero = true;
                break;
            }
        }
        for( int i = 0; i < c; i++ ) {
            if( matrix[0][i] == 0 ) {
                FirstRowZero = true;
                break;
            }
        }
        for( int i = 1; i < r; i++ ) {
            for( int j = 1; j < c; j++ ) {
                if( matrix[i][j] == 0 ) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for( int i = 1; i < r; i++ ) {
            for( int j = 1; j < c; j++ ) {
                if( matrix[i][0] == 0 || matrix[0][j] == 0 ) {
                    matrix[i][j] = 0;
                }
            }
        }
        if( FirstRowZero ) {
            for( int i = 0; i < c; i++ )
                matrix[0][i] = 0;
        }
        if( FirstColZero ) {
            for( int i = 0; i < r; i++ )
                matrix[i][0] = 0;
        }
    }
}

// [[0]] => [[0]]