import com.leetcode.www.TreeNode;

public class RangeSumOfBST { // 938
    /**
     * Not work, i.e. [15,9,21,7,13,19,23,5,null,11,null,17], 19, 21
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        } else {
            int cur = root.val;
            if (cur < L) {
                return getLeft(root.right, L, R);
            } else if (cur > R) {
                return getRight(root.left, L, R);
            } else {
                int left = getLeft(root.left, L, R);
                int right = getRight(root.right, L, R);
                return cur + left + right;
            }
        }
    }

    private int getLeft(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        } else {
            if (root.val < L) {
                return getLeft(root.right, L, R);
            } else {
                int left = getLeft(root.left, L, R);
                int right = getLeft(root.right, L, R);
                return root.val + left + right;
            }
        }
    }

    private int getRight(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        } else {
            if (root.val > R) {
                return getRight(root.left, L, R);
            } else {
                int left = getRight(root.left, L, R);
                int right = getRight(root.right, L, R);
                return root.val + left + right;
            }
        }
    }
}
