package com.leetcode.www;

import java.util.HashSet;
import java.util.Set;

public class FrogJump { // LC 403
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) {
            return false;
        }
        int n = stones.length;
        if (n < 3) {
            return n == 1 || (n == 2 && stones[1] == 1);
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (i > 3 && stones[i] > stones[i - 1] + i) { // 1, 2, 4, 7, ......
                return false;
            }
            set.add(stones[i]);
        }
        return canReach(set, 1, 1, stones[n -1]);
    }

    private boolean canReach(Set<Integer> set, int cur, int jump, int last) {
        if (last >= cur + jump - 1 && last <= cur + jump + 1) {
            return true;
        }

        // gready, k + 1 || k || k - 1, set.contains() indicate land on a stone or not
        return set.contains(cur + jump + 1) && canReach(set, cur + jump + 1, jump + 1, last) ||
                set.contains(cur + jump) && canReach(set, cur + jump, jump, last) ||
                jump > 1 && set.contains(cur + jump - 1) && canReach(set, cur + jump - 1, jump - 1, last);
    }


    /**
     *
     * Failure test case since failed to handle stone 6 (should skip from 5 to 8 directly)
     * Input: [0,1,3,5,6,8,12,17]
     * Output: false
     * Expected: true
     *
     * @param stones
     * @return
     */
    public boolean canCrossV2(int[] stones) {
        if (stones == null || stones.length == 0) {
            return false;
        }
        int n = stones.length;
        if (n == 1 || n == 2 && stones[1] == 1) {
            return true;
        }
        int step = 1;
        for (int i = 1; i < n; i++) {
            int last = stones[i - 1], cur = stones[i];
            if (cur == last + step + 1) {
                step += 1;
            } else if (cur == last + step || cur < last + step - 1) {
                continue;
            } else if (cur == last + step - 1) {
                step -= 1;
            } else {
                return false;
            }
        }
        return true;
    }
}

// [0,1,3,5,6,8,12,17] => true ([1,1,0,1,0,1,1,0,1,0,0,0,1,0,0,0,0,1])

// There are a total of 8 stones.
// The first stone at the 0th unit, second stone at the 1st unit,
// third stone at the 3rd unit, and so on...
// The last stone at the 17th unit.
//
// Return true. The frog can jump to the last stone by jumping
// 1 unit to the 2nd stone, then 2 units to the 3rd stone, then
// 2 units to the 4th stone, then 3 units to the 6th stone,
// 4 units to the 7th stone, and 5 units to the 8th stone.

// [0,1,2,3,4,8,9,11] => false
// [0,1,3,4,5,7,9,10,12] => true

// beats 98.63%