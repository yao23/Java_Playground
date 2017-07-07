/**
 * Created by liyao on 7/6/17.
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;

        for (int i = 0; i < row / 2; i++) {
            for (int j = i; j < col - 1; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[row - 1 - i][j];
                matrix[row - 1 - i][j] = matrix[row - 1 - i][col - 1 - j];
                matrix[row - 1 - i][col - 1 - j] = matrix[i][col - 1 - j];
                matrix[i][col - 1 - j] = tmp;
            }
        }
    }

    public void rotateV0(int[][] matrix) {
       int row = matrix.length, col = matrix[0].length;

       int[][] result = new int[row][col];
       for (int i = 0; i < row; i++) {
           for (int j = 0; j < col; j++) {
               result[j][col - 1 - i] = matrix[i][j];
           }
       }

       for (int i = 0; i < row; i++) {
           for (int j = 0; j < col; j++) {
               matrix[i][j] = result[i][j];
           }
       }
    }

    // [[1]] => [[1]]
    // [[1,2],[3,4]] => [[3,1],[4,2]]
    // [[1,2,3],[4,5,6],[7,8,9]] => [[7,4,1],[8,5,2],[9,6,3]]
    // [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]] => [[13,9,5,1],[14,10,6,2],[15,11,7,3],[16,12,8,4]]
}
