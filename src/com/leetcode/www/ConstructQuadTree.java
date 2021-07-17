package com.leetcode.www;

/**
 *  Definition for a QuadTree node.
 */
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};

class Solution {
    /**
     * Runtime: 1 ms, faster than 99.95% of Java online submissions for Construct Quad Tree.
     * Memory Usage: 41.5 MB, less than 68.97% of Java online submissions for Construct Quad Tree.
     *
     * https://leetcode.com/problems/construct-quad-tree/discuss/279486/Java-beats-100-recursion-solution
     *
     * check same section by compare values with start grid element, no extra space use
     *
     * @param grid
     * @return
     */

    public Node construct(int[][] grid) {
        return construct(grid,0, 0, grid.length) ;
    }

    private Node construct(int[][] grid, int startRow, int startCol, int n)  {
        if (sameSection(grid, startRow, startCol, n)) {
            return new Node(grid[startRow][startCol] == 1,true,null,null,null,null);
        } else{
            int halfLength = n / 2;
            Node topLeft = construct(grid, startRow, startCol, halfLength) ;
            Node topRight = construct(grid, startRow, startCol + halfLength, halfLength);
            Node bottomLeft = construct(grid,startRow + halfLength,startCol, halfLength);
            Node bottomRight = construct(grid,startRow + halfLength,startCol + halfLength, halfLength);
            return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
        }
    }

    private boolean sameSection(int[][] sect, int startRow, int startCol, int n) {
        if (n == 1) {
            return true;
        }

        for (int i = startRow; i < startRow + n; i++) {
            for (int j = startCol; j < startCol + n; j++) {
                if (sect[startRow][startCol] != sect[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Runtime: 8 ms, faster than 6.00% of Java online submissions for Construct Quad Tree.
     * Memory Usage: 44.3 MB, less than 45.98% of Java online submissions for Construct Quad Tree.
     *
     * the following link is similar like my proposed one (bottom up recursive as following code)
     * https://leetcode.com/problems/construct-quad-tree/discuss/286829/Java-recursive
     *
     * @param grid
     * @return
     */
    public Node construct0(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new Node();
        } else {
            int n = grid.length;
            if (n == 1) {
                return new Node(grid[0][0] == 1, true, null, null, null, null);
            } else {
                int mid = n / 2;
                Node topLeft = construct(getSubGrid(0, 0, grid));
                Node topRight = construct(getSubGrid(0, mid, grid));
                Node bottomLeft = construct(getSubGrid(mid, 0, grid));
                Node bottomRight = construct(getSubGrid(mid, mid, grid));
                if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf) {
                    if (topLeft.val == topRight.val && topRight.val == bottomLeft.val &&
                            bottomLeft.val == bottomRight.val) { // sub grids are same
                        return new Node(topLeft.val, true, null, null, null, null);
                    } else { // sub grids are different
                        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
                    }
                } else { // sub grids are different
                    return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
                }
            }
        }
    }

    private int[][] getSubGrid(int row, int col, int[][] grid) {
        int len = grid.length / 2;
        int[][] subGrid = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) { // System.arraycopy(grid[row + i], col, subGrid[i], 0, len);
                subGrid[i][j] = grid[row + i][col + j];
            }
        }
        return subGrid;
    }
}