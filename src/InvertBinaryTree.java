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

            invertHelper(left.left, right.right);
            invertHelper(left.right, right.left);

            if (left != null) {
                if (left.left != null) {
                    System.out.println("left.left: " + left.left.val);
                }
                if (left.right != null) {
                    System.out.println("left.right: " + left.right.val);
                }
            }

            if (right != null) {
                if (right.left != null) {
                    System.out.println("right.left: " + right.left.val);
                }
                if (right.right != null) {
                    System.out.println("right.right: " + right.right.val);
                }
            }
        } else { // left or right is not null
            if (left != null) { // left is not null
                System.out.println("left: " + left.val);
                right = new TreeNode(left.val);
                right.left = left.right;
                right.right = left.left;
                left = null;

                invertHelper(null, right.right);
                invertHelper(null, right.left);
            } else { // right is not null
                System.out.println("right: " + right.val);
                left = new TreeNode(right.val);
                left.left = right.right;
                left.right = right.left;
                right = null;

                invertHelper(left.left, null);
                invertHelper(left.right, null);
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

        invertHelper(root.left, root.right);

        return root;
    }

    // [4,2,7,1,3,6,9]
    // [1,2,3,4,null,null,5]
    // [1,2,3,null,4,null,5]

}
