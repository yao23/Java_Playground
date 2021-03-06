package com.leetcode.www;

public class BattleshipsInABoard { // LC 419
    /**
     * Runtime: 1 ms, faster than 98.22% of Java online submissions for Battleships in a Board.
     * Memory Usage: 40 MB, less than 90.64% of Java online submissions for Battleships in a Board.
     *
     * https://leetcode.com/problems/battleships-in-a-board/discuss/297094/Java-single-pass-O(1)-memory-1ms-execution-time
     *
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        int count = 0;

        boolean in = false;

        // scan first row
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'X') { // separate with battleships in previous column
                if (!in) {
                    in = true;
                    count++;
                }
            } else {
                in = false;
            }
        }

        // scan the rest
        for (int i = 1; i < board.length; i++) {
            in = false;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X' && board[i-1][j] != 'X') { // separate with battleships in previous row
                    if (!in) {
                        in = true;
                        count++;
                    }
                } else {
                    in = false;
                }

            }
        }

        return count;
    }

    /**
     * Runtime: 1 ms, faster than 98.22% of Java online submissions for Battleships in a Board.
     * Memory Usage: 40.6 MB, less than 89.47% of Java online submissions for Battleships in a Board.
     *
     * https://leetcode.com/problems/battleships-in-a-board/discuss/296699/Java-Single-Pass-Constant-Space-Solution
     *
     * @param board
     * @return
     */
    public int countBattleshipsV3(char[][] board) {
        int count = 0;
        for (int r = 0; r < board.length; r++) {
            boolean found = false;
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c]=='X' && !found && (r == 0 || board[r-1][c] != 'X')) {
                    found = true;
                    count++;
                } else if (board[r][c]=='.' && found) {
                    found = false;
                }
            }
        }
        return count;
    }

    /**
     * Runtime: 1 ms, faster than 98.22% of Java online submissions for Battleships in a Board.
     * Memory Usage: 42 MB, less than 70.75% of Java online submissions for Battleships in a Board.
     *
     * https://leetcode.com/problems/battleships-in-a-board/discuss/283948/Simple-Java-easy-solution-(Backtracking)
     * Backtracking
     *
     * @param board
     * @return
     */
    public int countBattleshipsV2(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'X' && !visited[i][j]) {
                    helper(board, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }

    private void helper(char[][] board, int i, int j, boolean[][] visited){
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '.' || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        helper(board,i + 1, j, visited);
        helper(board,i - 1, j, visited);
        helper(board, i,j + 1, visited);
        helper(board, i,j - 1, visited);
    }

    /**
     * Runtime: 1 ms, faster than 98.22% of Java online submissions for Battleships in a Board.
     * Memory Usage: 40.5 MB, less than 89.54% of Java online submissions for Battleships in a Board.
     *
     * @param board
     * @return
     */
    public int countBattleshipsV1(char[][] board) { // recursive
        int count = 0;
        int row = board.length;
        int col = board[0].length;
        int mark[][] = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mark[i][j] != -1 && board[i][j] == 'X') {
                    count++;
                    mark(mark, i, j, board);
                } else {
                    mark[i][j] = -1;
                }
            }
        }
        return count;
    }

    private void mark(int[][] mark, int x, int y, char[][] board) {
        mark[x][y] = -1;
        if (y < mark[x].length - 1 && board[x][y + 1] == 'X') { // Right
            mark(mark, x, y + 1, board);
        } else if(x < mark.length - 1 && board[x + 1][y] == 'X') { // Bottom
            mark(mark, x + 1, y, board);
        }
    }

    /**
     * Runtime: 1 ms, faster than 98.22% of Java online submissions for Battleships in a Board.
     * Memory Usage: 40.7 MB, less than 88.96% of Java online submissions for Battleships in a Board.
     *
     * https://leetcode.com/problems/battleships-in-a-board/discuss/293482/Java-simple-solutions
     *
     * @param board
     * @return
     */
    public int countBattleshipsV0(char[][] board) { // iterative
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == 'X'){
                    count++;
                    if(j > 0 && board[i][j - 1] == 'X')
                        count--;
                    else if(i > 0 && board[i - 1][j] == 'X')
                        count--;
                }
            }
        }
        return count;
    }
}
