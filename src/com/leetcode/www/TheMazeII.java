package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class TheMazeII { // LC 505
    /**
     * Runtime: 7 ms, faster than 99.21% of Java online submissions for The Maze II.
     * Memory Usage: 44.8 MB, less than 82.40% of Java online submissions for The Maze II.
     *
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int row = maze.length;
        int col = maze[0].length;
        int[][] dis = new int[row][col];
        int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        Deque<Point> queue = new ArrayDeque<>();
        queue.offerLast(new Point(start[0], start[1], 0));

        for (int i = 0; i < row; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }

        while (!queue.isEmpty()) {
            Point cur = queue.pollFirst();
            for (int[] d : dir) {
                int nextX = cur.x;
                int nextY = cur.y;
                int len = cur.len;

                // move until boundary
                while (isValid(nextX, nextY, row, col, maze)) {
                    nextX += d[0];
                    nextY += d[1];
                    len++;
                }

                nextX -= d[0];
                nextY -= d[1];
                len--;

                if (len > dis[destination[0]][destination[1]]) {
                    continue;
                }

                if (len < dis[nextX][nextY]) {
                    dis[nextX][nextY] = len;
                    queue.offerLast(new Point(nextX, nextY, len));
                }
            }
        }

        return dis[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dis[destination[0]][destination[1]];
    }

    private boolean isValid(int x, int y, int m, int n, int[][] maze) {
        return x < m && x >= 0 && y < n && y >= 0 && maze[x][y] == 0;
    }

    class Point {
        int x;
        int y;
        int len;
        public Point(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }
}

// Input 1: a maze represented by a 2D array
//
// 0 0 1 0 0
// 0 0 0 0 0
// 0 0 0 1 0
// 1 1 0 1 1
// 0 0 0 0 0
//
// Input 2: start coordinate (rowStart, colStart) = (0, 4)
// Input 3: destination coordinate (rowDest, colDest) = (4, 4)
//
// Output: 12
// Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
// The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.


// Input 1: a maze represented by a 2D array
//
// 0 0 1 0 0
// 0 0 0 0 0
// 0 0 0 1 0
// 1 1 0 1 1
// 0 0 0 0 0
//
// Input 2: start coordinate (rowStart, colStart) = (0, 4)
// Input 3: destination coordinate (rowDest, colDest) = (3, 2)
//
// Output: -1
// Explanation: There is no way for the ball to stop at the destination.

// beats 81.10%

/** Note:
 *    There is only one ball and one destination in the maze.
 *    Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 *    The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
 *    The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
