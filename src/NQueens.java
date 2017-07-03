/**
 * Created by liyao on 7/2/17.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n == 0) {
            return result;
        } else {
            int preQueens[] = new int[n];
            Arrays.fill(preQueens, -1);
            helper(0, preQueens, n, result);

            return result;
        }
    }

    private void helper(int row, int[] preQueens, int n, List<List<String>> result) {
        if (row == n) {
            List<String> queens = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (preQueens[i] == j) { // position of Q
                        sb.append("Q");
                    } else { // empty
                        sb.append(".");
                    }
                }
                queens.add(sb.toString());
            }
            result.add(queens);
        } else {
            for (int col = 0; col < n; col++) {
                if (check(row, col, preQueens)) {
                    preQueens[row] = col;
                    helper(row + 1, preQueens, n, result);
                    preQueens[row] = -1;
                }
            }
        }
    }

    private boolean check(int row, int col, int[] preQueens) {
        // check queens in same column
        for (int i = 0; i < row; i++) {
            if (preQueens[i] == col) {
                return false;
            }
        }
        // check queens in same diagonal line
        for (int i = row - 1, j = 1; i >= 0; i--, j++) {
            if (i >= 0 && preQueens[i] + j < preQueens.length && preQueens[i] + j == col ) {
                return false;
            } else if (i >= 0 && preQueens[i] - j >= 0 && preQueens[i] - j == col) {
                return false;
            }
        }

        return true;
    }
}