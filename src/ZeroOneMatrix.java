/**
 * Created by liyao on 7/3/17.
 */
import java.util.ArrayDeque;
import java.util.Deque;

public class ZeroOneMatrix {
    public int[][] updateMatrix(int[][] matrix) { // beats 43.77%, O(n)
        int row = matrix.length;
        int col = matrix[0].length;

        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) { // all neighbors of 0s
            int[] cell = queue.poll();
            for (int[] d : dirs) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r < 0 || r >= row || c < 0 || c >= col || matrix[r][c] <= matrix[cell[0]][cell[1]] + 1) {
                    continue;
                } else {
                    queue.add(new int[] {r, c});
                    matrix[r][c] = matrix[cell[0]][cell[1]] + 1;
                }
            }
        }

        return matrix;
    }

    public int[][] updateMatrixV0(int[][] matrix) { // TLE
        int row = matrix.length;
        if (row == 0) {
            return new int[][]{};
        } else {
            int col = matrix[0].length;
            int[][] results = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    results[i][j] = search(i, j, matrix);
                }
            }

            return results;
        }
    }

    private int search(int x, int y, int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;

        if (x < 0 || x >= row || y < 0 || y >= col) { // invalid index
            return -1;
        } else { // valid index
            if (matrix[x][y] == 0) {
                return 0;
            } else {
                matrix[x][y] = 2;
                int[] neighbors = new int[4];
                neighbors[0] = search(x - 1, y, matrix); // up
                if (neighbors[0] == 0) {
                    matrix[x][y] = 1;
                    return 1;
                }
                neighbors[1] = search(x + 1, y, matrix); // down
                if (neighbors[1] == 0) {
                    matrix[x][y] = 1;
                    return 1;
                }
                neighbors[2] = search(x, y - 1, matrix); // left
                if (neighbors[2] == 0) {
                    matrix[x][y] = 1;
                    return 1;
                }
                neighbors[3] = search(x, y + 1, matrix); // right
                if (neighbors[3] == 0) {
                    matrix[x][y] = 1;
                    return 1;
                }

                int min = Integer.MAX_VALUE;
                for (int i = 0; i < neighbors.length; i++) {
                    if (neighbors[i] >= 0 && neighbors[i] < min) {
                        min = neighbors[i];
                    }
                }

                matrix[x][y] = 1;
                return 1 + min;
            }
        }
    }

    // [[0,0,0],[0,1,0],[0,0,0]] => [[0,0,0],[0,1,0],[0,0,0]]
    // [[0,0,0],[0,1,0],[1,1,1]] => [[0,0,0],[0,1,0],[1,2,1]]
    // [[1,1,1],[0,1,0],[1,1,1]] => [[1,2,1],[0,1,0],[1,2,1]]
    // [[1,1,1],[1,0,1],[1,1,1]] => [[2,1,1],[1,0,1],[2,1,2]]
    // [[1,1,0,0,1,0,0,1,1,0],[1,0,0,1,0,1,1,1,1,1],[1,1,1,0,0,1,1,1,1,0],[0,1,1,1,0,1,1,1,1,1],[0,0,1,1,1,1,1,1,1,0],[1,1,1,1,1,1,0,1,1,1],[0,1,1,1,1,1,1,0,0,1],[1,1,1,1,1,0,0,1,1,1],[0,1,0,1,1,0,1,1,1,1],[1,1,1,0,1,0,1,1,1,1]] => [[2,1,0,0,1,0,0,1,1,0],[1,0,0,1,0,1,1,2,2,1],[1,1,1,0,0,1,2,2,1,0],[0,1,2,1,0,1,2,3,2,1],[0,0,1,2,1,2,1,2,1,0],[1,1,2,3,2,1,0,1,1,1],[0,1,2,3,2,1,1,0,0,1],[1,2,1,2,1,0,0,1,1,2],[0,1,0,1,1,0,1,2,2,3],[1,2,1,0,1,0,1,2,3,4]]
}
