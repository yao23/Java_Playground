package com.leetcode.www;

/**
 * Created by liyao on 7/8/17.
 */
public class GuessNumberHigherOrLower {
    private static int myNum = 6;

    public int guessNumber(int n) {
        int left = 1, right = n;
        while (true) {
            int mid = left + (right - left) / 2;
            int result = guess(mid);
            if (result == 0) { // find target
                return mid;
            } else if (result == -1) { // target is lower, go to left half
                right = mid;
            } else { // target is higher, go to right half
                left = mid + 1;
            }
        }
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

    // n = 10, return 6

    // beats 16.85%
}
