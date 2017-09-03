package io.bittiger.www;

public class FindFirstColumnHasOne {
    public int getFirstColumnContainsOne(int[][] nums) { // time: O(M + N)
        if (nums == null || nums.length == 0) { // corner case
            return -1;
        }
        // normal case
        int row = nums.length;
        int col = nums[0].length;
        int res = Integer.MAX_VALUE;
        int i = 0;
        int j = col - 1;
        while (i < row && j >= 0) {
            if (nums[i][j] == 0) {
                i++;
            } else {
                res = j;
                j--;
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res + 1;
    }
}

/**
 * given a 2D (N*M) integer array, find the first column that contains 1. Notice that for each row, if [i, j] = 1 then
 * every element afterwards in the same row will be 1. like [i, j + 1] [i, j + 2], [i, j + 3]...
 * Need to solve this in O(N + M)
 *
 * eg.
 * [
 *   [0   0   0   1   1   1]
 *   [0   0   1   1   1   1]
 *   [0   0   0   0   0   1]
 * ]
 * The first column that contains 1 is 3rd column. return 3
 *
 * [
 *   [0   0   1   1]
 *   [1   1   1   1]
 *   [0   0   0   1]
 * ]
 * The first column that contains 1 will be 1st column. return 1
 */