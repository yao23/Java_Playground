package com.leetcode.www;

import java.util.LinkedList;
import java.util.Queue;

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

    /**
     * Runtime: 1 ms, faster than 95.20% of Java online submissions for Check Completeness of a Binary Tree.
     * Memory Usage: 35 MB, less than 96.86% of Java online submissions for Check Completeness of a Binary Tree.
     *
     * BFS
     *
     * https://leetcode.com/problems/check-completeness-of-a-binary-tree/discuss/281891/Java-Short-Solution-!!!
     *
     * @param root
     * @return
     */
    public boolean isCompleteTreeV0(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) {
            q.offer(root);
        }

        boolean end = false;
        while (!q.isEmpty()) {
            TreeNode t = q.poll();
            if (t == null) {
                end = true;
            } else {
                if(end) { // there are nodes after null in current level
                    return false;
                }
                q.offer(t.left);
                q.offer(t.right);
            }
        }
        return true;
    }
}
