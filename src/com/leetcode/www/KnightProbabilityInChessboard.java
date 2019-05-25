package com.leetcode.www;

public class KnightProbabilityInChessboard { // LC 688
    private static int xMove[] = {2, 1, -1, -2, -2, -1, 1, 2};
    private static int yMove[] = {1, 2, 2, 1, -1, -2, -2, -1};

    /**
     * Runtime: 2 ms, faster than 100.00% of Java online submissions for Knight Probability in Chessboard.
     * Memory Usage: 33 MB, less than 89.82% of Java online submissions for Knight Probability in Chessboard.
     *
     * https://leetcode.com/problems/knight-probability-in-chessboard/discuss/274216/Easy-understand-solution
     *
     * @param N
     * @param K
     * @param r
     * @param c
     * @return
     */
    public double knightProbability(int N, int K, int r, int c) {
        double[][][] sol = new double[N][N][K + 1];
        double res = helper(r, c, 0, N, K, sol);
        return res / Math.pow(8, K);
    }

    private static double helper(int x, int y, int move, int N, int K, double[][][] sol) {
        if (sol[x][y][move] != 0) return sol[x][y][move];
        double res = 0;
        if (move == K) {
            res++;
            return res;
        }
        for (int i = 0; i < 8; i++) {
            int nextX = x + xMove[i];
            int nextY = y + yMove[i];
            if (isSafe(nextX, nextY, N)) {
                res += helper(nextX, nextY, move + 1, N, K, sol);
            }
        }
        sol[x][y][move] = res;
        return res;
    }

    private static boolean isSafe(int x, int y, int N) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private int[][] dirs = new int[][]{
            {-1, -2},
            {-2, -1},
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2}
    };

    // Let F(r, c, K) be count of valid positions after K move from (r, c)
    // Assume F(r', c', k-1) works where (r', c') is next position of move
    // F(r, c, k) = sum(F(r', c', k-1)) for all valid (r', c')
    // Base case, k = 0 or move is out of boundary

    Double[][][] memo;
    int N;

    /**
     * Runtime: 3 ms, faster than 95.61% of Java online submissions for Knight Probability in Chessboard.
     * Memory Usage: 35 MB, less than 55.26% of Java online submissions for Knight Probability in Chessboard.
     *
     * https://leetcode.com/problems/knight-probability-in-chessboard/discuss/261802/Java-backtrack-detailed-comments
     *
     * Time - O(N^2 * K)
     * Space - O(N^2 * K)
     *
     * @param N
     * @param K
     * @param r
     * @param c
     * @return
     */
    public double knightProbabilityV1(int N, int K, int r, int c) {
        memo = new Double[N][N][K + 1];
        this.N = N;
        return backtrack(r, c, K) / Math.pow(8, K);
    }

    private double backtrack(int i, int j, int k) {
        if (i < 0 || j < 0 || i >= N || j >= N) {
            return 0.0;
        }

        if (k == 0) {
            return 1.0;
        }

        if (memo[i][j][k] != null) {
            return memo[i][j][k];
        }

        double count = 0.0;
        for (int[] dir : dirs) {
            int ii = i + dir[0];
            int jj = j + dir[1];

            count += backtrack(ii, jj, k - 1);
        }

        memo[i][j][k] = count;
        return count;
    }

    /**
     * Runtime: 2 ms, faster than 100.00% of Java online submissions for Knight Probability in Chessboard.
     * Memory Usage: 34.4 MB, less than 57.60% of Java online submissions for Knight Probability in Chessboard.
     *
     * https://leetcode.com/problems/knight-probability-in-chessboard/discuss/281494/My-AC-with-DP-only-2ms-beats-100
     *
     * @param N
     * @param K
     * @param r
     * @param c
     * @return
     */
    public double knightProbabilityV0(int N, int K, int r, int c) {
        double[][] dp = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = 1.0;
            }
        }
        for (int i = 1; i <= K; i++) {
            dp = bulidDp(dp, N);
        }
        return dp[r][c] / Math.pow(8.0, K);
    }

    private double[][] bulidDp(double[][] dp, int N) {
        double[][] tmpDp = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmpDp[i][j] = init(dp, N, i, j);
            }
        }
        return tmpDp;
    }

    private double init(double[][] dp, int N, int i, int j) {
        double res = 0;
        if (i + 1 < N && j + 2 < N) {
            res += dp[i + 1][j + 2];
        }
        if (i + 1 < N && j - 2 >= 0) {
            res += dp[i + 1][j - 2];
        }
        if (i - 1 >= 0 && j + 2 < N) {
            res += dp[i - 1][j + 2];
        }
        if (i - 1 >= 0 && j - 2 >= 0) {
            res += dp[i - 1][j - 2];
        }
        if (i + 2 < N && j + 1 < N) {
            res += dp[i + 2][j + 1];
        }
        if (i + 2 < N && j - 1 >= 0) {
            res += dp[i + 2][j - 1];
        }
        if (i - 2 >= 0 && j + 1 < N) {
            res += dp[i - 2][j + 1];
        }
        if (i - 2 >= 0 && j - 1 >= 0) {
            res += dp[i - 2][j - 1];
        }
        return res;
    }
}
