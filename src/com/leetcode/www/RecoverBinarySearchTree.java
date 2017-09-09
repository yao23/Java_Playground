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

    /**
     *
     * @param root
     * 
     * https://discuss.leetcode.com/topic/29161/share-my-solutions-and-detailed-explanation-with-recursive-iterative-in-order-traversal-and-morris-traversal
     * https://discuss.leetcode.com/topic/8925/share-my-java-solution-using-morris-traversal
     */
    public void recoverTreeV0(TreeNode root) { // beats 26.89%
        TreeNode pre = null;
        TreeNode first = null, second = null;

        // Morris Traversal
        TreeNode temp = null;

        while (root != null) {
            // If left is not null, we need to find the rightmost node of left subtree,
            // Set its right child to current node
            if (root.left != null) {
                // connect threading for root
                temp = root.left;

                //find the rightmost
                while (temp.right != null && temp.right != root) {
                    temp = temp.right;
                }

                //There are two cases,
                //null: first time we access current, set node.right to current and move to left child of current and continue;
                //current: we accessed current before, thus we've finished traversing left subtree, set node.right back to null;

                if (temp.right != null) { // the threading already exists
                    if (pre != null && pre.val > root.val) {
                        if (first == null) {
                            first = pre;
                            second = root;
                        } else {
                            second = root;
                        }
                    }
                    pre = root;

                    temp.right = null;
                    root = root.right;
                } else { // construct the threading
                    temp.right = root;
                    root = root.left;
                }
            } else {
                if (pre != null && pre.val > root.val) {
                    if (first == null) { // first time we encounter reversed order, we set previous node to first
                        first = pre;
                        second = root; //In case that two nodes are successive, we set second to current every time.
                    } else {
                        second = root; //In case that two nodes are successive, we set second to current every time.
                    }
                }
                pre = root;
                root = root.right;
            }
        }
        // swap two node values
        if (first != null && second != null) {
            int t = first.val;
            first.val = second.val;
            second.val = t;
        }
    }
}

// [0,1] => [1,0]