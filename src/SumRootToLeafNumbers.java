/**
 * Created by liyao on 6/17/17.
 */

import java.util.ArrayList;
import java.util.List;

public class SumRootToLeafNumbers { // LC 129
    private void helperV1(TreeNode root, StringBuilder tmpResult, List<Integer> result) { // beats 9.66%
        tmpResult.append(root.val);
        if (root.left == null && root.right == null) {
            result.add(Integer.valueOf(tmpResult.toString()));
        } else {
            if (root.left != null) {
                helperV1(root.left, tmpResult, result);
                tmpResult.deleteCharAt(tmpResult.length() - 1);
            }
            if (root.right != null) {
                helperV1(root.right, tmpResult, result);
                tmpResult.deleteCharAt(tmpResult.length() - 1);
            }
        }
    }

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

    // beats 4.79%
}