/**
 * Created by liyao on 7/6/17.
 */
public class HouseRobberIII {
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int val = root.val; // rob root level
            if (root.left != null) {
                val += (rob(root.left.left) + rob(root.left.right));
            }
            if (root.right != null) {
                val += (rob(root.right.left) + rob(root.right.right));
            }

            int childrenLevel = rob(root.left) + rob(root.right); // not rob root level

            return Math.max(val, childrenLevel);
        }
    }
}
