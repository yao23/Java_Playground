package com.leetcode.www;

import java.util.List;

public class LowestCommonAncestorOfABinaryTree { // LC 236
    /**
     * Runtime: 5 ms, faster than 100.00% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
     * Memory Usage: 33 MB, less than 99.70% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) { // beats 59.36%
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else {
            return (left == null) ? right : left;
        }
    }

    // Follow up 1: LCA of K nodes
    private TreeNode lowestCommonAncestorK(TreeNode root, List<TreeNode> nodes) {
        if (root == null) {
            return root;
        }
        for (TreeNode n : nodes) {
            if (root == n) {
                return root;
            }
        }
        TreeNode left = lowestCommonAncestorK(root.left, nodes);
        TreeNode right = lowestCommonAncestorK(root.right, nodes);
        if (left != null && right != null) {
            return root;
        } else {
            return (left == null) ? right : left;
        }
    }

    // Follow up 2: Targets are not guaranteed in the tree

    /**
     *
     * @param root
     * @param counter
     * @param p
     * @param q
     * @return
     *
     * main function content
     *
     * int[] counter = {0};
     * TreeNode res = helper(root, counter, p, q);
     * return counter[0] == 2 ? res : null;
     *
     */
    private TreeNode lowestCommonAncestor(TreeNode root, int[] counter, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, counter, p, q);
        TreeNode right = lowestCommonAncestor(root.right, counter, p, q);
        if (root == p || root == q) {
            counter[0]++;
            return root;
        }
        if (left != null && right != null) {
            return root;
        } else {
            return (left == null) ? right : left;
        }
    }

    // Follow up 3: parent pointer in TreeNode
    private TreeNodeP lowestCommonAncestor(TreeNodeP p, TreeNodeP q) {
        if (p == null || q == null) {
            return null;
        }

        int pHeight = getHeight(p);
        int qHeight = getHeight(q);

        if (pHeight > qHeight) {
            return moveUp(p, q, pHeight - qHeight);
        } else {
            return moveUp(q, p, qHeight - pHeight);
        }
    }

    private int getHeight(TreeNodeP node) {
        int count = 0;

        while (node != null) {
            count++;
            node = node.parent;
        }

        return count;
    }

    private TreeNodeP moveUp(TreeNodeP deep, TreeNodeP shallow, int diff) {
        while (diff > 0) {
            deep = deep.parent;
            diff--;
        }

        while (deep != shallow) {
            deep = deep.parent;
            shallow = shallow.parent;
        }

        return deep;
    }

    class TreeNodeP {
        int val;
        TreeNodeP left;
        TreeNodeP right;
        TreeNodeP parent;

        TreeNodeP(int x) { val = x; left = null; right = null; parent = null; }
    }
}
