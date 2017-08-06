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

        return numCircles;
    }

    private UFElement compressedFind(int r, int c) {
        UFElement parent = matrix[r][c];

        while (UFElement.isEqual(parent, matrix[parent.row][parent.col]) {
            parent = matrix[parent.row][parent.col];
        }

        UFElement curParent = matrix[r][c];
        while (UFElement.isEqual(curParent, matrix[curParent.row][curParent.col]) {
            UFElement tmp = matrix[curParent.row][curParent.col];
            matrix[curParent.row][curParent.col] = parent; // update to top parent
            curParent = tmp;
        }

        return parent;
    }

    private UFElement find(int r, int c) {
        UFElement parent = matrix[r][c];

        while (UFElement.isEqual(parent, matrix[parent.row][parent.col]) {
            parent = matrix[parent.row][parent.col];
        }

        return parent;
    }

    private void union(int r1, int c1, int r2, int c2) {
        UFElement parent1 = compressedFind(r1, c1);
        UFElement parent2 = compressedFind(r2, c2);
        if (!UFElement.isEqual(parent1, parent2)) {
            if (parent1.row < parent2.row) {
                matrix[r2][c2] = parent1;
            } else if (parent1.row == parent2.row) {
                if (parent1.col < parent2.col) {
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