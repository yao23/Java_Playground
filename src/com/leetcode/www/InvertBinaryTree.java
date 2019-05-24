package com.leetcode.www;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by liyao on 6/5/17.
 */
public class    InvertBinaryTree { // LC 226
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Invert Binary Tree.
     * Memory Usage: 33.7 MB, less than 99.05% of Java online submissions for Invert Binary Tree.
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Invert Binary Tree.
     * Memory Usage: 33.6 MB, less than 99.05% of Java online submissions for Invert Binary Tree.
     *
     * BFS
     *
     * @param root
     * @return
     */
    public TreeNode invertTreeV1(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            // swap children first
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            // add children
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return root;
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Invert Binary Tree.
     * Memory Usage: 33.4 MB, less than 99.05% of Java online submissions for Invert Binary Tree.
     *
     * @param root
     * @return
     */
    public TreeNode invertTreeV0(TreeNode root) {
        if (root == null) {
            return root;
        }

        if (root.left == null && root.right == null) {
            return root;
        }

        invertHelper(root.left, root.right, root);

        return root;
    }

    private void swap(TreeNode left, TreeNode right, TreeNode root) {
        TreeNode tmp = right;
        root.right = root.left;
        root.left = tmp;
    }

    private void invertHelper(TreeNode left, TreeNode right, TreeNode root) {
        if (left == null && right == null) { // left and right are null
            return;
        } else { // left or right is not null
            if (left != null) { // left is not null
                invertHelper(left.left, left.right, left);
            }
            if (right != null) { // right is not null
                invertHelper(right.left, right.right, right);
            }

            swap(left, right, root);
        }
    }

    // []
    // [4,2,7,1,3,6,9]
    // [1,2,3,4,null,null,5]
    // [1,2,3,null,4,null,5]
    // [1,2,3,null,4,null,5,6,null,null,8]

    // beats 29.61%

}
