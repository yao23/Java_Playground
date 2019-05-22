package com.leetcode.www;

import java.util.Arrays;

public class KnightDialer {
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
}
