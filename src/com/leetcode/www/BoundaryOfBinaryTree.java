package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class BoundaryOfBinaryTree { // LC 545
    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Boundary of Binary Tree.
     * Memory Usage: 37.5 MB, less than 97.70% of Java online submissions for Boundary of Binary Tree.
     *
     * https://leetcode.com/problems/boundary-of-binary-tree/discuss/280001/Java-solution-beats-100
     *
     * @param root
     * @return
     */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.add(root.val);
        if (root.left == null && root.right == null) {
            return res;
        }
        helperLeft(root.left, res);
        helperBottom(root, res);
        helperRight(root.right, res);
        return res;
    }

    private void helperLeft(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        res.add(root.val);
        if (root.left != null) {
            helperLeft(root.left, res);
        } else {
            helperLeft(root.right, res);
        }
    }

    private void helperRight(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        if (root.right != null) {
            helperRight(root.right, res);
        } else {
            helperRight(root.left, res);
        }
        res.add(root.val); // anti-clockwise
    }

    private void helperBottom(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return;
        }
        helperBottom(root.left, res);
        helperBottom(root.right, res);
    }
}
