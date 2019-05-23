package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestDistanceFromAllBuildings { // LC 317
    /**
     *
     * Runtime: 38 ms, faster than 44.96% of Java online submissions for Shortest Distance from All Buildings.
     * Memory Usage: 36.5 MB, less than 99.44% of Java online submissions for Shortest Distance from All Buildings.
     *
     * @param grid
     * @return
     *
     * Traverse the matrix. For each building, use BFS to compute the shortest distance from each '0' to
     * this building. After we do this for all the buildings, we can get the sum of shortest distance
     * from every '0' to all reachable buildings. This value is stored
     * in 'distance[][]'. For example, if grid[2][2] == 0, distance[2][2] is the sum of shortest distance from this block to all reachable buildings.
     * Time complexity: O(number of 1)O(number of 0) ~ O(m^2n^2)

     * We also count how many building each '0' can be reached. It is stored in reach[][]. This can be done during the BFS.
     * We also need to count how many total buildings are there in the matrix, which is stored in 'buildingNum'.

     * Finally, we can traverse the distance[][] matrix to get the point having shortest distance to all buildings. O(m*n)
     */
    public int shortestDistance(int[][] grid) { // beats 44.18%
        if (grid == null || grid[0].length == 0) {
            return 0;
        }
        final int[] shift = new int[] {0, 1, 0, -1, 0};

        int row  = grid.length, col = grid[0].length;
        int[][] distance = new int[row][col];
        int[][] reach = new int[row][col];
        int buildingNum = 0;

        for (int i = 0; i < row; i++) {
            for (int j =0; j < col; j++) {
                if (grid[i][j] == 1) {
                    buildingNum++;
                    Deque<int[]> myQueue = new ArrayDeque<>();
                    myQueue.offer(new int[] {i,j});

                    boolean[][] isVisited = new boolean[row][col];
                    int level = 1; // set level before BFS

                    while (!myQueue.isEmpty()) { // BFS
                        int qSize = myQueue.size();
                        for (int q = 0; q < qSize; q++) {
                            int[] curr = myQueue.poll();

                            for (int k = 0; k < 4; k++) { // right, down, left, up
                                int nextRow = curr[0] + shift[k];
                                int nextCol = curr[1] + shift[k + 1];

                                if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col
                                        && grid[nextRow][nextCol] == 0 && !isVisited[nextRow][nextCol]) {
                                    // The shortest distance from [nextRow][nextCol] to this building is 'level'.
                                    distance[nextRow][nextCol] += level;
                                    reach[nextRow][nextCol]++;

                                    isVisited[nextRow][nextCol] = true;
                                    myQueue.offer(new int[] {nextRow, nextCol});
                                }
                            }
                        }
                        level++;
                    }
                }
            }
        }

        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0 && reach[i][j] == buildingNum) { // the current empty land could access to all buildings or not
                    shortest = Math.min(shortest, distance[i][j]);
                }
            }
        }

        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }

    /**
     * Runtime: 33 ms, faster than 54.15% of Java online submissions for Shortest Distance from All Buildings.
     * Memory Usage: 41.2 MB, less than 71.90% of Java online submissions for Shortest Distance from All Buildings.
     *
     * Solution V1 (improved my solution, working)
     */
    public int shortestDistanceV1(int[][] grid) { // beats 39.63%
        if (grid == null || grid[0].length == 0) {
            return 0;
        }

        int row  = grid.length, col = grid[0].length;
        int[][] distance = new int[row][col];
        int[][] reach = new int[row][col];
        int buildingNum = searchBuilding(grid, reach, distance, row, col);

        return getShortest(grid, reach, distance, row, col, buildingNum);
    }

    private int searchBuilding(int[][] grid, int[][] reach, int[][] distance, int row, int col) {
        int buildingNum = 0;

        for (int i = 0; i < row; i++) {
            for (int j =0; j < col; j++) {
                if (grid[i][j] == 1) {
                    buildingNum++;
                    searchEmptyLand(grid, reach, distance, i, j, row, col);
                }
            }
        }

        return buildingNum;
    }

    // BFS
    private void searchEmptyLand(int[][]grid, int[][] reach, int[][] distance, int i, int j, int row, int col) {
        Deque<int[]> myQueue = new ArrayDeque<>();
        myQueue.offer(new int[] {i,j});

        boolean[][] isVisited = new boolean[row][col];
        int level = 1;

        while (!myQueue.isEmpty()) {
            int qSize = myQueue.size();
            for (int q = 0; q < qSize; q++) {
                int[] curr = myQueue.poll();

                for (int k = 0; k < 4; k++) {
                    int newX = curr[0] + dx[k];
                    int newY = curr[1] + dy[k];

                    if (newX >= 0 && newX < row && newY >= 0 && newY < col
                            && grid[newX][newY] == 0 && !isVisited[newX][newY]) {
                        //The shortest distance from [nextRow][nextCol] to this building is 'level'.
                        distance[newX][newY] += level;
                        reach[newX][newY]++;

                        isVisited[newX][newY] = true;
                        myQueue.offer(new int[] {newX, newY});
                    }
                }
            }
            level++;
        }
    }

    private int getShortest(int[][] grid, int[][] reach, int[][] distance, int row, int col, int buildingNum) {
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0 && reach[i][j] == buildingNum) {
                    shortest = Math.min(shortest, distance[i][j]);
                }
            }
        }

        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }


    /**
     * My Original Solution (not working, for retrospection)
     */

    private int[] dx = new int[]{-1, 1, 0, 0};
    private int[] dy = new int[]{0, 0, -1, 1};

    public int shortestDistanceV0(int[][] grid) {
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
