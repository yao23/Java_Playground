public class MaxAreaOfIsland {
    /**
     * Runtime: 6 ms, faster than 7.73% of Java online submissions for Max Area of Island.
     * Memory Usage: 46.3 MB, less than 7.41% of Java online submissions for Max Area of Island.
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        if (row == 0) {
            return 0;
        }
        int col = grid[0].length;
        if (col == 0) {
            return 0;
        }
        int area = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                area = Math.max(area, helper(i, j, grid));
            }
        }
        return area;
    }

    private int helper(int r, int c, int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        if (r < 0 || r == row || c < 0 || c == col || grid[r][c] == 0) {
            return 0;
        } else {
            grid[r][c] = 0;
            int res = 1;
            int[] dx = new int[]{-1, 1, 0, 0};
            int[] dy = new int[]{0, 0, -1, 1};
            for (int i = 0; i < dx.length; i++) {
                res += helper(r + dx[i], c + dy[i], grid);
            }
            return res;
        }
    }
}
