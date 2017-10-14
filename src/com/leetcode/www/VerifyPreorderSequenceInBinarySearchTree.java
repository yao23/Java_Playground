package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;

public class VerifyPreorderSequenceInBinarySearchTree { // LC 255
    public boolean verifyPreorder(int[] preorder) { // beats 77.05%
        int low = Integer.MIN_VALUE;
        Deque<Integer> path = new ArrayDeque<>();
        for (int p : preorder) {
            if (p < low) {
                return false;
            }
            while (!path.isEmpty() && p > path.peek()) {
                low = path.pop();
            }
            path.push(p);
        }
        return true;
    }
}
