/**
 * Created by liyao on 6/16/17.
 */
public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) { // []
            return false;
        }
        int col = matrix[0].length;
        if (col == 0) { // [[]]
            return false;
        }
        int start = 0, end = row * col - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int midElem = matrix[mid / col][mid % col];
            if (midElem == target) {
                return true;
            } else if (midElem < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        int startElem = matrix[start / col][start % col];
        if (startElem == target) {
            return true;
        }
        int endElem = matrix[end / col][end % col]; // [[1,1]], 0, java.lang.ArrayIndexOutOfBoundsException: 1, if row instead of col
        if (endElem == target) {
            return true;
        } else {
            return false;
        }
    }

    // [
    //    [1,   3,  5,  7],
    //    [10, 11, 16, 20],
    //    [23, 30, 34, 50]
    //  ]

    // Given target = 3, return true.

    // [[1,3,5,7],[10,11,16,20],[23,30,34,50]],3 => true
    // [] => false
    // [[]] => false
    // [[1,1]], 0 => false

    // beats 6.80%
}
