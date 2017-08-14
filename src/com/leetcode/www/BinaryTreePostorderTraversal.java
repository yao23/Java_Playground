package com.leetcode.www;

import java.util.*;

public class BinaryTreePostorderTraversal { // LC 145
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;

        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                res.addFirst(p.val); // reverse the process of preorder
                p = p.right; // reverse the process of preorder
            } else {
                TreeNode node = stack.pop();
                p = node.left; // reverse the process of preorder
            }
        }

        return res;
    }
}

// beats 45.67%