package com.leetcode.www;

public class LowestCommonAncestorOfABinaryTree { // LC 236
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else {
            return (left == null) ? right : left;
        }
    }
}

// beats 59.36%
