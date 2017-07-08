/**
 * Created by liyao on 7/8/17.
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        if (row == 1 && col == 1) { // only 1 number
            return grid[0][0];
        }
        int[][] result = new int[row + 1][col + 1];

        for (int i = 0; i <= col; i++) { // init bottom row
            result[row][i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i <= row; i++) { // init right col
            result[i][col] = Integer.MAX_VALUE;
        }

        result[row][col - 1] = 0; // init to start DP

        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                result[i][j] = grid[i][j] + Math.min(result[i + 1][j], result[i][j + 1]); // down or right
            }
        }

        return result[0][0];
    }

    // [[1]] => 1

    // beats 38.38%
}
