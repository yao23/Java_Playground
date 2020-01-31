package io.educative.www.Coding.P08_TreeDFS;

class P08_02_AllPathsForASum {
    /**
     * Time complexity
     * The time complexity of the above algorithm is O(N^2), where ‘N’ is the total number of nodes in the tree. This is
     * due to the fact that we traverse each node once (which will take O(N)), and for every leaf node we might have to
     * store its path which will take O(N).
     *
     * We can calculate a tighter time complexity of O(NlogN) from the space complexity discussion below.
     *
     * Space complexity
     * If we ignore the space required for the allPaths list, the space complexity of the above algorithm will be O(N)
     * in the worst case. This space will be used to store the recursion stack. The worst case will happen when the
     * given tree is a linked list (i.e., every node has only one child).
     *
     * @param root
     * @param sum
     * @return
     */
    public static List<List<Integer>> findPaths(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<Integer>();
        findPathsRecursive(root, sum, currentPath, allPaths);
        return allPaths;
    }

    private static void findPathsRecursive(TreeNode currentNode, int sum, List<Integer> currentPath,
                                           List<List<Integer>> allPaths) {
        if (currentNode == null)
            return;

        // add the current node to the path
        currentPath.add(currentNode.val);

        // if the current node is a leaf and its value is equal to sum, save the current path
        if (currentNode.val == sum && currentNode.left == null && currentNode.right == null) {
            allPaths.add(new ArrayList<Integer>(currentPath));
        } else {
            // traverse the left sub-tree
            findPathsRecursive(currentNode.left, sum - currentNode.val, currentPath, allPaths);
            // traverse the right sub-tree
            findPathsRecursive(currentNode.right, sum - currentNode.val, currentPath, allPaths);
        }

        // remove the current node from the path to backtrack, 
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 23;
        List<List<Integer>> result = P08_02_AllPathsForASum.findPaths(root, sum);
        System.out.println("Tree paths with sum " + sum + ": " + result);
    }
}
