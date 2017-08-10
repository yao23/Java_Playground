package com.leetcode.www;

public class Candy { // LC 135
    public int candy(int[] ratings) { // beats 26.73%
        if (ratings == null || ratings.length == 0) {
            return -1;
        }

        int len = ratings.length;
        int[] left = new int[len];
        int[] right = new int[len];

        left[0] = 1;
        right[len - 1] = 1;

        // scan from left
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        // scan from right
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += Math.max(left[i], right[i]);
        }
        return sum;
    }
}

// [0] => 1
// [0,4,5,5,2,1,0,0] => 17