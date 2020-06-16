package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections { // 986 (Facebook)
    /**
     * Runtime: 3 ms, faster than 73.31% of Java online submissions for Interval List Intersections.
     * Memory Usage: 40.4 MB, less than 97.30% of Java online submissions for Interval List Intersections.
     *
     * @param A
     * @param B
     * @return
     */
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> tmp = new ArrayList<>();
        int i = 0, j = 0, lenA = A.length, lenB = B.length;
        while (i < lenA && j < lenB) {
            int sA = A[i][0], eA = A[i][1], sB = B[j][0], eB = B[j][1];
            if (sA > eB) {
                j++;
            } else if (sB > eA) {
                i++;
            } else {
                tmp.add(new int[]{Math.max(sA, sB), Math.min(eA, eB)});
                if (eA <= eB) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        if (tmp.size() > 0) {
            int[][] res = new int[tmp.size()][2];
            for (int k = 0; k < tmp.size(); k++) {
                res[k] = tmp.get(k);
            }
            return res;
        } else {
            return new int[0][0];
        }
    }
}
