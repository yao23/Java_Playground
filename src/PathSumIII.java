/**
 * Created by liyao on 6/19/17.
 */

import java.util.ArrayList;

public class PathSumIII {
    private void helper(TreeNode root, int targetSum, ArrayList<Integer> tmpRes, int[] result) {
        for (int i = tmpRes.size() - 1; i >= 0; i--) {
            System.out.print(tmpRes.get(i) + " ");
        }
        System.out.println("cur: " + root.val);

        int curSum = 0;
        tmpRes.add(root.val); // assume upper levels are done
        for (int i = tmpRes.size() - 1; i >= 0; i--) {
            curSum += tmpRes.get(i);
            if (curSum == targetSum) { // find a path
                result[0] += 1;
            }
        }

        if (root.left != null) {
            helper(root.left, targetSum, tmpRes, result);
        }
        if (root.right != null) {
            helper(root.right, targetSum, tmpRes, result);
        }
        tmpRes.remove(tmpRes.size() - 1);
    }

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        } else {
            int[] result = {0};
            helper(root, sum, new ArrayList<Integer>(), result);
            return result[0];
        }
    }
}
