package com.leetcode.www;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecoverBinarySearchTree { // LC 99
    /**
     *
     * @param root
     *
     * Two elements of a binary search tree (BST) are swapped by mistake.
     * Recover the tree without changing its structure.
     */
    public void recoverTree(TreeNode root) { // beats 4.70%
        if (root == null) {
            return;
        }
        List<TreeNode> nodes = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        inorderTraversal(root, nodes, values);
        Collections.sort(values);

        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).val = values.get(i);
        }
    }
    private void inorderTraversal(TreeNode root, List<TreeNode> nodes, List<Integer> values) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inorderTraversal(root.left, nodes, values);
        }
        nodes.add(root);
        values.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right, nodes, values);
        }
    }

    public void recoverTree2(TreeNode root) { // beats 26.89%
        if (root == null) {
            return;
        }
        TreeNode f1 = null, f2 = null;
        TreeNode cur, pre, parent = null;
        boolean found = false;
        cur = root;

        while (cur != null) {
            if (cur.left == null) {
                if (parent != null && parent.val > cur.val) {
                    if (!found) {
                        f1 = parent;
                        found = true;
                    }
                    f2 = cur;
                }
                parent = cur;
                cur = cur.right;
            } else {
                pre = cur.left;

                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    if (parent.val > cur.val) {
                        if (!found) {
                            f1 = parent;
                            found = true;
                        }
                        f2 = cur;
                    }
                    parent = cur;
                    cur = cur.right;
                }
            }
        }

        if (f1 != null && f2 != null) {
            int tmp = f1.val;
            f1.val = f2.val;
            f2.val = tmp;
        }
    }
}
