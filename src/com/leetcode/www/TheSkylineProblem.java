package com.leetcode.www;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class TheSkylineProblem { // LC 218
    public List<int[]> getSkylineV1(int[][] buildings) { // beats 32.08% (https://discuss.leetcode.com/topic/28482/once-for-all-explanation-with-clean-java-code-o-n-2-time-o-n-space/2)
        List<int[]> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for(int[] b:buildings) {
            // start point has negative height value
            height.add(new int[]{b[0], -b[2]});
            // end point has normal height value
            height.add(new int[]{b[1], b[2]});
        }

        // sort $height, based on the first value, if necessary, use the second to
        // break ties
        Collections.sort(height, (a, b) -> {
            if(a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        // Use a maxHeap to store possible heights
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));

        // Provide a initial value to make it more consistent
        pq.offer(0);

        // Before starting, the previous max height is 0;
        int prev = 0;

        // visit all points in order
        for(int[] h:height) {
            if(h[1] < 0) { // a start point, add height
                pq.offer(-h[1]);
            } else {  // a end point, remove height
                pq.remove(h[1]);
            }
            int cur = pq.peek(); // current max height;

            // compare current max height with previous max height, update result and
            // previous max height if necessary
            if(prev != cur) {
                result.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return result;
    }

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
