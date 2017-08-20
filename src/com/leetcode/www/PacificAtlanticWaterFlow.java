package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PacificAtlanticWaterFlow { // LC 417
    public List<int[]> pacificAtlantic(int[][] matrix) {

    }

    private int[] dx = new int[]{-1, 1, 0, 0};
    private int[] dy = new int[]{0, 0, -1, 1};

    public List<int[]> pacificAtlanticV0(int[][] matrix) { // beats 21.27%
        List<int[]> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int row = matrix.length, col = matrix[0].length;
        //One visited map for each ocean
        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];
        Deque<int[]> pQueue = new ArrayDeque<>();
        Deque<int[]> aQueue = new ArrayDeque<>();

        for (int i = 0; i < row; i++) { // Vertical border
            pQueue.offer(new int[]{i, 0});
            aQueue.offer(new int[]{i, col - 1});
            pacific[i][0] = true;
            atlantic[i][col - 1] = true;
        }
        for (int i = 0; i < col; i++) { // Horizontal border
            pQueue.offer(new int[]{0, i});
            aQueue.offer(new int[]{row - 1, i});
            pacific[0][i] = true;
            atlantic[row - 1][i] = true;
        }
        bfs(matrix, pQueue, pacific);
        bfs(matrix, aQueue, atlantic);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (pacific[i][j] && atlantic[i][j])
                    res.add(new int[]{i, j});
            }
        }
        return res;
    }

    private void bfs(int[][]matrix, Deque<int[]> queue, boolean[][]visited){
        int row = matrix.length, col = matrix[0].length;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0], curY = cur[1];
            for (int i = 0; i < 4; i++) {
                int x = curX + dx[i];
                int y = curY + dy[i];
                if (x < 0 || x >= row || y < 0 || y >= col || visited[x][y] || matrix[x][y] < matrix[curX][curY]) {
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[]{x, y});
            }
        }
    }
}
