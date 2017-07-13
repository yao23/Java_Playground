/**
 * Created by liyao on 7/12/17.
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }

    // [] => 0
    // [1,2,3,null,null,5,6] => 3

    // beats 15.97%
}
