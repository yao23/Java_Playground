package com.leetcode.www;

import java.util.PriorityQueue;

public class KClosestPointsToOrigin { // LC 973
    /**
     * Runtime: 63 ms, faster than 24.70% of Java online submissions for K Closest Points to Origin.
     * Memory Usage: 63.4 MB, less than 25.38% of Java online submissions for K Closest Points to Origin.
     *
     * Max Heap
     *
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]
        );
        for (int[] p : points) {
            pq.offer(p);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        int[][] res = new int[K][2];
        while (K > 0) {
            res[--K] = pq.poll();
        }
        return res;
    }
}
