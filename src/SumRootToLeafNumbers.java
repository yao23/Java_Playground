/**
 * Created by liyao on 6/17/17.
 */

import java.util.ArrayList;
import java.util.List;

public class SumRootToLeafNumbers {
    private void helper(TreeNode root, List<TreeNode> tmpResult, List<Integer> result) {
        tmpResult.add(root);
        if (root.left == null && root.right == null) {
            String num = "";
            for (TreeNode n : tmpResult) {
                num += n.val;
            }
            result.add(Integer.valueOf(num));
        } else {
            if (root.left != null) {
                helper(root.left, tmpResult, result);
                tmpResult.remove(tmpResult.size() - 1);
            }
            if (root.right != null) {
                helper(root.right, tmpResult, result);
                tmpResult.remove(tmpResult.size() - 1);
            }
        }
    }

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            List<Integer> result = new ArrayList<>();
            helper(root, new ArrayList<TreeNode>(), result);
            int sum = 0;
            for (Integer num : result) {
                sum += num;
            }
            return sum;
        }
    }

    // [] => 0
    // [1] => 1
    // [1,2] => 12
    // [1,2,3] => 25 (12 + 13)
    // [1,null,2,null,3] => 123
}