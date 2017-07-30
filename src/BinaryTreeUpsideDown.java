public class BinaryTreeUpsideDown {
    public TreeNode upsideDownBinaryTree(TreeNode root) { // beats 48.04%
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
}

// [] => []
// [1,2,3,4,5] => [4,5,2,#,#,3,1]