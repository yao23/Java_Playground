/**
 * Created by liyao on 6/5/17.
 */
public class InvertBinaryTree {
    private void invertHelper(TreeNode left, TreeNode right) {
        if (left == null && right == null) { // left and right are null
            return;
        } else if (left != null && right != null) { // left and right are not null
            int tmp = left.val;
            left.val = right.val;
            right.val = tmp;
        } else { // left or right is not null
            if (left != null) { // left is not null
                right = new TreeNode(left.val);
                left = null;
            } else { // right is not null
                left = new TreeNode(right.val);
                right = null;
            }
        }

        invertHelper(left.left, right.right);
        invertHelper(left.right, right.left);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        if (root.left == null && root.right == null) {
            return root;
        }

        invertHelper(root.left, root.right);

        return root;
    }

    // [4,2,7,1,3,6,9]
    // [1,2,3,4,null,null,5]
    // [1,2,3,null,4,null,5]

}
