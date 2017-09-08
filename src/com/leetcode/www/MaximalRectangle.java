package com.leetcode.www;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int r = matrix.length;
        if( r == 0 )	return 0;
        int c = matrix[0].length;
        int[][] dp = new int[r][c];
        for( int i = 0; i < r; i++ ) {
            for( int j = 0; j < c; j++ ) {
                if( matrix[i][j] != '0' ) {
                    dp[i][j] = 1;
                    int k = j + 1;

                    while( k < c && matrix[i][k] > '0' ) {
                        dp[i][j] += 1;
                        k++;
                    }
                }
            }
        }
        int MaxArea = 0;
        for( int i = 0; i < r; i++ ) {
            for( int j = 0; j < c; j++ ) {
                if( matrix[i][j] != '0' ) {
                    int MinWidth = dp[i][j];
                    for( int k = i; k < r && dp[k][j] > 0; k++ ) {
                        MinWidth = Math.min(MinWidth, dp[k][j]);
                        int area = MinWidth * (k - i + 1);
                        if( area > MaxArea )
                            MaxArea  = area;
                    }
                }
            }
        }
        return MaxArea;
    }
}
