package com.leetcode.www;

public class BinaryTreeUpsideDown { // LC 156
    /**
     * recursively to process left subtree
     *
     * beats 48.04%
     *
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) {
            return root;
        } else {
            TreeNode parent = root, left = root.left, right = root.right;
            if (left != null) {
                TreeNode res = upsideDownBinaryTree(left);
                left.left = right;
                left.right = parent;

                // update to null to avoid infinite loop
                root.left = null;
                root.right = null;

                return res;
            } else {
                return root;
            }
        }
    }

    // iterative solution, easier to understand
    public TreeNode upsideDownBinaryTreeV1(TreeNode root) { // beats 48.04%
        if (root == null) {
            return root;
        } else {
            TreeNode node = root, parent = null, right = null;

            while (node != null) {
                TreeNode left = node.left;
                node.left = right;
                right = node.right;
                node.right = parent;
                parent = node;
                node = left;
            }

            return parent;
        }
    }

    // post order to level order traversal
    private TreeNode out = null;
    public TreeNode UpsideDownBinaryTreeV2(TreeNode root) { // not working, failed at test case 3
        TreeNode dummy = new TreeNode(0);
        dummy.left = new TreeNode(0);
        out = dummy;

        postorder(root);
        return dummy.right;
    }

    private void postorder(TreeNode root) {
        if (root == null)
            return;

        postorder(root.left);
        postorder(root.right);

        if (out.left == null) {
            out.left = root;
            out.left.left = null;
            out.left.right = null;
        } else if (out.right == null) {
            out.right = root;
            out.right.left = null;
            out.right.right = null;
        }

        if (out.right != null) {
            out = out.right;
        }
    }
}

// [] => []
// [1,2,3,4,5] => [4,5,2,#,#,3,1]
// [1,2] => [2,null,1]