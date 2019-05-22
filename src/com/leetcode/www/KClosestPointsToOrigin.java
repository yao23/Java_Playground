package com.leetcode.www;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin { // LC 973
    /**
     * Runtime: 4 ms, faster than 99.63% of Java online submissions for K Closest Points to Origin.
     * Memory Usage: 51.5 MB, less than 98.98% of Java online submissions for K Closest Points to Origin.
     *
     * Quick Sort - average O(N), worst case is O(N^2)
     *
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K) {
                break;
            }
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) { // find one less than pivot in right
                r--;
            }
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) { // find one greater than pivot in left
                l++;
            }
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }

    /**
     * Runtime: 55 ms, faster than 37.01% of Java online submissions for K Closest Points to Origin.
     * Memory Usage: 56.9 MB, less than 93.02% of Java online submissions for K Closest Points to Origin.
     *
     * Arrays.sort() - O(NlogN)
     *
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosestV1(int[][] points, int K) {
        Arrays.sort(points, (p1, p2) -> p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]);
        return Arrays.copyOfRange(points, 0, K);
    }
    /**
     * Runtime: 63 ms, faster than 24.70% of Java online submissions for K Closest Points to Origin.
     * Memory Usage: 63.4 MB, less than 25.38% of Java online submissions for K Closest Points to Origin.
     *
     * Max Heap - O(NlogK)
     *
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosestV0(int[][] points, int K) {
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
