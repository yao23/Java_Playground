package com.leetcode.www;

public class ValidateBinarySearchTree { // LC 98
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        // check current level
        if (root.val >= max || root.val <= min) {
            return false;
        }
        // recurse down
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }
}

// [2,1,3], return true.
// [1,2,3], return false.

// beats 94.19%
