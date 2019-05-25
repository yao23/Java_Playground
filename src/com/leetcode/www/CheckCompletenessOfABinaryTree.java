package com.leetcode.www;

public class CheckCompletenessOfABinaryTree { // LC 958
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Check Completeness of a Binary Tree.
     * Memory Usage: 35.6 MB, less than 96.65% of Java online submissions for Check Completeness of a Binary Tree.
     *
     * The complete binary tree can be represented in an array with no gaps
     *
     * https://leetcode.com/problems/check-completeness-of-a-binary-tree/discuss/298725/0-ms-Faster-than-100-java-solutions
     *
     * @param root
     * @return
     */
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        int count = getCount(root); // number of nodes
        int maxIndex = getMaxIndex(root,1); // max index of nodes
        return count == maxIndex;
    }

    private int getCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + getCount(root.left) + getCount(root.right);
    }

    private int getMaxIndex(TreeNode root, int prev) {
        if (root.left == null && root.right == null) {
            return prev;
        }
        int res = prev;
        if (root.left != null) {
            res = Math.max(res, getMaxIndex(root.left,2 * prev));
        }
        if (root.right != null) {
            res = Math.max(res, getMaxIndex(root.right,2 * prev + 1));
        }
        return res;
    }
}
