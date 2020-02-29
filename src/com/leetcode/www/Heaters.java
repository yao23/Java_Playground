package com.leetcode.www;

import java.util.Arrays;

public class Heaters { // LC 475
    /**
     * 1) Input are house and heaters positions, difference is cover radius
     * 2) Search current heater cover range as much as possible
     * 3) If current heater has shorter radius than next heater, then go right to next house, otherwise go to next heater
     *
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        int res = 0;
        if (houses == null || heaters == null) {
            return res;
        }
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int i = 0;
        int j = 0;

        while (i < houses.length) { // for each house find a heater

            // find next heater
            while (j < heaters.length - 1 && houses[i] - heaters[j] > heaters[j + 1] - houses[i]) {
                j++;
            }

            res = Math.max(res, Math.abs(heaters[j] - houses[i]));
            i++; // move to next house
        }

        return res;
    }
}

// Input: [1,2,3],[2]
// Output: 1
// Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.

// Input: [1,2,3,4],[1,4]
// Output: 1
// Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.

// beats 94.04%