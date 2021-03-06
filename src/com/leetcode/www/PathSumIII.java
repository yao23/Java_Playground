package com.leetcode.www; /**
 * Created by liyao on 6/19/17.
 */

import java.util.ArrayList;

public class PathSumIII { // LC 437
    /**
     * Runtime: 11 ms, faster than 53.13% of Java online submissions for Path Sum III.
     * Memory Usage: 40.8 MB, less than 63.45% of Java online submissions for Path Sum III.
     *
     * https://leetcode.com/problems/path-sum-iii/discuss/297635/Simple-efficient-and-easy-to-understand-Recursion-(Java)
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        } else {
            return numPathStartingAtNode(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
        }
    }
    private int numPathStartingAtNode(TreeNode root, int sum){
        if (root == null) {
            return 0;
        } else {
            return (root.val == sum ? 1 : 0) + numPathStartingAtNode(root.left, sum - root.val) +
                    numPathStartingAtNode(root.right, sum - root.val);
        }
    }

    private void helper(TreeNode root, int targetSum, ArrayList<Integer> tmpRes, int[] result) {
        int curSum = 0;
        tmpRes.add(root.val);
        for (int i = tmpRes.size() - 1; i >= 0; i--) { // assume upper levels are done
            curSum += tmpRes.get(i); // add from current to upper levels
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

    /**
     * Runtime: 18 ms, faster than 17.56% of Java online submissions for Path Sum III.
     * Memory Usage: 39.6 MB, less than 88.07% of Java online submissions for Path Sum III.
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSumV0(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        } else {
            int[] result = {0};
            helper(root, sum, new ArrayList<Integer>(), result);
            return result[0];
        }
    }

    // [], 8 => 0
    // [1], 8 => 0
    // [8], 8 => 1
    // [2,6], 8 => 1
    // [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8 => 3 (5 -> 3, 5 -> 2 -> 1, -3 -> 11)

    // beats 10.34%
}
