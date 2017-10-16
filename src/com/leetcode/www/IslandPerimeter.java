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

    // O(mn) time and O(1) space
    private int rn ;
    private int cn ;
    public int islandPerimeterV1(int[][] grid) { // beats 73.68%
        if (grid == null || grid.length < 1 || grid[0].length < 1) {
            return 0;
        }
        rn = grid.length - 1;
        cn = grid[0].length - 1;
        int perimeter = 0;
        for (int i = 0; i <= rn; i++) {
            for (int j = 0; j <= cn; j++) {
                if (grid[i][j] == 1) {
                    perimeter += findCount(grid, i, j);
                }
            }
        }
        return perimeter;
    }
    private int findCount(int[][] grid, int i, int j) {
        int sum = 0;
        //top
        if (i != 0) {
            sum += (grid[i - 1][j] == 0) ? 1 : 0;
        } else {
            sum += 1;
        }
        //bottom
        if (i != rn) {
            sum += (grid[i + 1][j] == 0) ? 1 : 0;
        } else {
            sum += 1;
        }
        //left
        if (j != 0) {
            sum += (grid[i][j - 1] == 0) ? 1 : 0;
        } else {
            sum += 1;
        }
        //right
        if (j != cn) {
            sum += (grid[i][j + 1] == 0) ? 1 : 0;
        } else{
            sum += 1;
        }
        return sum;
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
