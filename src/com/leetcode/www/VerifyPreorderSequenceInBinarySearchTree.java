package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;

public class VerifyPreorderSequenceInBinarySearchTree { // LC 255
    // Divide and Conquer
    public boolean verifyPreorder(int[] preorder) { // beats 9.49
        if (preorder == null || preorder.length == 0) {
            return true;
        }
        return verify(preorder, 0, preorder.length - 1);
    }

    private boolean verify(int[] a, int start, int end) {
        if (start >= end) {
            return true;
        }
        int pivot = a[start];
        int bigger = -1;
        for (int i = start + 1; i <= end; i++) {
            if (bigger == -1 && a[i] > pivot) {
                bigger = i;
            }
            if (bigger != -1 && a[i] < pivot) {
                return false;
            }
        }
        if (bigger == -1) {
            return verify(a, start + 1, end);
        } else {
            return verify(a, start + 1, bigger - 1) && verify(a, bigger, end);
        }
    }

    // in place (given array)
    public boolean verifyPreorderV1(int[] preorder) { // beats 80.90%
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
