/**
 * Created by liyao on 6/18/17.
 */

import java.util.ArrayList;
import java.util.List;

public class PathSumII {
    private void helper(TreeNode root, int sum, ArrayList<Integer> tmpResult, List<List<Integer>> result) {
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                ArrayList<Integer> tmpRes = new ArrayList<>(tmpResult);
                tmpRes.add(root.val);

                System.out.println();
                for (Integer n : tmpResult) {
                    System.out.println(n + " ");
                }

                result.add(tmpRes);
                return;
            } else {
                return;
            }
        } else {
            tmpResult.add(root.val);
            if (root.left != null) {
                helper(root.left, sum - root.val, tmpResult, result);
            }
            if (root.right != null) {
                helper(root.right, sum - root.val, tmpResult, result);
            }
            tmpResult.remove(tmpResult.size() - 1);
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        } else {
            helper(root, sum, new ArrayList<Integer>(), result);
            return result;
        }
    }

    // [],22 => []
    // [1],22 => []
    // [22],22 => [[22]]
    // [10,12],22 => [[10,12]]
    // [11,12],22 => []
    // [5,4,8,11,null,13,4,7,2,null,null,5,1],22 => [[5,4,11,2],[5,8,4,5]]

    // beats 48.42%
}
