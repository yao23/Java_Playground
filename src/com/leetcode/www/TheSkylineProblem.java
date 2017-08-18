package com.leetcode.www;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class TheSkylineProblem { // LC 218
    public List<int[]> getSkyline(int[][] buildings) { // beats 53.35%
        List<int[]> res = new ArrayList<>();
        if (buildings.length == 0 || buildings[0].length == 0) {
            return res;
        }
        List<HeightPoint> heightPoints = new ArrayList<>();
        for (int[] building : buildings) {
            heightPoints.add(new HeightPoint(building[0], building[2], true));
            heightPoints.add(new HeightPoint(building[1], building[2], false));
        }
        Collections.sort(heightPoints);
        PriorityQueue<Integer> pq = new PriorityQueue<>(heightPoints.size(), Collections.reverseOrder());
        int pre = 0;
        for (HeightPoint heightPoint : heightPoints) {
            if (heightPoint.isStart) {
                pq.offer(heightPoint.height);
            } else {
                pq.remove(heightPoint.height);
            }

            int val = pq.isEmpty() ? 0 : pq.peek();
            if (val != pre) {
                pre = val;
                res.add(new int[]{heightPoint.index, val});
            }
        }
        return res;
    }

    class HeightPoint implements Comparable<HeightPoint> {
        int index;
        int height;
        boolean isStart;
        public HeightPoint(int index, int height, boolean isStart) {
            this.index = index;
            this.height = height;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(HeightPoint that) {
            if (this.index < that.index) {
                return -1;
            } else if (this.index > that.index) {
                return 1;
            } else {
                if (this.isStart && that.isStart) { // [1,3], [1,2], [1,1] in test case 2
                    if (this.height < that.height) {
                        return 1;
                    } else if (this.height > that.height) {
                        return -1;
                    } else {
                        return 0;
                    }
                } else if (this.isStart && !that.isStart) {
                    return -1;
                } else if (!this.isStart && that.isStart) {
                    return 1;
                } else {
                    if (this.height < that.height) { // [2,1], [2,2], [2,3] in test case 2
                        return -1;
                    } else if (this.height > that.height) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        }
    }
}

// [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]  => [[2 10],[3 15],[7 12],[12 0],[15 10],[20 8],[24, 0]]
// [[1,2,1],[1,2,2],[1,2,3]] => [[1,3],[2,0]]
