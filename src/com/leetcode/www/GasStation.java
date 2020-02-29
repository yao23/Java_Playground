package com.leetcode.www;

import java.util.PriorityQueue;

public class GasStation { // LC 134
    /**
     * gas: [3, 4, 3, 6, 7, 1, 2], cost: [2, 4, 5, 1, 5, 1, 3] => 3 (gas: 6)
     * [4], [5] => -1
     * beats 57.95%
     *
     * @param gas
     * @param cost
     * @return
     */
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

    /**
     * maxHeap store diff array index and poll index having most diff[i] every time
     *
     * Runtime: 85 ms, faster than 10.59% of Java online submissions for Gas Station.
     * Memory Usage: 39.6 MB, less than 5.88% of Java online submissions for Gas Station.
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuitV1(int[] gas, int[] cost) {
        int idx = -1;
        if (gas == null || cost == null) {
            return idx;
        }
        int gasL = gas.length, costL = cost.length;
        if (gasL < costL || gasL == 0) {
            return idx;
        }

        int[] diff = new int[gasL];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(gasL, (a, b) -> diff[b] - diff[a]);
        int maxDiff = 0;

        for (int i = 0; i < gasL; i++) {
            diff[i] = gas[i] - cost[i];
            if (diff[i] >= maxDiff) {
                idx = i;
                maxDiff = diff[i];
            }
            maxHeap.add(i);
        }

        if (idx < 0) {
            return idx;
        }

        while (maxHeap.size() > 0) {
            int i = maxHeap.poll();
            if (isValid(i, diff)) {
                return i;
            }
        }

        return -1;
    }

    private boolean isValid(int idx, int[] diff) {
        int sumDiff = 0, len = diff.length;
        for (int i = idx; i < idx + len; i++) {
            sumDiff += diff[i % len];
            if (sumDiff < 0) {
                return false;
            }
        }

        return sumDiff >= 0;
    }
}