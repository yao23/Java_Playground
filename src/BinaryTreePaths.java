/**
 * Created by liyao on 6/18/17.
 */

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
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
}
