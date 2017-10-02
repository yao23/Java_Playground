package com.leetcode.www;

public class FindTheCelebrity { // LC 277
    /* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */
    private boolean knows(int a, int b) {
        return true;
    }

    // The first loop is to exclude n - 1 labels that are not possible to be a celebrity.
    // After the first loop, x is the only candidate.
    // The second and third loop is to verify x is actually a celebrity by definition.

    // The key part is the first loop. To understand this you can think the knows(a,b) as a a < b comparison,
    // if a knows b then a < b, if a does not know b, a > b. Then if there is a celebrity, he/she must be the "maximum"
    // of the n people.

    // However, the "maximum" may not be the celebrity in the case of no celebrity at all. Thus we need the second and
    // third loop to check if x is actually celebrity by definition.

    // The total calls of knows is thus 3n at most. One small improvement is that in the second loop we only need to
    // check i in the range [0, x). You can figure that out yourself easily.

    public int findCelebrity(int n) { // beats 94.21%
        int x = 0;
        for (int i = 0; i < n; ++i) {
            if (knows(x, i)) {
                x = i;
            }
        }
        for (int i = 0; i < x; ++i) {
            if (knows(x, i)) {
                return -1;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (!knows(i, x)) {
                return -1;
            }
        }
        return x;
    }

    public int findCelebrityV0(int n) { // beats 46.85%
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
