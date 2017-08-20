package com.leetcode.www;

import java.util.*;

public class BestMeetingPoint { // LC 296
    public int minTotalDistance(int[][] grid) { // beats 55.32%
        int m = grid.length;
        int n = grid[0].length;

        List<Integer> I = new ArrayList<>(m);
        List<Integer> J = new ArrayList<>(n);

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    I.add(i);
                    J.add(j);
                }
            }
        }

        return getMin(I) + getMin(J);
    }

    private int getMin(List<Integer> list){
        int ret = 0;

        Collections.sort(list);

        int i = 0;
        int j = list.size() - 1;
        while(i < j){
            ret += list.get(j--) - list.get(i++);
        }

        return ret;
    }


    /**
     * My original solution (TLE for test case 3, pass 56/57)
     * similar problem: LC 317, Shortest Distance from All Buildings
     */
    private int[] dx = new int[]{-1, 1, 0, 0};
    private int[] dy = new int[]{0, 0, -1, 1};

    public int minTotalDistanceV0(int[][] grid) { // TLE for test case 3 (pass 56/57)
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int[][] distance = new int[row][col];

        int num = searchPeople(grid, distance, row, col);

        if (num == row * col) {
            return distance[0][0];
        } else {
            return getShortestDistance(grid, distance);
        }
    }

    private int searchPeople(int[][] grid, int[][] distance, int row, int col) {
        int num = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    updateDistance(distance, row, col, i, j);
                    num++;
                }
            }
        }

        return num;
    }

    private void updateDistance(int[][] distance, int row, int col, int x, int y) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        int cur = 1;
        boolean[][] visited = new boolean[row][col];

        while (!queue.isEmpty()) {
            int next = 0;
            for (int i = 0; i < cur; i++) {
                int[] pair = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int newX = pair[0] + dx[j];
                    int newY = pair[1] + dy[j];
                    if (newX >= 0 && newX < row && newY >= 0 && newY < col && !visited[newX][newY]) {
                        distance[newX][newY] += (Math.abs(newX - x) + Math.abs(newY - y));
                        visited[newX][newY] = true;
                        queue.offer(new int[]{newX, newY});
                        next++;
                    }
                }
            }
            cur = next;
        }
    }

    private int getShortestDistance(int[][] grid, int[][] distance) {
        int row = grid.length;
        int col = grid[0].length;
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (distance[i][j] < res) {
                    res = distance[i][j];
                }
            }
        }

        if (res == Integer.MAX_VALUE) {
            return -1;
        } else {
            return res;
        }
    }
}

// test case 1
// [[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]] => 6

// 1 - 0 - 0 - 0 - 1
// |   |   |   |   |
// 0 - 0 - 0 - 0 - 0
// |   |   |   |   |
// 0 - 0 - 1 - 0 - 0

// (0,2), 6 = 2 + 2 + 2 (Manhattan Distance, distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|)


// test case 2
// [[1,1]] => 1

// test case 3
// row: 110, col: 110, every element is 1 => 665500