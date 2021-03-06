package com.leetcode.www;

public class MaximalRectangle { // LC 85
    /**
     *
     * @param matrix
     * @return
     *
     *
     * https://discuss.leetcode.com/topic/6650/share-my-dp-solution
     * 
     * The DP solution proceeds row by row, starting from the first row. Let the maximal rectangle area at row i and
     * column j be computed by [right(i,j) - left(i,j)]*height(i,j).

     * All the 3 variables left, right, and height can be determined by the information from previous row,
     * and also information from the current row. So it can be regarded as a DP solution. The transition equations are:

     * left(i,j) = max(left(i-1,j), cur_left), cur_left can be determined from the current row
     * right(i,j) = min(right(i-1,j), cur_right), cur_right can be determined from the current row
     * height(i,j) = height(i-1,j) + 1, if matrix[i][j]=='1';
     * height(i,j) = 0, if matrix[i][j]=='0'
     * The code is as below. The loops can be combined for speed but I separate them for more clarity of the algorithm.
     */
    public int maximalRectangle(char[][] matrix) { // beats 75.25%
        if (matrix.length == 0) {
            return 0;
        }
        int n = matrix[0].length, maxA = 0;
        int[][] dp = new int[n][3]; //[i, 0] is left; [i, 1] is right; [i, 2] is height
        for (int i = 0; i < n; i++) {
            dp[i][1] = n;
        }
        for (int i = 0; i < matrix.length; i++) {
            int cur_left = 0, cur_right = n;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') { // compute height (can do this from either side)
                    dp[j][2]++;
                } else {
                    dp[j][2]=0; }
                if (matrix[i][j] == '1') { // compute left (from left to right)
                    dp[j][0] = Math.max(dp[j][0], cur_left);
                } else {
                    dp[j][0] = 0;
                    cur_left = j + 1;
                }
            }
            // compute right (from right to left)
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    dp[j][1] = Math.min(dp[j][1], cur_right); }
                else {
                    dp[j][1] = n;
                    cur_right = j;
                }
            }
            // compute the area of rectangle (can do this from either side)
            for (int j = 0; j < n; j++) {
                maxA = Math.max(maxA, (dp[j][1] - dp[j][0]) * dp[j][2]);
            }
        }
        return maxA;
    }

    public int maximalRectangleV0(char[][] matrix) { // beats 59.36%
        int r = matrix.length;
        if (r == 0) {
            return 0;
        }
        int c = matrix[0].length;
        int[][] dp = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] != '0') {
                    dp[i][j] = 1;
                    int k = j + 1;

                    while( k < c && matrix[i][k] > '0' ) {
                        dp[i][j] += 1;
                        k++;
                    }
                }
            }
        }
        int maxArea = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] != '0') {
                    int minWidth = dp[i][j];
                    for (int k = i; k < r && dp[k][j] > 0; k++ ) {
                        minWidth = Math.min(minWidth, dp[k][j]);
                        int area = minWidth * (k - i + 1);
                        if (area > maxArea) {
                            maxArea = area;
                        }
                    }
                }
            }
        }
        return maxArea;
    }
}

// 1 0 1 0 0
// 1 0 1 1 1
// 1 1 1 1 1
// 1 0 0 1 0

// return 6