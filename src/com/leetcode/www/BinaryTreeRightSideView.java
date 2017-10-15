package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeRightSideView { // LC 199
    /**
     * 1. Each depth of the tree only select one node.
     * 2. View depth is current size of result list.
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) { // beats 22.08%
        List<Integer> result = new ArrayList<>();
        rightView(root, result, 0);
        return result;
    }

    private void rightView(TreeNode curr, List<Integer> result, int currDepth) {
        if (curr == null) {
            return;
        }
        if (currDepth == result.size()) {
            result.add(curr.val);
        }

        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);

    }
}
