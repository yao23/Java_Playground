/**
 * Created by liyao on 6/24/17.
 */
public class SurroundedRegions {
    private void dfs(int x, int y, char[][] board) {
        int row = board.length, col = board[0].length;
        if (x < 0 || x >= row || y < 0 || y >= col || board[x][y] == 'X'|| board[x][y] == 'V') { // out of bounds or invalid point
            return;
        } else { // inside valid point
            board[x][y] = 'V'; // update as visited
            dfs(x - 1, y, board); // up row
            dfs(x + 1, y, board); // bottom row
            dfs(x, y - 1, board); // left col
            dfs(x, y + 1, board); // right col
        }
    }
    
    public void solve(char[][] board) {
        int row = board.length;
        if (row == 0) { // zero row
            return;
        }
        int col = board[0].length;
        if (col == 0) { // zero col
            return;
        }

        // each col in top row
        for (int i = 0; i < col; i++) {
            if (board[0][i] == 'O') {
                dfs(0, i, board);
            } else {
                continue;
            }
        }

        // each col in bottom row
        for (int i = 0; i < col; i++) {
            if (board[row - 1][i] == 'O') {
                dfs(row - 1, i, board);
            } else {
                continue;
            }
        }

        // left col in each row
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {
                dfs(i, 0, board);
            } else {
                continue;
            }
        }

        // right col in each row
        for (int i = 0; i < row; i++) {
            if (board[i][col - 1] == 'O') {
                dfs(i, col - 1, board);
            } else {
                continue;
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X'; // update flipped O as X
                } else if (board[i][j] == 'V') {
                    board[i][j] = 'O';
                } else {
                    continue;
                }
            }
        }
    }

    // [] => []
    // ["XXXX"] => ["XXXX"]
    // ["XOOX"] => ["XOOX"]
    // ["XXOX"] => ["XXOX"]
    // ["XXOX","XOXX"] => ["XXOX","XOXX"]
    // ["XXXX","XOOX","XXOX","XOXX"] => ["XXXX","XXXX","XXXX","XOXX"]
}
