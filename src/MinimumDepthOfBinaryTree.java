/**
 * Created by liyao on 7/12/17.
 */
public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            if (root.left == null && root.right == null) {
                return 1;
            } else if (root.left != null && root.right != null) {
                return 1 + Math.min(minDepth(root.left), minDepth(root.right));
            } else {
                return 1 + ((root.left != null) ? minDepth(root.left) : minDepth(root.right)); // test case 2
            }
        }
    }

    // [] => 0
    // [1,2] => 2
    // [1,3,4,null,null,5,6] => 2

    // beats 16.35%
}
