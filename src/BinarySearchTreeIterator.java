/**
 * Created by liyao on 6/5/17.
 */
public class BinarySearchTreeIterator {
    public BinarySearchTreeIterator(TreeNode root) {

    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return true;
    }

    /** @return the next smallest number */
    public int next() {
        return 0;
    }

    // [4,2,6,1,3,5,7], what's order? 1,2,3,4,5,6,7?
}


/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
