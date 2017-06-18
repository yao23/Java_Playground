/**
 * Created by liyao on 6/14/17.
 */
public class BinaryTreeMaximumPathSum {
    private int helper(TreeNode root, int curSum, int[] result) {
        if (root == null) {
            return 0;
        } else {
            int left = helper(root.left, curSum + root.val, result);
            int right = helper(root.right, curSum + root.val, result);
            int single = Math.max(root.val, root.val + Math.max(left,right));
            int max = Math.max(single, root.val + left + right);
            if (max > result[0]) {
                result[0] = max;
            }
            return single;
        }
    }

    public int maxPathSum(TreeNode root) {
        int[] result = new int[]{0};
        helper(root, 0, result);
        return result[0];
    }
    // [] => -2147483648
    // [-3] => -3
    // [1,2,3] => 6
    // [5,3,4,1,2,6,7] => 21, [2,3,5,4,7], root 5 use both left and right single path
    // [5,-3,-4,1,2,6,7] => 9, [6,-4,7], root 5 don't use left or right single path, arch in root 6 as max
}
