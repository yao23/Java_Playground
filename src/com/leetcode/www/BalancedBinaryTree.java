package com.leetcode.www;

public class BalancedBinaryTree { // LC 110
    /**
     * beats 63.97%
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return height(root) != -1;

    }
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = height(node.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = height(node.right);
        if (rightHeight == -1) {
            return -1;
        }
        if (leftHeight - rightHeight < -1 || leftHeight - rightHeight > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean isBalancedV0(TreeNode root) { // beats 5.02%
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (Math.abs(heightV0(root.left) - heightV0(root.right)) > 1) {
            return false;
        }
        return (isBalancedV0(root.left) && isBalancedV0(root.right));
    }
    private int heightV0(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }
}
