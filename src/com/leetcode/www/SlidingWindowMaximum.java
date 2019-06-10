package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum { // LC 239
    /**
     * Runtime: 11 ms, faster than 56.06% of Java online submissions for Sliding Window Maximum.
     * Memory Usage: 41.5 MB, less than 74.71% of Java online submissions for Sliding Window Maximum.
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len == 0 || k == 0) {
            return new int[]{};
        } else if (k == 1) {
            return nums;
        }
        int[] results = new int[len - k + 1];
        Deque<Integer> queue = new ArrayDeque<>(); // index queue (descending order)

        for (int cur = 0; cur < results.length; cur++) {

            removePassedNums(queue, cur);

            // 1st window need to process all k elements
            // only need to process last element from 2nd window (prev k-1 elements have been processed)
            int start = (cur == 0) ? 0 : k - 1;

            for (int offset = start; offset < k && cur + offset < len; offset++) {
                int idx = cur + offset, curNum = nums[idx];
                if (queue.isEmpty() || curNum < nums[queue.getLast()]) {
                    queue.addLast(idx);
                } else {
                    while (!queue.isEmpty() && curNum >= nums[queue.getLast()]) {
                        queue.removeLast();
                    }
                    queue.addLast(idx);
                }
            }

            if (!queue.isEmpty()) {
                results[cur] = nums[queue.getFirst()];
            }
        }

        return results;
    }

    private void removePassedNums(Deque<Integer> queue, int curIdx) {
        while (!queue.isEmpty() && queue.getFirst() < curIdx) {
            queue.removeFirst();
        }
    }

    // [],0 => []
    // [1,3,-1,-3,5,3,6,7],3 => [3,3,5,5,6,7]
    // [5,3,4],1 => [5,3,4]

    // beats 0.20% (start offset from 0 and process k elements every time, offset = 0)
    // beats 87.55% (start offset from 0 only for 1st window and process k elements, then start offset from k-1 and only process 1 element from 2nd window
}
