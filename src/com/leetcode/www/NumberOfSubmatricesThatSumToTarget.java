import java.util.HashMap;
import java.util.Map;

public class NumberOfSubmatricesThatSumToTarget { // 1074 [Google]
    /**
     * Number of Subarrays that Sum to Target: Horizontal 1D Prefix Sum
     *
     * Time complexity: O(R^2 C), where R is the number of rows and C is the number of columns.
     * Space complexity: O(RC) to store 2D prefix sum.
     */
    class Solution {
        public int numSubmatrixSumTarget(int[][] matrix, int target) {
            int r = matrix.length, c = matrix[0].length;

            // compute 2D prefix sum
            int[][] ps = new int[r + 1][c + 1];
            for (int i = 1; i < r + 1; ++i) {
                for (int j = 1; j < c + 1; ++j) {
                    ps[i][j] = ps[i - 1][j] + ps[i][j - 1] - ps[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }

            int count = 0, currSum;
            Map<Integer, Integer> h = new HashMap();
            // reduce 2D problem to 1D one
            // by fixing two rows r1 and r2 and
            // computing 1D prefix sum for all matrices using [r1..r2] rows
            for (int r1 = 1; r1 < r + 1; ++r1) {
                for (int r2 = r1; r2 < r + 1; ++r2) {
                    h.clear();
                    h.put(0, 1);
                    for (int col = 1; col < c + 1; ++col) {
                        // current 1D prefix sum
                        currSum = ps[r2][col] - ps[r1 - 1][col];

                        // add subarrays which sum up to (currSum - target)
                        count += h.getOrDefault(currSum - target, 0);

                        // save current prefix sum
                        h.put(currSum, h.getOrDefault(currSum, 0) + 1);
                    }
                }
            }

            return count;
        }
    }

    /**
     * Time complexity: O(RC^2), where R is the number of rows and C is the number of columns.
     *
     * Space complexity: O(RC) to store 2D prefix sum.
     *
     * @param matrix
     * @param target
     * @return
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int r = matrix.length, c = matrix[0].length;

        // compute 2D prefix sum
        int[][] ps = new int[r + 1][c + 1];
        for (int i = 1; i < r + 1; ++i) {
            for (int j = 1; j < c + 1; ++j) {
                ps[i][j] = ps[i - 1][j] + ps[i][j - 1] - ps[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }

        int count = 0, currSum;
        Map<Integer, Integer> h = new HashMap();
        // reduce 2D problem to 1D one
        // by fixing two columns c1 and c2 and
        // computing 1D prefix sum for all matrices using [c1..c2] columns
        for (int c1 = 1; c1 < c + 1; ++c1) {
            for (int c2 = c1; c2 < c + 1; ++c2) {
                h.clear();
                h.put(0, 1);
                for (int row = 1; row < r + 1; ++row) {
                    // current 1D prefix sum
                    currSum = ps[row][c2] - ps[row][c1 - 1];

                    // add subarrays which sum up to (currSum - target)
                    count += h.getOrDefault(currSum - target, 0);

                    // save current prefix sum
                    h.put(currSum, h.getOrDefault(currSum, 0) + 1);
                }
            }
        }

        return count;
    }
}
