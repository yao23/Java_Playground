public class WordSearch { // LC 79
    public boolean exist(char[][] board, String word) { // beats 69.70%
        int row = board.length;
        if (row == 0) {
            return false;
        }

        int col = board[0].length;
        if (col == 0) {
            return false;
        }

        boolean[] res = new boolean[1];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                dfs(board, word, 0, r, c, res);
                if (res[0]) {
                    return true;
                }
            }
        }

        return false;
    }

    private void dfs(char[][] board, String word, int depth, int r, int c, boolean[] res) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(depth) || res[0]) {
            return;
        }
        if (depth == word.length() - 1) {
            res[0] = true;
            return;
        }

        char origVal = board[r][c];
        board[r][c] = '#'; // visited

        // search in left, right, up, down
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            dfs(board, word, depth + 1, r + dx[i], c + dy[i], res);
        }

        board[r][c] = origVal; // update back to original value
    }
}

// [
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
// ]

// word = "ABCCED", -> returns true,
// word = "SEE", -> returns true,
// word = "ABCB", -> returns false.
