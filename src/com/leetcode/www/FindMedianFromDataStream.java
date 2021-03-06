package com.leetcode.www;

import java.util.ArrayDeque;

/**
 * Created by liyao on 6/25/17.
 */
import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromDataStream { // LC 295 [Google]
    public class MedianFinder { // class MedianFinder in LC
        private PriorityQueue<Integer> minQueue; // right half
        private PriorityQueue<Integer> maxQueue; // left half

        /** initialize your data structure here. */
        public MedianFinder() {
            maxQueue = new PriorityQueue<>(1, Collections.reverseOrder()); // descending order
            minQueue = new PriorityQueue<>(); // ascending order (default)
        }

        public void addNum(int num) {
            int minLen = minQueue.size();
            int maxLen = maxQueue.size();

            if (minLen == 0 && maxLen == 0) { // maxQueue and minQueue have no num
                maxQueue.offer(num);
            } else if (minLen == 0) { // maxQueue has 1 num
                if (num >= maxQueue.peek()) {
                    minQueue.offer(num);
                } else {
                    minQueue.offer(maxQueue.poll());
                    maxQueue.offer(num);
                }
            } else if (maxLen == 0) { // minQueue has 1 num
                if (num <= minQueue.peek()) {
                    maxQueue.offer(num);
                } else {
                    maxQueue.offer(minQueue.poll());
                    minQueue.offer(num);
                }
            } else { // maxQueue and minQueue have more than 1 num
                if (minLen == maxLen) { // min and max stack sizes are same
                    if (num > maxQueue.peek()) {
                        minQueue.offer(num);
                    } else {
                        maxQueue.offer(num);
                    }
                } else { // min and max stack sizes are different
                    if (minLen < maxLen) { // maxQueue has more
                        if (num >= maxQueue.peek()) {
                            minQueue.offer(num);
                        } else {
                            minQueue.offer(maxQueue.poll()); // update to make "balance" (diff as 0 or 1) for 2 stacks
                            maxQueue.offer(num);
                        }
                    } else { // minQueue has more
                        if (num >= minQueue.peek()) {
                            maxQueue.offer(minQueue.poll()); // update to make "balance" (diff as 0 or 1) for 2 stacks
                            minQueue.offer(num);
                        } else {
                            maxQueue.offer(num);
                        }
                    }
                }
            }
        }

        public double findMedian() {
            int minLen = minQueue.size();
            int maxLen = maxQueue.size();

            if (minLen == maxLen) {
                int minHead = minQueue.peek(), maxHead = maxQueue.peek();
                return (maxHead + (minHead - maxHead) / 2.00);
            } else {
                if (minLen < maxLen) {
                    return maxQueue.peek();
                } else {
                    return minQueue.peek();
                }
            }
        }
    }

    // ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
    // [[],[1],[2],[],[3],[]]
    // [null,null,null,1.50000,null,2.00000]

    // ["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
    // [[],[-1],[],[-2],[],[-3],[],[-4],[],[-5],[]]
    // [null,null,-1.00000,null,-1.50000,null,-2.00000,null,-2.50000,null,-3.00000]

    // ["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
    // [[],[12],[],[10],[],[13],[],[11],[],[5],[],[15],[],[1],[],[11],[],[6],[],[17],[],[14],[],[8],[],[17],[],[6],[],[4],[],[16],[],[8],[],[10],[],[2],[],[12],[],[0],[]]
    // [null,null,12.00000,null,11.00000,null,12.00000,null,11.50000,null,11.00000,null,11.50000,null,11.00000,null,11.00000,null,11.00000,null,11.00000,null,11.00000,null,11.00000,null,11.00000,null,11.00000,null,11.00000,null,11.00000,null,11.00000,null,10.50000,null,10.00000,null,10.50000,null,10.00000]

    // beats 44.40%
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
