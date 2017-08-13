package com.leetcode.www;

public class LowestCommonAncestorOfABinarySearchTree { // LC 235
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        } else if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        } else{
            return root;
        }
    }
}

// beats 53.58%

// similar problem: 236. Lowest Common Ancestor of a Binary Tree
