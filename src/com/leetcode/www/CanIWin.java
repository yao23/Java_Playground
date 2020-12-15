package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class CanIWin { // LC 464
    /**
     * https://discuss.leetcode.com/topic/68773/java-solution
     *
     * beats 98.05%
     *
     * @param n
     * @param total
     * @return
     */
    public boolean canIWin(int n, int total) {
        if (total <= 1) {
            return true;
        }
        if ((1 + n) * n / 2 < total) {
            return false;
        }
        Boolean[] map = new Boolean[1 << n];
        return canWin(total, n, 0, map);
    }

    private boolean canWin(int remains, int n, int status, Boolean[] map) {
        if (map[status] != null) {
            return map[status];
        }
        for (int i = n; i >= 1; i--) {
            int bit = 1 << (i - 1);
            if ((status & bit) == 0) {
                if (i >= remains) {
                    map[status] = true;
                    return true;
                }

                status ^= bit;
                boolean opWin = canWin(remains - i, n, status, map);
                status ^= bit;
                if (!opWin) {
                    map[status] = true;
                    return true;
                }
            }
        }
        map[status] = false;
        return false;
    }

    Map<Integer, Boolean> map;
    boolean[] used;

    /**
     * https://discuss.leetcode.com/topic/68896/java-solution-using-hashmap-with-detailed-explanation/2
     *
     * beats 71.26%
     *
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWinV1(int maxChoosableInteger, int desiredTotal) {
        int sum = (1+maxChoosableInteger)*maxChoosableInteger/2;
        if(sum < desiredTotal) return false;
        if(desiredTotal <= 0) return true;

        map = new HashMap<>();
        used = new boolean[maxChoosableInteger+1];
        return helper(desiredTotal);
    }

    public boolean helper(int desiredTotal){
        if(desiredTotal <= 0) return false;
        int key = format(used);
        if(!map.containsKey(key)){
            // try every unchosen number as next step
            for(int i=1; i<used.length; i++){
                if(!used[i]){
                    used[i] = true;
                    // check whether this lead to a win (i.e. the other player lose)
                    if(!helper(desiredTotal-i)){
                        map.put(key, true);
                        used[i] = false;
                        return true;
                    }
                    used[i] = false;
                }
            }
            map.put(key, false);
        }
        return map.get(key);
    }

    /**
     * transfer boolean[] to an Integer
     *
     * @param used
     * @return
     */
    private int format(boolean[] used){
        int num = 0;
        for(boolean b: used){
            num <<= 1;
            if(b) num |= 1;
        }
        return num;
    }

    /**
     * https://discuss.leetcode.com/topic/68792/java-easy-strightforward-solution-with-explanation
     *
     */
    Map<Integer, Boolean> set[];
    public boolean canIWinV0(int maxChoosableInteger, int desiredTotal) { // beats 22.78%
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
