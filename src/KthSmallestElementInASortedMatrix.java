/**
 * Created by liyao on 6/26/17.
 */
public class KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }
        int col = matrix[0].length;
        if (col == 0) {
            return 0;
        }
        if (k <= row * col) {
            return matrix[k / col][k % col - 1];
        } else {
            return matrix[row - 1][col - 1];
        }
    }
}
