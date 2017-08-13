package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix { // LC 54
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return res;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        helper(res, matrix, row, col, 0);
        return res;
    }

    private void helper(List<Integer> res, int[][] m, int row, int col, int offset) {
        if (row == 0 || col == 0) {
            return;
        }
        if (row == 1) {
            for (int i = offset; i < col + offset; i++) {
                res.add(m[offset][i]);
            }
            return;
        }
        if (col == 1) {
            for (int i = offset; i < row + offset; i++) {
                res.add(m[i][offset]);
            }
            return;
        }

        // up row
        for (int i = offset; i < col - 1 + offset; i++) {
            res.add(m[offset][i]);
        }

        // right col
        for (int i = offset; i < row - 1 + offset; i++) {
            res.add(m[i][col - 1 + offset]);
        }

        // down row
        for (int i = col - 1 + offset; i > offset; i--) {
            res.add(m[row - 1 + offset][i]);
        }

        // left col
        for (int i = row - 1 + offset; i > offset; i--) {
            res.add(m[i][offset]);
        }

        // next level
        helper(res, m, row - 2, col - 2, offset + 1);
    }
}

// [
//  [ 1, 2, 3 ],
//  [ 4, 5, 6 ],
//  [ 7, 8, 9 ]
// ]
//
// => [1,2,3,6,9,8,7,4,5]

// [[2,5,8],[4,0,-1]] => [2,5,8,-1,0,4]

// beats 76.03%