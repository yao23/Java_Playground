package com.leetcode.www;

public class GasStation { // LC 134
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null) {
            return -1;
        }
        if (gas.length != cost.length) {
            return -1;
        }

        int start = gas.length - 1;
        int end = 0;
        int sum = gas[start] - cost[start];

        while (end < start) {
            if (sum < 0) { // a new start needed
                start--;
                sum += (gas[start] - cost[start]); // need more petrol in the start
            } else { // try to move more
                sum += (gas[end] - cost[end]);
                end++;
            }
        }

        return (sum >= 0) ? start : -1;
    }
}

// gas: [3, 4, 3, 6, 7, 1, 2], cost: [2, 4, 5, 1, 5, 1, 3] => 3 (gas: 6)
// [4], [5] => -1

// beats 57.95%