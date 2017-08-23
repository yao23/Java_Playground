package com.leetcode.www; /**
 * Created by liyao on 6/18/17.
 */

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths { // LC 257
    private void helper(TreeNode root, StringBuilder sb, List<String> result) {
        if (root.left == null && root.right == null) {
            System.out.println("sb: " + sb);
            String str = sb.append(root.val).toString();
            System.out.println("str: " + str);
            result.add(str);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            sb.append(root.val + "->");
            if (root.left != null) {
                helper(root.left, sb, result);
            }
            if (root.right != null) {
                helper(root.right, sb, result);
            }
            sb.delete(sb.length() - 3, sb.length());
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        } else {
            helper(root, new StringBuilder(), result);
            return result;
        }
    }

    // [] => []
    // [1] => ["1"]
    // [1,2] => ["1->2"]
    // [1,2,3,null,5] => ["1->2->5","1->3"]
    // [1,2,3,4,5] => ["1->2->4","1->2->5","1->3"]
    // [37,-34,-48,null,-100,-100,48,null,null,null,null,-54,null,-71,-22,null,null,null,8] => ["37->-34->-100","37->-48->-100","37->-48->48->-54->-71","37->-48->48->-54->-22->8"]

    // beats 88.74%
}
