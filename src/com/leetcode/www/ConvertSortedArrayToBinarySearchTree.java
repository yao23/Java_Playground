package com.leetcode.www;

public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] num) {
        return generate_BST(num, 0, num.length-1);
    }
    public TreeNode generate_BST(int[] num, int start, int end) {
        if( end < start )
            return null;
        int mid = (start + end)/2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = generate_BST(num, start, mid-1);
        root.right = generate_BST(num, mid+1, end);
        return root;
    }
}
