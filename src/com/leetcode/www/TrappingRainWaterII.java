package com.leetcode.www;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TrappingRainWaterII { // LC 407
    /**
     * Runtime: 51 ms, faster than 11.59% of Java online submissions for Trapping Rain Water II.
     * Memory Usage: 38 MB, less than 92.65% of Java online submissions for Trapping Rain Water II.
     *
     * https://leetcode.com/problems/trapping-rain-water-ii/discuss/89461/Java-solution-using-PriorityQueue
     *
     * BFS + Heap (Min Heap)
     *
     */
    public class Cell {
        int row;
        int col;
        int height;
        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return 0;
        }

        // min heap (https://jindongpu.wordpress.com/2015/10/20/implement-max-heap-and-min-heap-using-priorityqueue-in-java/)
        PriorityQueue<Cell> queue = new PriorityQueue<>(1, Comparator.comparingInt(a -> a.height));

        int row = heights.length;
        int col = heights[0].length;
        boolean[][] visited = new boolean[row][col];

        // Initially, add all the Cells which are on borders to the queue.
        for (int i = 0; i < row; i++) { // left and right columns
            visited[i][0] = true;
            visited[i][col - 1] = true;
            queue.offer(new Cell(i, 0, heights[i][0]));
            queue.offer(new Cell(i, col - 1, heights[i][col - 1]));
        }

        for (int i = 0; i < col; i++) { // top and bottom rows
            visited[0][i] = true;
            visited[row - 1][i] = true;
            queue.offer(new Cell(0, i, heights[0][i]));
            queue.offer(new Cell(row - 1, i, heights[row - 1][i]));
        }

        // from the borders, pick the shortest cell visited and check its neighbors:
        // if the neighbor is shorter, collect the water it can trap and update its height as its height plus the water trapped
        /**
         * Reason is we put boarder elements in min heap, we start from shortest cell(imaging there are many tall walls
         * surrounding those cells which are short,and we start from the shortest wall) to find the neighbor which is
         * shorter than the shortest wall (this poor short neighbor is going to trap water because it's shorter than the
         * shortest wall so that it's also shorter than all other wall surrounding it.)
         */
        // add all its neighbors to the queue.
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int res = 0;
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            for (int[] dir : dirs) {
                int newX = cell.row + dir[0];
                int newY = cell.col + dir[1];
                if (newX >= 0 && newX < row && newY >= 0 && newY < col && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    res += Math.max(0, cell.height - heights[newX][newY]); // ignore negative height difference
                    queue.offer(new Cell(newX, newY, Math.max(heights[newX][newY], cell.height)));
                }
            }
        }

        return res;
    }
}
