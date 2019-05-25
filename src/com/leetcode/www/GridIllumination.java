package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class GridIllumination { // LC 1001
    /**
     * Runtime: 83 ms, faster than 96.24% of Java online submissions for Grid Illumination.
     * Memory Usage: 72.2 MB, less than 24.71% of Java online submissions for Grid Illumination.
     *
     * https://leetcode.com/problems/grid-illumination/discuss/276604/Java-clear-code-thought-process
     *
     * @param N
     * @param lamps
     * @param queries
     * @return
     */

    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> horizontal = new HashMap<>();
        Map<Integer, Integer> vertical = new HashMap<>();
        Map<Integer, Integer> positiveSlope = new HashMap<>();
        Map<Integer, Integer> negativeSlope = new HashMap<>();

        Map<Long, Boolean> lampsMap = new HashMap<>(); // <location hash key, whether lamp is turned on>
        for (int[] l : lamps) {
            Location loc = new Location(l[0], l[1]);
            lampsMap.put(loc.toHashkey(), true);
            horizontal.put(loc.y, 1 + horizontal.getOrDefault(loc.y, 0));
            vertical.put(loc.x, 1 + vertical.getOrDefault(loc.x, 0));
            positiveSlope.put(loc.x + loc.y, 1 + positiveSlope.getOrDefault(loc.x + loc.y, 0));
            negativeSlope.put(loc.x - loc.y, 1 + negativeSlope.getOrDefault(loc.x - loc.y, 0));
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            Location loc = new Location(queries[i][0], queries[i][1]);
            if (horizontal.getOrDefault(loc.y, 0) > 0
                    || vertical.getOrDefault(loc.x, 0) > 0
                    || positiveSlope.getOrDefault(loc.x + loc.y, 0) > 0
                    || negativeSlope.getOrDefault(loc.x - loc.y, 0) > 0) {
                ans[i] = 1;

                for (int[] dir : dirs) {
                    Location next = new Location(loc.x + dir[0], loc.y + dir[1]);

                    if (next.x >= 0 && next.y >= 0 && next.x < N && next.y < N) {
                        long hashKey = next.toHashkey();
                        if (!lampsMap.getOrDefault(hashKey, false)) continue;

                        lampsMap.put(hashKey, false);
                        horizontal.put(next.y, horizontal.get(next.y) - 1);
                        vertical.put(next.x, vertical.get(next.x) - 1);
                        positiveSlope.put(next.x + next.y, positiveSlope.get(next.x + next.y) - 1);
                        negativeSlope.put(next.x - next.y, negativeSlope.get(next.x - next.y) - 1);
                    }
                }
            }
        }
        return ans;
    }

    private int[][] dirs = new int[][]{
            {0, 0},
            {0, 1},
            {0, -1},
            {1, 0},
            {1, 1},
            {1, -1},
            {-1, 0},
            {-1, -1},
            {-1, 1}
    };

    private class Location {
        int x;
        int y;

        Location(int i, int j) {
            this.x = i;
            this.y = j;
        }

        long toHashkey() {
            long ans = this.x;
            return (ans << 30) | this.y;
        }
    }
}
