package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestDistanceFromAllBuildings { // LC 317
    private int[] dx = new int[]{-1, 1, 0, 0};
    private int[] dy = new int[]{0, 0, -1, 1};

    public int shortestDistance(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int[][] res = new int[row][col];

//        init(res, row, col);

        if (updateDistance(res, grid, row, col)) {
            return getShortestDistance(res, grid, row, col);
        } else {
            return -1;
        }
    }

    private void init(int[][] res, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    private Deque<int[]> findOnes(int[][] grid, int row, int col) {
        Deque<int[]> res = new ArrayDeque<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    res.offer(new int[]{i, j});
                }
            }
        }

        return res;
    }

    private boolean updateDistance(int[][] res, int[][] grid, int row, int col) {
        Deque<int[]> queue = findOnes(grid, row, col);

        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int x = pair[0], y = pair[1];
            if (!updateEmptyLand(res, grid, row, col, x, y)) {
                return false;
            }
        }

        return true;
    }

    private boolean updateEmptyLand(int[][] res, int[][] grid, int row, int col, int xBuilding, int yBuilding) {
        Deque<int[]> queue = new ArrayDeque<>();
        int cur = 0;
        int distance = 0;

        for (int i = 0; i < 4; i++) {
            if (update(res, grid, row, col, xBuilding + dx[i], yBuilding + dy[i], distance)) {
                queue.offer(new int[]{xBuilding + dx[i], yBuilding + dy[i]});
                cur++;
            }
        }

        boolean isConnected = (cur > 0); // connected with at least 1 empty land

        while (!queue.isEmpty()) {
            int next = 0;
            for (int i = 0; i < cur; i++) {
                int[] pair = queue.poll();
                int x = pair[0], y = pair[1];
                for (int j = 0; j < 4; j++) {
                    if (update(res, grid, row, col, x + dx[j], y + dy[j], distance + 1)) {
                        queue.offer(new int[]{x + dx[j], y + dy[j]});
                        next++;
                    }
                }
            }
            distance++;
            cur = next;
        }

        return isConnected;
    }

    private boolean update(int[][] res, int[][] grid, int row, int col, int x, int y, int distance) {
        if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] != 0) {
            return false;
        }
        res[x][y] += (distance + 1);
        return true;
    }

    private int getShortestDistance(int[][] res, int[][] grid, int row, int col) {
        int shortest = Integer.MAX_VALUE;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    shortest = Math.min(shortest, res[i][j]);
                }
            }
        }

        return shortest;
    }
}
