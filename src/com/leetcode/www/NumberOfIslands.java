package com.leetcode.www;

/**
 * Created by liyao on 6/24/17.
 */
public class NumberOfIslands { // LC 200
    private void dfs(int x, int y, char[][] grid) {
        int row = grid.length, col = grid[0].length;
        if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] == '0') { // out of bounds or invalid point (water)
            return;
        } else { // inside valid point (land)
            grid[x][y] = '0'; // update as visited
            dfs(x - 1, y, grid); // up row
            dfs(x + 1, y, grid); // bottom row
            dfs(x, y - 1, grid); // left col
            dfs(x, y + 1, grid); // right col
        }
    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Number of Islands.
     * Memory Usage: 41 MB, less than 61.74% of Java online submissions for Number of Islands.
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int[] num = new int[]{0};
        int row = grid.length;
        if (row == 0) { // zero row
            return 0;
        }
        int col = grid[0].length;
        if (col == 0) { // zero col
            return 0;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') { // land
                    num[0]++;
                    dfs(i, j, grid); // update neighbors
                } else { // water
                    continue;
                }
            }
        }

        return num[0];
    }

    // [] => 0
    // ["11000"] => 1
    // ["11110","11010","11000","00000"] => 1
    // ["11000","11000","00100","00011"] => 3

    // beats 93.85%
}
