/**
 * Created by liyao on 7/16/17.
 */
public class TicTacToe { // LC 348
    private int n;
    private int[] rows; // row number occupied by players
    private int[] cols; // col number occupied by players
    private int diagonal; // diagonal number occupied by players
    private int antiDiagonal; // anti-diagonal number occupied by players

    /**
     * Initialize your data structure here.
     */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        antiDiagonal = 0;
        this.n = n;
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     *
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either:
     * 0: No one wins.
     * 1: Player 1 wins.
     * 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        if (player == 1) {
            rows[row]++;
            cols[col]++;
            if (row == col) {
                diagonal++;
            }
            if (row + col == n - 1) {
                antiDiagonal++;
            }
            if (rows[row] == n || cols[col] == n || diagonal == n || antiDiagonal == n) {
                return 1;
            } else {
                return 0;
            }
        } else {
            rows[row]--;
            cols[col]--;
            if (row == col) {
                diagonal--;
            }
            if (row + col == n - 1) {
                antiDiagonal--;
            }
            if (rows[row] == -n || cols[col] == -n || diagonal == -n || antiDiagonal == -n) {
                return 2;
            } else {
                return 0;
            }
        }
    }

    // ["TicTacToe","move","move","move","move","move","move","move"], [[3],[0,0,1],[0,2,2],[2,2,1],[1,1,2],[2,0,1],[1,0,2],[2,1,1]] => [null,0,0,0,0,0,0,1]

    // beats 95.30%
}