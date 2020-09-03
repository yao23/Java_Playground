package com.leetcode.www;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RussianDollEnvelopes { // LC 354 [Google]
    public int maxEnvelopes(int[][] envelopes) {
        int num = envelopes.length;
        if (num == 0) {
            return 0;
        }
        List<Envelope> list = new ArrayList<>(num);
        for (int[] envelope : envelopes) {
            list.add(new Envelope(envelope[0], envelope[1]));
        }
        Collections.sort(list);
        int[] dp = new int[num];
        Arrays.fill(dp, 1);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                Envelope cur = list.get(i);
                Envelope pre = list.get(j);
                if (cur.w > pre.w && cur.h > pre.h) { // test case 2, same w
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    class Envelope implements Comparable<Envelope> {
        int w;
        int h;
        public Envelope(int w, int h) {
            this.w = w;
            this.h = h;
        }
        public int compareTo(Envelope that) {
            if (this.w < that.w) {
                return -1;
            } else if (this.w == that.w) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}

// [[5,4],[6,4],[6,7],[2,3]] => 3 (([2,3] => [5,4] => [6,7]))
// [[4,5],[4,6],[6,7],[2,3],[1,1]] => 4

// beats 43.59%