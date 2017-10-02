package com.leetcode.www;

public class FindTheCelebrity { // LC 277
    /* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */
    private boolean knows(int a, int b) {
        return true;
    }

    public int findCelebrity(int n) { // beats 46.85%
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i != candidate && (knows(candidate, i) || !knows(i, candidate))) { // i and candidate don't know each other
                return -1;
            }
        }
        return candidate;
    }
}
