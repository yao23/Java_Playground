package com.leetcode.www;

public class DeleteNodeInABST { // LC 450 (FB)
    /**
     *
     * root = [5,3,6,2,4,null,7], key = 3 => [5,4,6,2,null,null,7] ([5,2,6,null,4,null,7])
     * beats 60.00%
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        } else {
            if (key < root.val) {
                root.left = deleteNode(root.left, key);
            } else if (key > root.val) {
                root.right = deleteNode(root.right, key);
            } else {
                if (root.left == null) {
                    return root.right;
                } else if(root.right == null) {
                    return root.left;
                } else {
                    TreeNode minNode = findMin(root.right);
                    root.val = minNode.val;
                    root.right = deleteNode(root.right, root.val); // delete the found min node in right tree
                }
            }
            return root;
        }
    }

    private TreeNode findMin(TreeNode node){
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
