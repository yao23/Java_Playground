package com.leetcode.www;

import java.util.Deque;
import java.util.LinkedList;

public class MovingAverageFromDataStream { // LC 346
    class MovingAverage {
        /**
         * Runtime: 69 ms, faster than 89.77% of Java online submissions for Moving Average from Data Stream.
         * Memory Usage: 41.1 MB, less than 85.25% of Java online submissions for Moving Average from Data Stream.
         */

        /** Initialize your data structure here. */
        Deque<Integer> dq;
        int size;
        int sum;
        public MovingAverage(int size) {
            dq = new LinkedList<>();
            this.size = size;
            this.sum = 0;
        }

        public double next(int val) {
            if (dq.size() >= size) {
                int temp = dq.pollFirst();
                sum -= temp;
            }

            dq.addLast(val);
            sum += val;
            return (double)sum / (double)dq.size();
        }
    }

    /**
     * Your MovingAverage object will be instantiated and called as such:
     * MovingAverage obj = new MovingAverage(size);
     * double param_1 = obj.next(val);
     */
}
