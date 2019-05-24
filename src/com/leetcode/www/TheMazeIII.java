package com.leetcode.www;

import java.util.PriorityQueue;

public class TheMazeIII { // LC 499
    /**
     * Runtime: 11 ms, faster than 39.63% of Java online submissions for The Maze III.
     * Memory Usage: 35.9 MB, less than 97.84% of Java online submissions for The Maze III.
     *
     * BFS
     *
     * https://leetcode.com/problems/the-maze-iii/discuss/97797/Similar-to-The-Maze-II.-Easy-understanding-Java-bfs-solution.
     *
     * @param maze
     * @param ball
     * @param hole
     * @return
     */
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int row = maze.length, col = maze[0].length;
        Point[][] points = new Point[row][col];
        for (int i = 0; i < row * col; i++) {
            points[i / col][i % col] = new Point(i / col, i % col);
        }
        int[][] dir = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        String[] ds = new String[] {"u", "r", "d", "l"};
        PriorityQueue<Point> list = new PriorityQueue<>();
        list.offer(new Point(ball[0], ball[1], 0, ""));
        while (!list.isEmpty()) {
            Point p = list.poll();
            if (points[p.x][p.y].compareTo(p) <= 0) { // if we have already found a route shorter
                continue;
            }
            points[p.x][p.y] = p;
            for (int i = 0; i < 4; i++) {
                int newX = p.x, newY = p.y, l = p.l;
                while (newX >= 0 && newX < row && newY >= 0 && newY < col && maze[newX][newY] == 0 &&
                        (newX != hole[0] || newY != hole[1])) {
                    newX += dir[i][0];
                    newY += dir[i][1];
                    l++;
                }
                if (newX != hole[0] || newY != hole[1]) { // check the hole
                    newX-=dir[i][0];
                    newY-=dir[i][1];
                    l--;
                }
                list.offer(new Point(newX, newY, l, p.s + ds[i]));
            }
        }
        return points[hole[0]][hole[1]].l == Integer.MAX_VALUE ? "impossible" : points[hole[0]][hole[1]].s;
    }

    class Point implements Comparable<Point> {
        int x, y, l;
        String s;
        public Point(int _x, int _y) {
            x = _x;
            y = _y;
            l = Integer.MAX_VALUE;
            s = "";
        }
        public Point(int _x, int _y, int _l, String _s) {
            x = _x;
            y = _y;
            l = _l;
            s = _s;
        }
        public int compareTo(Point p) {
            return l == p.l ? s.compareTo(p.s) : l - p.l;
        }
    }
}
