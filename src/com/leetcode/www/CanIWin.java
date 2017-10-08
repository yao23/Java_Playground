package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class CanIWin { // LC 464
    Map<Integer, Boolean> set[];
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) { // beats 22.78%
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        if (maxChoosableInteger + 1 >= desiredTotal) {
            return false;
        }
        set = new Map[301];
        for (int i = 0 ; i < 301; i++) {
            set[i] = new HashMap<>();
        }
        if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) {
            return false;
        }
        return canWin((1 << maxChoosableInteger + 1) - 1, desiredTotal);
    }

    private boolean canWin(int set1, int total){
        if (set[total].containsKey(set1)) {
            return set[total].get(set1);
        }
        for (int i = 20; i >= 1; i--) {
            int p = (1 << i);
            if ((p&set1) == p) {
                int set1next = (set1 ^ p);
                int totalNext = total - i;
                if (totalNext <= 0) {
                    return true;
                }
                boolean x;
                if (set[totalNext].containsKey(set1next)) {
                    x = set[totalNext].get(set1next);
                } else {
                    x = canWin(set1next, totalNext);
                }
                if (!x) {
                    set[total].put(set1, true);
                    return true;
                }
            }
        }
        set[total].put(set1, false);
        return false;
    }
}
