package com.leetcode.www;

public class CircularArrayLoop { // LC 457
    /**
     * Runtime: 20 ms, faster than 38.02% of Java online submissions for Circular Array Loop.
     * Memory Usage: 33.4 MB, less than 100.00% of Java online submissions for Circular Array Loop.
     *
     * similar like find cycle in linked list
     *
     * @param nums
     * @return
     */
    public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean isForward = nums[i] >= 0;
            int slow = i, fast = i;
            do {
                slow = getNextIndex(nums, isForward, slow);
                fast = getNextIndex(nums, isForward, fast);
                if (fast != -1) {
                    fast = getNextIndex(nums, isForward, fast);
                }
            } while (slow != -1 && fast != -1 && slow != fast);

            if (slow != -1 && slow == fast) {
                return true;
            }
        }
        return false;
    }

    private int getNextIndex(int[] nums, boolean isForward, int currentIndex) {
        int currentNum = nums[currentIndex];
        boolean direction = currentNum >= 0;
        if (isForward != direction) { // different direction
            return -1;
        }
        int nextIndex = (currentIndex + currentNum) % nums.length;
        if (nextIndex < 0) {
            nextIndex += nums.length; // wrap around for negative numbers
        }
        if (nextIndex == currentIndex) { // one element cycle
            return -1;
        }
        return nextIndex;
    }
}
