package com.leetcode.www;

import java.util.*;

public class BinaryTreePostorderTraversal { // LC 145
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

// beats 45.67%