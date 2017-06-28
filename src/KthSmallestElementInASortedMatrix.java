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
            int left = matrix[0][0], right = matrix[row - 1][col - 1];

            while (left < right) {
                int mid = left + (right - left) / 2;
                int count = 0, j = col - 1;
                for (int i = 0; i < row; i++) {
                    // get number of elements smaller or equal to mid in current row
                    while (j >= 0 && matrix[i][j] > mid) {
                        j--;
                    }

                    count += (j + 1);
                }
                if (count < k) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        } else {
            return matrix[row - 1][col - 1];
        }
    }

    // [[]] => 0
    // [[1]], 1 => 1
    // [[1,5],[10,11]],1 => 1
    // [[1,5],[10,11]],2 => 5
    // [[1,5],[10,11]],3 => 10
    // [[1,5],[10,11]],4 => 11
    // [[1,5,9],[10,11,13],[12,13,15]],8 => 13
    // [[1,2],[1,3]],2 => 1, not working
}
