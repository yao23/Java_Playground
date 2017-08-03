public class LargestBSTSubtree { // LC 333
    public int largestBSTSubtree(TreeNode root) { // beats 24.48%
        if (root == null) {
            return 0;
        }
        ResNode resNode = helper(root);
        return resNode.count;
    }

    private ResNode helper(TreeNode root) {
        if (root == null) {
            return new ResNode();
        }
        ResNode left = helper(root.left);
        ResNode right = helper(root.right);
        if (left.max < root.val && root.val < right.min) {
            if (left.node == root.left && right.node == root.right) {
                ResNode resNode = new ResNode();
                resNode.min = Math.min(left.min, root.val);
                resNode.max = Math.max(right.max, root.val);
                resNode.count = left.count + 1 + right.count;
                resNode.node = root;
                return resNode;
            } else {
                return (left.count < right.count) ? right : left;
            }
        } else {
            return (left.count < right.count) ? right : left;
        }
    }

    class ResNode {
        int min;
        int max;
        int count;
        TreeNode node;
        public ResNode() {
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            count = 0;
            node = null;
        }
    }
}

// [10,5,15,1,8,null,7] => 3 ([5,1,8])