package io.educative.www.Coding.P08_TreeDFS;

class P08_01_BinaryTreePathSum {
    /**
     * Time complexity
     * The time complexity of the above algorithm is O(N), where ‘N’ is the total number of nodes in the tree.
     * This is due to the fact that we traverse each node once.
     *
     * Space complexity
     * The space complexity of the above algorithm will be O(N) in the worst case. This space will be used to store the
     * recursion stack. The worst case will happen when the given tree is a linked list (i.e., every node has only one child).
     *
     * @param root
     * @param sum
     * @return
     */
    public static boolean hasPath(TreeNode root, int sum) {
        if (root == null)
            return false;

        // if the current node is a leaf and its value is equal to the sum, we've found a path
        if (root.val == sum && root.left == null && root.right == null)
            return true;

        // recursively call to traverse the left and right sub-tree
        // return true if any of the two recursive call return true
        return hasPath(root.left, sum - root.val) || hasPath(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + P08_01_BinaryTreePathSum.hasPath(root, 23));
        System.out.println("Tree has path: " + P08_01_BinaryTreePathSum.hasPath(root, 16));
    }
}
