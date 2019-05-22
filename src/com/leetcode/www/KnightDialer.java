package com.leetcode.www;

import java.util.Arrays;

public class KnightDialer { // LC 935
    /**
     * Runtime: 19 ms, faster than 87.07% of Java online submissions for Knight Dialer.
     * Memory Usage: 34.8 MB, less than 69.75% of Java online submissions for Knight Dialer.
     *
     * https://leetcode.com/problems/knight-dialer/discuss/189230/Java-Straightforward-Simulation-DP-Whatever
     *
     * cur[i]: how many ways to to get to position i.
     * next[0] = cur[4] + cur[6]: if the next position is 0, it could from position 4 or position 6. Thus we add them up.
     *
     * For DP, we should try to get the next state based on the current known state.
     *
     * https://hackernoon.com/google-interview-questions-deconstructed-the-knights-dialer-f780d516f029
     *
     * @param N
     * @return
     */
    public int knightDialer(int N) {
        if (N == 1) {
            return 10;
        }
        long[] cur = new long[10];
        Arrays.fill(cur, 1);
        cur[5] = 0;
        long res = 0, M = (int)(1e9 + 7);

        while (N-- > 1) {
            long[] next= new long[10];
            next[0] = (cur[4] + cur[6]) % M;
            next[1] = (cur[6] + cur[8]) % M;
            next[2] = (cur[7] + cur[9]) % M;
            next[3] = (cur[4] + cur[8]) % M;
            next[4] = (cur[3] + cur[9] + cur[0]) % M;
            next[6] = (cur[1] + cur[7] + cur[0]) % M;
            next[7] = (cur[2] + cur[6]) % M;
            next[8] = (cur[1] + cur[3]) % M;
            next[9] = (cur[2] + cur[4]) % M;
            cur = next;
        }
        for (long n: cur) {
            res= (res + n) % M;
        }
        return (int)res;
    }

    /**
     * Runtime: 51 ms, faster than 37.64% of Java online submissions for Knight Dialer.
     * Memory Usage: 36.6 MB, less than 60.20% of Java online submissions for Knight Dialer.
     *
     * The problem can be transformed into:
     * Traverse a directed graph (each node with a number as label and edges are defined by Knight's moving rule)
     * Start from 0 to 9
     * Move N - 1 step
     * Return how many ways to reach the end
     *
     * Easy to come up with a DFS solution to start traversal from 0 to 9
     * In each recursion, move to one of the current node's neighbors and the remain step becomes N-1
     * Stop recursion when N == 0
     *
     * Optimization:
     * Observe the recursive problem. The variances are:
     *
     * Current Node
     * Remain Steps
     * Therefore, we can store these two variables as the memo to speed up DFS (then it's a Top Down DP)
     *
     * https://leetcode.com/problems/knight-dialer/discuss/189271/Java-Top-Down-Memo-DP-O(N)
     */
    public static final int MOD = 1000000007;
    public int knightDialerV0(int N) {
        int[][] graph = new int[][]{{4,6},{6,8},{7,9},{4,8},{3,9,0},{},{1,7,0},{2,6},{1,3},{2,4}};
        int cnt = 0;
        Integer[][] memo = new Integer[N+1][10];
        for (int i = 0; i <= 9; i++) {
            cnt = (cnt + helper(N - 1, i, graph, memo)) % MOD;
        }
        return cnt;
    }
    private int helper(int N, int cur, int[][] graph, Integer[][] memo) {
        if (N == 0) {
            return 1;
        }
        if (memo[N][cur] != null) {
            return memo[N][cur];
        }
        int cnt = 0;
        for (int nei : graph[cur]) {
            cnt = (cnt + helper(N - 1, nei, graph, memo)) % MOD;
        }
        memo[N][cur] = cnt;
        return cnt;
    }
}
