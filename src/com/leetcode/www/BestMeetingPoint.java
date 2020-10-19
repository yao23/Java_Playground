package com.leetcode.www;

import java.util.*;

public class BestMeetingPoint { // LC 296
    /**
     * beats 55.32%
     *
     * @param grid
     * @return
     */
    public int minTotalDistance(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        List<Integer> rowList = new ArrayList<>(row);
        List<Integer> colList = new ArrayList<>(col);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    rowList.add(i);
                    colList.add(j);
                }
            }
        }

        return getMin(rowList) + getMin(colList);
    }

    private int getMin(List<Integer> list){
        int ret = 0;

        Collections.sort(list);

        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            ret += list.get(j) - list.get(i);
            j--;
            i++;
        }

        return ret;
    }

    /**
     * As long as you have different numbers of people on your left and on your right,
     * moving a little to the side with more people decreases the sum of distances.
     * So to minimize it, you must have equally many people on your left and on your right. Same with above/below.
     *
     *
     * Two O(mn) solutions, both take 2ms.

     * The neat total += Z[hi--] - Z[lo++]-style summing is from larrywang2014's solution (above 1st).
     * Originally I used total += abs(Z[i] - median)-style.
     *
     * beats 88.66%
     */
    public int minTotalDistanceV2(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int total = 0, Z[] = new int[row*col];
        for (int dim = 0; dim < 2; ++dim) {
            int i = 0, j = 0;
            if (dim == 0) {
                for (int x = 0; x < col; ++x) {
                    for (int y = 0; y < row; ++y) {
                        if (grid[y][x] == 1) {
                            Z[j++] = x;
                        }
                    }
                }
            } else {
                for (int y = 0; y < row; ++y) {
                    for (int g : grid[y]) {
                        if (g == 1) {
                            Z[j++] = y;
                        }
                    }
                }
            }
            while (i < --j) {
                total += Z[j] - Z[i++];
            }
        }
        return total;
    }

    /**
     *
     * @param grid
     * @return
     *
     * BucketSort-ish. Count how many people live in each row and each column. Only O(m+n) space.
     *
     * beats 96.76
     */
    public int minTotalDistanceV1(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[] rowArr = new int[row], colArr = new int[col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (grid[i][j] == 1) {
                    ++rowArr[i];
                    ++colArr[j];
                }
            }
        }
        int total = 0;
        for (int[] rowArray : new int[][]{ rowArr, colArr }) {
            int i = 0, j = rowArray.length - 1;
            while (i < j) {
                int k = Math.min(rowArray[i], rowArray[j]);
                total += k * (j - i);
                if ((rowArray[i] -= k) == 0) {
                    ++i;
                }
                if ((rowArray[j] -= k) == 0) {
                    --j;
                }
            }
        }
        return total;
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