package com.leetcode.www;

public class IslandPerimeter { // LC 463
    public int islandPerimeter(int[][] grid) { // beats 58.99%
        int islands = 0, neighbours = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    islands++; // count islands
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) {
                        neighbours++; // count down neighbours
                    }
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) {
                        neighbours++; // count right neighbours
                    }
                }
            }
        }

        return islands * 4 - neighbours * 2;
    }

    // add 4 for each land and remove 2 for each internal edge
    public int islandPerimeterV0(int[][] grid) { // beats 22.07%
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    result += 4;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        result -= 2;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        result -= 2;
                    }
                }
            }
        }
        return result;
    }
}

// [[0,1,0,0],
//  [1,1,1,0],
//  [0,1,0,0],
//  [1,1,0,0]]
//
// Answer: 16
// Explanation: The perimeter is the 16 yellow stripes in the image below:
