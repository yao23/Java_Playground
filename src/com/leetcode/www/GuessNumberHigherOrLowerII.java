package com.leetcode.www;

public class GuessNumberHigherOrLowerII { // LC 375
    private static int myNum = 8;

    public int getMoneyAmount(int n) {
        int sum = 0;
        int left = 1, right = n;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int res = guess(mid);
            if (res == 0) {
                return sum;
            } else if (res < 0) {
                sum += mid;
                right = mid;
            } else {
                sum += mid;
                left = mid + 1;
            }
        }
        if (guess(left) == 0) {
            sum += left;
        }

        return sum;
    }

    /* The guess API is defined in the parent class GuessGame.
       @param num, your guess
       @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
          int guess(int num); */
    private int guess(int n) {
        if (myNum < n) {
            return -1;
        } else if (myNum == n) {
            return 0;
        } else {
            return 1;
        }
    }
}

// n = 10, I pick 8. => 21 (5 + 7 + 9)