package io.bittiger.www;

import java.util.*;

public class BoundaryTraversalOfBinaryTree {
    public List<Integer> boundaryOfBinaryTree(TreeNode   root)   {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return   res;
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        List<Integer> leaves = new ArrayList<>();
        helper(root, left, right);
        leaves(root.left, leaves);
        leaves(root.right, leaves);
        Set<Integer> set   =   new HashSet<>();
        for (int i = 0; i < left.size(); i++) {
            res.add(left.get(i));
            set.add(left.get(i));
        }
        for (int i = 0; i < leaves.size(); i++) {
            if (set.contains(leaves.get(i))) {
                continue;
            }
            res.add(leaves.get(i));
            set.add(leaves.get(i));
        }
        for (int i = right.size() - 1; i >= 0; i--) {
            if (set.contains(right.get(i))) {
                continue;
            }
            res.add(right.get(i));
            set.add(right.get(i));
        }
        return res;
    }

    private void leaves(TreeNode root, List<Integer> nodes) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            nodes.add(root.val);
            return;
        }
        leaves(root.left, nodes);
        leaves(root.right, nodes);
    }

    private void helper(TreeNode root, List<Integer> left, List<Integer> right) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.addLast(root);

        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = que.removeFirst();
                if (i == 0) {
                    left.add(cur.val);
                }
                if (i == size - 1) {
                    right.add(cur.val);
                }
                if (cur.left != null) {
                    que.addLast(cur.left);
                }
                if (cur.right != null) {
                    que.addLast(cur.right);
                }
            }
        }
    }
}
