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

    public int findRadius2(int[] houses, int[] heaters) {
        if(houses == null || houses.length == 0 || heaters == null || heaters.length == 0){
            return 0;
        }

        Arrays.sort(houses);
        Arrays.sort(heaters);

        int n = houses.length;
        int m = heaters.length;

        int i = 0;
        int j = 0;

        int res = 0;
        while(i < n && j < m){
            // for each heaters[j],  compare to heaters[j + 1]
            int dist1 = Math.abs(heaters[j] - houses[i]);
            int dist2 = Integer.MAX_VALUE;
            if(j + 1 < m){
                dist2 = Math.abs(heaters[j + 1] - houses[i]);
            }
            // if j closer to i than j + 1, settle and i ++;
            if(dist1 < dist2){
                res = Math.max(res, dist1);
                i++;
            }else{
                j++;
            }
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