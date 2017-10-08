package com.leetcode.www;

public class SecondMinimumNodeInABinaryTree { // LC 671
    private int[] res = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};

    public int findSecondMinimumValue(TreeNode root) { // beats 45.82%
        if (root == null) {
            return -1;
        }
        if (root.left == null && root.right == null) {
            return -1;
        }
        helper(root);
        if (res[0] != Integer.MAX_VALUE && res[1] != Integer.MAX_VALUE) {
            return res[1];
        } else {
            return -1;
        }
    }

    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.val < res[0]) {
            res[0] = root.val;
        } else if (root.val < res[1] && root.val > res[0]) {
            res[1] = root.val;
        }
        helper(root.left);
        helper(root.right);
    }
}
