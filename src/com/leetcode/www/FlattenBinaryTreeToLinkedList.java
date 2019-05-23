package com.leetcode.www;

public class FlattenBinaryTreeToLinkedList { // LC 114
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Flatten Binary Tree to Linked List.
     * Memory Usage: 38.7 MB, less than 23.96% of Java online submissions for Flatten Binary Tree to Linked List.
     *
     */
    private TreeNode prev = null;

    public void flatten(TreeNode root) { // beats 22.15%
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

    public void flattenV1(TreeNode root) { // beats 22.15% (iterative, based on Morris Traversal)
        TreeNode cur = root;
        TreeNode prev = null;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                prev = cur.left;
                while (prev.right != null) {
                    prev = prev.right;
                }
                prev.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
        }
    }

    public void flattenV0(TreeNode root) { // beats 13.88%
        if (root == null) {
            return;
        }
        flattenV0(root.left);
        flattenV0(root.right);
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
