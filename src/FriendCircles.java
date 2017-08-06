public class FriendCircles {
    private UFElement[][] matrix;
    private int numCircles;
    public int findCircleNum(int[][] M) {
        numCircles = 0;
        int row = M.length;
        if (row == 0) {
            return numCircles;
        }
        int col = M[0].length;
        matrix = new UFElement[row][col];
        initMatrix(M, row, col);


        return numCircles;
    }

    private void updateMatrix(int[][] M, int row, int col) {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                matrix[r][c] = new UFElement(r, c);
            }
        }
    }

    private void initMatrix(int[][] M, int row, int col) {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                matrix[r][c] = new UFElement(r, c);
            }
        }
    }

    private UFElement compressedFind(int r, int c) {
        UFElement parent = matrix[r][c];

        while (UFElement.isEqual(parent, matrix[parent.getRow()][parent.getCol()])) {
            parent = matrix[parent.getRow()][parent.getCol()];
        }

        UFElement curParent = matrix[r][c];
        while (UFElement.isEqual(curParent, matrix[curParent.getRow()][curParent.getCol()])) {
            UFElement tmp = matrix[curParent.getRow()][curParent.getCol()];
            matrix[curParent.getRow()][curParent.getCol()] = parent; // update to top parent
            curParent = tmp;
        }

        return parent;
    }

    private UFElement find(int r, int c) {
        UFElement parent = matrix[r][c];

        while (UFElement.isEqual(parent, matrix[parent.getRow()][parent.getCol()])) {
            parent = matrix[parent.getRow()][parent.getCol()];
        }

        return parent;
    }

    private void union(int r1, int c1, int r2, int c2) {
        UFElement parent1 = compressedFind(r1, c1);
        UFElement parent2 = compressedFind(r2, c2);
        if (!UFElement.isEqual(parent1, parent2)) {
            if (parent1.getRow() < parent2.getRow()) {
                matrix[r2][c2] = parent1;
            } else if (parent1.getRow() == parent2.getRow()) {
                if (parent1.getCol() < parent2.getCol()) {
                    matrix[r2][c2] = parent1;
                } else {
                    matrix[r1][c1] = parent2;
                }
            } else {
                matrix[r1][c1] = parent2;
            }

            numCircles++;
        }
    }

}


// Union Find Element
class UFElement {
    private int row;
    private int col;

    public UFElement(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static boolean isEqual(UFElement one, UFElement two) {
        return (one.row == two.row && one.col == two.col);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}