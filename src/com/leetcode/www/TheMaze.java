package com.leetcode.www;

import java.util.LinkedList;
import java.util.Queue;

public class TheMaze { // LC 490
    /**
     * Runtime: 3 ms, faster than 88.62% of Java online submissions for The Maze.
     * Memory Usage: 44.8 MB, less than 83.55% of Java online submissions for The Maze.
     *
     * DFS
     *
     * https://leetcode.com/problems/the-maze/discuss/297377/Concise-3ms-DFS-java
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

    /**
     * Runtime: 4 ms, faster than 83.44% of Java online submissions for The Maze.
     * Memory Usage: 45.4 MB, less than 83.18% of Java online submissions for The Maze.
     *
     * BFS
     *
     * https://leetcode.com/problems/the-maze/discuss/289031/Java-Solution-with-some-comments-using-BFS-like-other-submitted-solutions
     *
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    public boolean hasPathV0(int[][] maze, int[] start, int[] destination) {
        int row = maze.length;
        int col = maze[0].length;
        boolean[][]visited = new boolean[row][col];
        visited[start[0]][start[1]] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        int[][]dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        while (!q.isEmpty()) {
            int[] s = q.poll();
            // we arrived at the destination so return true;
            if (visited[destination[0]][destination[1]]) {
                return true;
            }
            // Check for all the directions
            for (int[]dir : dirs) {
                // Keep moving in a particular direction until we have met an obstacle or we're out of the maze.
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                while (x >= 0 && y >= 0 && x < row && y < col && maze[x][y]==0) {
                    x += dir[0];
                    y += dir[1];
                }
                // subtract dir[0] & dir[1] from x and y as the while loop has given it an increased value which needs to be compensated for.
                int newX = x - dir[0];
                int newY = y - dir[1];
                if (!visited[newX][newY]) {
                    visited[newX][newY] = true;
                    q.add(new int[] {newX, newY});
                }
            }
        }
        return false;
    }
}
