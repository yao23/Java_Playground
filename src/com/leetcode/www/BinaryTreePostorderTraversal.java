package com.leetcode.www;

import java.util.*;

public class BinaryTreePostorderTraversal { // LC 145
    /**
     * Runtime: 1 ms, faster than 49.35% of Java online submissions for Binary Tree Postorder Traversal.
     * Memory Usage: 34 MB, less than 98.78% of Java online submissions for Binary Tree Postorder Traversal.
     *
     * beats 45.67%
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                res.addFirst(cur.val); // reverse the process of preorder
                cur = cur.right; // reverse the process of preorder
            } else {
                TreeNode node = stack.pop();
                cur = node.left; // reverse the process of preorder
            }
        }

        return res;
    }
}