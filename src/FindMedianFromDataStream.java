import java.util.ArrayDeque;

/**
 * Created by liyao on 6/25/17.
 */
import java.util.PriorityQueue;

public class FindMedianFromDataStream {
    public class MedianFinder { // class MedianFinder in LC
        private PriorityQueue<Integer> minQueue; // right half
        private PriorityQueue<Integer> maxQueue; // left half

        /** initialize your data structure here. */
        public MedianFinder() {
            minQueue = new PriorityQueue<>();
            maxQueue = new PriorityQueue<>();
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
                    minQueue.offer(maxQueue.peek());
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
                        if (num > maxQueue.peek()) {
                            minQueue.offer(num);
                        } else {
                            maxQueue.offer(num);
                            minQueue.offer(maxQueue.poll()); // update to make "balance" (diff as 0 or 1) for 2 stacks
                        }
                    } else { // minQueue has more
                        if (num > maxQueue.peek()) {
                            minQueue.offer(num);
                            maxQueue.offer(minQueue.poll()); // update to make "balance" (diff as 0 or 1) for 2 stacks
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
                return (maxHead + (minHead - maxHead) / 2.00); // need double, cannot use >>>
            } else {
                if (minLen < maxLen) {
                    return maxQueue.peek();
                } else {
                    return minQueue.peek();
                }
            }
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
