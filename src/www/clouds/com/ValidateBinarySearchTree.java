package www.clouds.com;

import java.util.concurrent.*;

public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        return isValidBST(executorService, root);
    }

    public boolean isValidBST(ExecutorService executorService, TreeNode root) {
        if (root == null) {
            return true;
        }

        IsValidBST isValidBSTCallable = new IsValidBST(executorService, root, Integer.MIN_VALUE, Integer.MAX_VALUE);

        try {
            return isValidBSTCallable.isValidTree();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; left = null; right = null; }
}

class IsValidBST implements Callable<Boolean> {
    TreeNode root;
    long minVal;
    long maxVal;
    ExecutorService executorService;

    public IsValidBST(ExecutorService executorService, TreeNode root, long minVal, long maxVal) {
        this.executorService = executorService;
        this.root = root;
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    @Override
    public Boolean call() throws ExecutionException, InterruptedException {
        return isValidTree();
    }

    public Boolean isValidTree() throws ExecutionException, InterruptedException {
        if (root == null) {
            return true;
        }
        if (root.val >= maxVal || root.val <= minVal) {
            return false;
        }
        Future<Boolean> left = executorService.submit(new IsValidBST(executorService, root.left, minVal, root.val));
        Future<Boolean> right = executorService.submit(new IsValidBST(executorService, root.right, root.val, maxVal));

        return left.get() && right.get();
    }
}