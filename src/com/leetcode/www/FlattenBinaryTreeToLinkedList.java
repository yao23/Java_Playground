package com.leetcode.www;

public class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode tmp = root;
        if (tmp.left == null) {
            return; // flattening is done
        } else {
            tmp = tmp.left;
        }

        while (tmp.right != null) {
            tmp = tmp.right;
        }

        tmp.right = root.right;
        root.right = root.left;
        root.left = null;
    }
}
