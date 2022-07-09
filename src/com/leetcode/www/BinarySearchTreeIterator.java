package com.leetcode.www;
/**
 * Created by liyao on 6/5/17.
 */

import java.util.Deque;
import java.util.ArrayDeque;
import com.leetcode.www.TreeNode;

public class BinarySearchTreeIterator { // LC 173 (Facebook)
    private Deque<TreeNode> stack;

    /**
     * Runtime: 15 ms, faster than 98.62% of Java online submissions for Binary Search Tree Iterator.
     * Memory Usage: 44.8 MB, less than 100.00% of Java online submissions for Binary Search Tree Iterator.
     *
     * @param root
     */
    public BinarySearchTreeIterator(TreeNode root) {
        stack = new ArrayDeque<TreeNode>();

        // push left children into stack and most left one is smallest
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode result = stack.pop();

        if (result.right != null) {
            TreeNode tmpNode = result.right;

            while (tmpNode != null) {
                stack.push(tmpNode);
                tmpNode = tmpNode.left;
            }
        }

        return result.val;
    }

    // [] => []
    // [1,null,2] => 1,2
    // [4,2,6,1,3,5,7] => 1,2,3,4,5,6,7
    // [3,2,4,1,null,null,5] => 1,2,3,4,5
    // [3,1,5,null,2,4,null] => 1,2,3,4,5

    // beats 47.43%
}


/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
