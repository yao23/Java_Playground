package com.leetcode.www;

/**
 * Created by liyao on 7/3/17.
 */
public class ClimbingStairs { // LC 70
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Climbing Stairs.
     * Memory Usage: 32 MB, less than 60.15% of Java online submissions for Climbing Stairs.
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        return climbV1(n);
    }

    private int climbV1(int n) {
        if (n <= 2) {
            return n;
        } else {
            int res = 0, first = 1, second = 2;

            for (int i = 2; i < n; i++) {
                res = first + second;
                first = second;
                second = res;
            }

            return res;
        }
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Climbing Stairs.
     * Memory Usage: 32.2 MB, less than 59.64% of Java online submissions for Climbing Stairs.
     *
     * @param n
     * @return
     */
    private int climbV0(int n) {
        if (n <= 2) {
            return n;
        } else {
            int res[] = new int[n];
            res[0] = 1;
            res[1] = 2;

            for (int i = 2; i < n; i++) {
                res[i] = res[i - 1] + res[i - 2];
            }

            return res[n - 1];
        }
    }

    /**
     * 1 => 1
     * 2 => 2
     * 3 => 3
     * 4 => 5
     * 5 => 8
     * 44 => 1134903170
     *
     * beats 12.92%
     *
     * @param n
     * @return
     */
    private int climb(int n) { // Time Limit Exceeded for n = 44
        if (n <= 2) {
            return n;
        } else {
            return climb(n - 1) + climb(n - 2);
        }
    }


}
