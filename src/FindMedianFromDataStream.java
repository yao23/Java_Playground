import java.util.ArrayDeque;

/**
 * Created by liyao on 6/25/17.
 */
import java.util.Deque;
import java.util.ArrayDeque;

public class FindMedianFromDataStream {
    public class MedianFinder { // class MedianFinder in LC
        private Deque<Integer> minStack; // right half
        private Deque<Integer> maxStack; // left half

        /** initialize your data structure here. */
        public MedianFinder() {
            minStack = new ArrayDeque<>();
            maxStack = new ArrayDeque<>();
        }

        public void addNum(int num) {
            int minLen = minStack.size();
            int maxLen = maxStack.size();

            if (minLen == 0 && maxLen == 0) { // maxStack and minStack have no num
                maxStack.push(num);
            } else if (minLen == 0) { // maxStack has 1 num
                if (num >= maxStack.peek()) {
                    minStack.push(num);
                } else {
                    minStack.push(maxStack.peek());
                    maxStack.push(num);
                }
            } else if (maxLen == 0) { // minStack has 1 num
                if (num <= minStack.peek()) {
                    maxStack.push(num);
                } else {
                    maxStack.push(minStack.pop());
                    minStack.push(num);
                }
            } else { // maxStack and minStack have more than 1 num
                if (minLen == maxLen) { // min and max stack sizes are same
                    if (num > maxStack.peek()) {
                        minStack.push(num);
                    } else {
                        maxStack.push(num);
                    }
                } else { // min and max stack sizes are different
                    if (minLen < maxLen) { // maxStack has more
                        if (num > maxStack.peek()) {
                            minStack.push(num);
                        } else {
                            maxStack.push(num);
                            minStack.push(maxStack.pop()); // update to make "balance" (diff as 0 or 1) for 2 stacks
                        }
                    } else { // minStack has more
                        if (num > maxStack.peek()) {
                            minStack.push(num);
                            maxStack.push(minStack.pop()); // update to make "balance" (diff as 0 or 1) for 2 stacks
                        } else {
                            maxStack.push(num);
                        }
                    }
                }
            }
        }

        public double findMedian() {
            int minLen = minStack.size();
            int maxLen = maxStack.size();

            if (minLen == maxLen) {
                return (minStack.peek() + maxStack.peek()) >>> 1;
            } else {
                if (minLen < maxLen) {
                    return maxStack.peek();
                } else {
                    return minStack.peek();
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
