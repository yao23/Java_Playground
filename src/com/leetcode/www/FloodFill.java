package com.leetcode.www;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class FloodFill { // LC 733
    /**
     * Runtime: 1 ms, faster than 97.71% of Java online submissions for Flood Fill.
     * Memory Usage: 44.7 MB, less than 69.04% of Java online submissions for Flood Fill.
     *
     * https://leetcode.com/problems/flood-fill/discuss/304883/Java-1-ms-faster-than-97.81
     *
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        boolean[][] painted = new boolean[image.length][image[0].length];
        paint(image, sr, sc, newColor, painted);
        return image;
    }

    private void paint(int[][] image, int r, int c, int newColor, boolean[][] painted) {
        int oldColor = image[r][c];
        int width = image[0].length;
        int height = image.length;

        if (c - 1 >= 0) {
            if (image[r][c - 1] == oldColor && !painted[r][c - 1]) {
                painted[r][c - 1] = true;
                paint(image, r, c - 1, newColor, painted);
            }
        }
        if (c + 1 < width) {
            if (image[r][c + 1] == oldColor && !painted[r][c + 1]) {
                painted[r][c + 1] = true;
                paint(image, r, c + 1, newColor, painted);
            }
        }
        if (r - 1 >= 0) {
            if (image[r - 1][c] == oldColor && !painted[r - 1][c]) {
                painted[r - 1][c] = true;
                paint(image, r - 1, c, newColor, painted);
            }
        }
        if (r + 1 < height) {
            if (image[r + 1][c] == oldColor && !painted[r + 1][c]) {
                painted[r + 1][c] = true;
                paint(image, r + 1, c, newColor, painted);
            }
        }
        image[r][c] = newColor;
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Flood Fill.
     * Memory Usage: 45 MB, less than 68.35% of Java online submissions for Flood Fill.
     *
     * https://leetcode.com/problems/flood-fill/discuss/291959/Java-Solution-using-DFS
     *
     * @param image
     * @param i
     * @param j
     * @param newColor
     * @return
     */
    public int[][] floodFillV1(int[][] image, int i, int j, int newColor) {
        dfs(image, i, j, newColor, image[i][j]);
        return image;
    }

    private void dfs(int[][] image, int i, int j, int newColor, int startingPixelColor) {
        if (i < 0 || j < 0 || i >= image.length || j >= image[0].length || image[i][j] != startingPixelColor ||
                image[i][j] == newColor) {
            return;
        }
        image[i][j] = newColor;
        dfs(image,i - 1, j, newColor, startingPixelColor);
        dfs(image,i + 1, j, newColor, startingPixelColor);
        dfs(image,i,j - 1, newColor, startingPixelColor);
        dfs(image,i,j + 1, newColor, startingPixelColor);
    }

    // left, top, right, bottom
    private final int[][] sides = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    /**
     * Runtime: 2 ms, faster than 41.50% of Java online submissions for Flood Fill.
     * Memory Usage: 38.3 MB, less than 100.00% of Java online submissions for Flood Fill.
     *
     * BFS
     *
     * https://leetcode.com/problems/flood-fill/discuss/306147/Java-BFS-solution-with-comments-(2ms-45.3MB)
     *
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public int[][] floodFillV0(int[][] image, int sr, int sc, int newColor) {
        Set<Point> visited = new HashSet<>();
        LinkedList<Point> queue = new LinkedList<>();
        queue.add(new Point(sr,sc));
        visited.add(queue.peek());
        int fillColor = image[sr][sc];

        while (!queue.isEmpty()) {
            Point point = queue.pollFirst();
            image[point.row][point.col] = newColor;

            // Check 4 directions (left, top, right, bottom) for suitable to change color
            for (int[] side : sides){
                Point p = isSuitable(point.row + side[0], point.col + side[1], image, visited, fillColor);

                // If this point is suitable, add to visited and to the query for next step
                if (p != null) {
                    queue.add(p);
                    visited.add(point);
                }
            }
        }
        return image;
    }

    //*** Check if this Point in array is suitable and not visited before. Return Point if yes
    private Point isSuitable(int row, int col, int[][] image, Set<Point> visited, int fillColor){
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length || image[row][col] != fillColor) {
            return null;
        }
        Point point = new Point(row, col);
        return visited.contains(point) ? null : point;
    }

    // Point class with row and column in the array
    private class Point {
        int row;
        int col;
        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }

        //*** Add hashCode and equals for use HashSet 'visited'
        @Override
        public int hashCode(){
            return 31 * (31 + row) + col;
        }

        @Override
        public boolean equals(Object obj){
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Point)) {
                return false;
            }
            Point p = (Point) obj;
            return p.row == this.row && p.col == this.col;
        }
    }
}
