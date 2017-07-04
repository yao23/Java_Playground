/**
 * Created by liyao on 7/3/17.
 */
public class ZeroOneMatrix {
    public int[][] updateMatrix(int[][] matrix) {
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
                int[] neighbors = new int[4];
                neighbors[0] = search(x - 1, y, matrix); // up
                neighbors[1] = search(x + 1, y, matrix); // down
                neighbors[2] = search(x, y - 1, matrix); // left
                neighbors[3] = search(x, y + 1, matrix); // right

                int min = Integer.MAX_VALUE;
                for (int i = 0; i < neighbors.length; i++) {
                    if (neighbors[i] >= 0 && neighbors[i] < min) {
                        min = neighbors[i];
                    }
                }

                return 1 + min;
            }
        }
    }
}
