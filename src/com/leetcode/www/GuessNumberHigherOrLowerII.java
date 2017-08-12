package com.leetcode.www;

public class GuessNumberHigherOrLowerII { // LC 375
    public int getMoneyAmount(int n) { // beats 54.44%
        int[][] table = new int[n + 1][n + 1];
        return dp(table, 1, n);
    }

    private int dp(int[][] t, int start, int end){
        if (start >= end) {
            return 0;
        }
        if (t[start][end] != 0) {
            return t[start][end];
        }
        int res = Integer.MAX_VALUE;
        for (int x = start; x <= end; x++) {
            int tmp = x + Math.max(dp(t, start, x-1), dp(t, x+1, end));
            res = Math.min(res, tmp);
        }
        t[start][end] = res;
        return res;
    }
}

// n = 10, I pick 8. => 21 (5 + 7 + 9)

/**
 * For each number x in range[i~j]
 * we do: result_when_pick_x = x + max{DP([i~x-1]), DP([x+1, j])}
 * --> // the max means whenever you choose a number, the feedback is always bad and therefore leads you to a worse branch.
 * then we get DP([i~j]) = min{xi, ... ,xj}
 * --> // this min makes sure that you are minimizing your cost.
 */