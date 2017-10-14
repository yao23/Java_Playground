package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;

public class VerifyPreorderSequenceInBinarySearchTree { // LC 255
    // in place (given array)
    public boolean verifyPreorder(int[] preorder) { // beats 80.90%
        int low = Integer.MIN_VALUE, i = -1;
        for (int p : preorder) {
            if (p < low) {
                return false;
            }
            while (i >= 0 && p > preorder[i]) {
                low = preorder[i--];
            }
            preorder[++i] = p;
        }
        return true;
    }

    // stack
    public boolean verifyPreorderV0(int[] preorder) { // beats 77.05%
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
