package com.leetcode.www; /**
 * Created by liyao on 7/12/17.
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class InorderSuccessor { // LC 285
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) { // from CLRS, beats 44.98%
        TreeNode res = null;
        while (root != null) {
            if (p.val < root.val) {
                res = root; // it might be the successor (smallest larger value)
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return res;
    }

    public TreeNode inorderSuccessorV1(TreeNode root, TreeNode p) { // beats 44.98%
        if (p.right != null) {
            p = p.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        TreeNode candidate = null;
        while (root != p) {
            root = (p.val > root.val) ? root.right : (candidate = root).left;
        }

        return candidate;
    }

    public TreeNode inorderSuccessorV0(TreeNode root, TreeNode p) {
        if (root == null || root.left == null && root.right == null) {
            return null;
        } else {
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode cur = root;

            while (cur != null && !stack.isEmpty()) {
                if (cur == null) { // cur is null
                    cur = stack.pop();
                    if (cur == p) {
                        if (cur.right == null) {
                            return stack.isEmpty() ? null : stack.peek();
                        } else {
                            TreeNode tmp = cur.right;
                            while (tmp.left != null) {
                                tmp = tmp.left;
                            }

                            return tmp;
                        }
                    } else {
                        cur = cur.right;
                    }
                } else { // cur is not null
                    if (cur.left != null && cur.left == p) {
                        return cur;
                    } else {
                        stack.push(cur);
                        cur = cur.left;
                    }
                }
            }

            return null;
        }
    }
}

/**
  *  Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
  *  Note: If the given node has no in-order successor in the tree, return null.
  **/