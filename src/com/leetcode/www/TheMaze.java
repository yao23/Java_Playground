package com.leetcode.www;

public class TheMaze { // LC 490
    /**
     * Runtime: 3 ms, faster than 88.62% of Java online submissions for The Maze.
     * Memory Usage: 44.8 MB, less than 83.55% of Java online submissions for The Maze.
     */
    boolean[][] visited;
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int row = maze.length;
        int col = maze[0].length;
        if (visited == null) {
            visited = new boolean[row][col];
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return true;
        }

        int x = start[0], y = start[1];
        if (visited[x][y]) {
            return false;
        }
        visited[x][y] = true;

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : dirs) {
            x = start[0];
            y = start[1];
            while (x >= 0 && y >= 0 && x < row && y < col && maze[x][y] == 0) { // roll as far as possible in one direction
                x += dir[0];
                y += dir[1];
            }
            if (hasPath(maze, new int[]{x - dir[0], y - dir[1]}, destination)) { // reach destination from the direction
                return true;
            }
        }
        return false;
    }
}
