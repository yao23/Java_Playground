package com.leetcode.www;

public class Candy { // LC 135
    public int candy(int[] ratings) { // beats 26.73% (1 pass, greedy), time: O(n), space: O(1)
        if (ratings == null || ratings.length == 0) {
            return -1;
        }

        int sum = 1;
        int pre = 1;
        int down = 0;

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                down++;
            } else {
                // check descending sequence before
                if (down > 0) {
                    // step 1: add from 1 to down
                    sum += (down * (down + 1) / 2); // 1 + 2 + ... + down
                    // step 2: add enough on prev
                    if (down >= pre) {
                        sum += (down - pre + 1);
                    }
                    pre = 1;
                    down = 0;
                }

                pre = (ratings[i] == ratings[i - 1]) ? 1 : (pre + 1);
                sum += pre;
            }
        }

        // check last descending sequence
        if (down > 0) {
            sum += (down * (down + 1) / 2);
            if (down >= pre) {
                sum += (down - pre + 1);
            }
        }

        return sum;
    }

    public int candyV0(int[] ratings) { // beats 26.73% (2 passes), time: O(n), space: O(n)
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