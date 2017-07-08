/**
 * Created by liyao on 7/6/17.
 */
import java.util.Map;

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
}
