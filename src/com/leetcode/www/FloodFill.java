package com.leetcode.www;

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
}
