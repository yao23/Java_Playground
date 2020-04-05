import java.util.Stack;

public class MaxAreaOfIsland {
    /**
     * Runtime: 6 ms, faster than 7.73% of Java online submissions for Max Area of Island.
     * Memory Usage: 46.3 MB, less than 7.41% of Java online submissions for Max Area of Island.
     *
     * Time Complexity: O(R*C), where R is the number of rows in the given grid, and C is the number of columns.
     * We visit every square once.
     *
     * Space complexity: O(R*C), the space used by seen to keep track of visited squares, and the space used by the
     * call stack during our recursion.
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

    /**
     * Runtime: 5 ms, faster than 12.80% of Java online submissions for Max Area of Island.
     * Memory Usage: 40 MB, less than 96.30% of Java online submissions for Max Area of Island.
     *
     * Time Complexity: O(R*C), where R is the number of rows in the given grid, and C is the number of columns.
     * We visit every square once.
     *
     * Space complexity: O(R*C), the space used by seen to keep track of visited squares, and the space used by stack.
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIslandV0(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] seen = new boolean[row][col];
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        int ans = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == 1 && !seen[r][c]) {
                    int area = 0;
                    Stack<int[]> stack = new Stack();
                    stack.push(new int[]{r, c});
                    seen[r][c] = true;
                    while (!stack.empty()) {
                        int[] node = stack.pop();
                        int r = node[0], c = node[1];
                        area++;
                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];
                            if (0 <= nr && nr < grid.length &&
                                    0 <= nc && nc < grid[0].length &&
                                    grid[nr][nc] == 1 && !seen[nr][nc]) {
                                stack.push(new int[]{nr, nc});
                                seen[nr][nc] = true;
                            }
                        }
                    }
                    ans = Math.max(ans, area);
                }
            }
        }
        return ans;
    }
}
