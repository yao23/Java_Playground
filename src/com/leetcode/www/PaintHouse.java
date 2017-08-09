package com.leetcode.www;

/**
 * Created by liyao on 7/3/17.
 */
public class PaintHouse { // LC 256
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        } else {
            int row = costs.length;

            for (int i = 1; i < row; i++) {
                costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
                costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
                costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
            }

            return Math.min(costs[row - 1][0], Math.min(costs[row - 1][1], costs[row - 1][2]));
        }
    }

    public int minCostV0(int[][] costs) { // Time: O(3n), Space: O(3), beats 6.56%
        if (costs == null || costs.length == 0) {
            return 0;
        } else {
            int[] pre = new int[3];
            int[] cur = new int[3];

            for (int[] cost : costs) {
                for (int i = 0; i < 3; i++) {
                    cur[i] = cost[i] + Math.min(pre[(i + 1) % 3], pre[(i + 2) % 3]);
                }
                int[] tmp = cur;
                cur = pre;
                pre = tmp;
            }


            return Math.min(pre[0], Math.min(pre[1], pre[2]));
        }
    }

    // beats 51.97%
}

/**
 * cost[i][k]: min cost till house i, when paint with color k for i
 */