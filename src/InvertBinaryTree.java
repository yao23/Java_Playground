/**
 * Created by liyao on 6/5/17.
 */
public class InvertBinaryTree {
    private void swap(TreeNode left, TreeNode right, TreeNode root) {
        TreeNode tmp = right;
        root.right = root.left;
        root.left = tmp;
    }

    private void invertHelper(TreeNode left, TreeNode right, TreeNode root) {
        if (left == null && right == null) { // left and right are null
            return;
        } else if (left != null && right != null) { // left and right are not null
            invertHelper(left.left, left.right, left);
            invertHelper(right.left, right.right, right);

            swap(left, right, root);
        } else { // left or right is not null
            if (left != null) { // left is not null
                invertHelper(left.left, left.right, left);

                swap(left, right, root);
            } else { // right is not null
                invertHelper(right.left, right.right, right);

                swap(left, right, root);
            }
        }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        if (root.left == null && root.right == null) {
            return root;
        }

        invertHelper(root.left, root.right, root);

        return root;
    }

    // []
    // [4,2,7,1,3,6,9]
    // [1,2,3,4,null,null,5]
    // [1,2,3,null,4,null,5]
    // [1,2,3,null,4,null,5,6,null,null,8]

}
