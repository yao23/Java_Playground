package com.leetcode.www;

import java.util.Deque;
import java.util.LinkedList;

public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] num) { // beats 11.10% (recursive)
        return generateBST(num, 0, num.length-1);
    }

    /**
     *   LC 108
     * @param num
     * @param start
     * @param end
     * @return
     */
    private TreeNode generateBST(int[] num, int start, int end) {
        if (end < start) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = generateBST(num, start, mid - 1);
        root.right = generateBST(num, mid + 1, end);
        return root;
    }

    public TreeNode sortedArrayToBSTV0(int[] nums) { // beats 1.40% (iterative)
        int len = nums.length;
        if (len == 0) {
            return null;
        }

        // 0 as a placeholder
        TreeNode head = new TreeNode(0);

        Deque<TreeNode> nodeStack       = new LinkedList<TreeNode>() {{ push(head);  }};
        Deque<Integer>  leftIndexStack  = new LinkedList<Integer>()  {{ push(0);     }};
        Deque<Integer>  rightIndexStack = new LinkedList<Integer>()  {{ push(len-1); }};

        while (!nodeStack.isEmpty()) {
            TreeNode currNode = nodeStack.pop();
            int left  = leftIndexStack.pop();
            int right = rightIndexStack.pop();
            int mid   = left + (right - left)/2; // avoid overflow
            currNode.val = nums[mid];
            if (left <= mid - 1) {
                currNode.left = new TreeNode(0);
                nodeStack.push(currNode.left);
                leftIndexStack.push(left);
                rightIndexStack.push(mid - 1);
            }
            if (mid + 1 <= right) {
                currNode.right = new TreeNode(0);
                nodeStack.push(currNode.right);
                leftIndexStack.push(mid + 1);
                rightIndexStack.push(right);
            }
        }
        return head;
    }
}

// [] => null