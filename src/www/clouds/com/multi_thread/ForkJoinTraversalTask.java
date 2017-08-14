package www.clouds.com.multi_thread;

import java.util.concurrent.RecursiveTask;

public class ForkJoinTraversalTask extends RecursiveTask<Boolean> {
    TreeNode root;
    int height;
    long minVal;
    long maxVal;
    public ForkJoinTraversalTask(TreeNode root, int height, long minVal, long maxVal) {
        this.root = root;
        this.height = height;
        this.minVal = minVal;
        this.maxVal = maxVal;
    }
    public Boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) {
            return true;
        }
        if (root.val >= maxVal || root.val <= minVal) {
            return false;
        }
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
    @Override
    public Boolean compute() {
        if (height <= 3) {
            return isValidBST(root, this.minVal, this.maxVal); // normal recursion
        } else {
            ForkJoinTraversalTask left = new ForkJoinTraversalTask(root.left, height - 1, minVal, root.val);
            ForkJoinTraversalTask right = new ForkJoinTraversalTask(root.right, height - 1, root.val, maxVal);

            left.fork();
            right.fork();

            return left.join() && right.join();
        }
    }
}
