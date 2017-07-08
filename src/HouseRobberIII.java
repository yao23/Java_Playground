/**
 * Created by liyao on 7/6/17.
 */
import java.util.Map;

public class HouseRobberIII {
    public int rob(TreeNode root) {
        int[] result = robV2(root);
        return Math.max(result[0], result[1]);
    }

    public int[] robV2(TreeNode root) {
        if (root == null) {
            return new int[2];
        } else {
            int[] left = robV2(root.left);
            int[] right = robV2(root.right);
            int[] result = new int[2];
            result[0] = (Math.max(left[0], left[1]) + Math.max(right[0], right[1])); // root is not robbed
            result[1] = (root.val + left[0] + right[0]); // root is robbed
            return result;
        }
    }

    public int robV0(TreeNode root) {
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

    private int robV1(TreeNode root, Map<TreeNode, Integer> preRes) { // memorized search
        if (root == null) {
            return 0;
        } else {
            if (preRes.containsKey(root)) {
                return preRes.get(root);
            } else {
                int val = root.val;
                if (root.left != null) {
                    val += (robV1(root.left.left, preRes) + robV1(root.left.right, preRes));
                }
                if (root.right != null) {
                    val += (robV1(root.right.left, preRes) + robV1(root.right.right, preRes));
                }
                int childrenLevel = (robV1(root.left, preRes) + robV1(root.right, preRes));
                val = Math.max(val, childrenLevel);
                preRes.put(root, val);
                return val;
            }
        }
    }

    // [] => 0
    // [1] => 1
    // [3,2,3,null,3,null,1] => 7 (3 + 3 + 1)
    // [3,4,5,1,3,null,1] => 9 (4 + 5)
}
