package com.leetcode.www;

import java.util.Stack;

public class ConvertBinarySearchTreeToSortedDoublyLinkedList { // LC 426
    private TreeNode head = null, tail = null;

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Binary Search Tree to Sorted Doubly Linked List.
     * Memory Usage: 35.2 MB, less than 57.47% of Java online submissions for Convert Binary Search Tree to Sorted Doubly Linked List.
     *
     * @param root
     * @return
     */
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return root;
        }

        inorder(root);
        head.left = tail;
        tail.right = head;
        return head;
    }
    private void inorder(TreeNode root) {
        if (root == null)
            return;

        inorder(root.left);
        if (head == null) {
            head = root;
            tail = head;
        } else {
            tail.right = root;
            root.left = tail;
            tail = root;
        }
        inorder(root.right);
    }

    /**
     * Runtime: 2 ms, faster than 49.57% of Java online submissions for Convert Binary Search Tree to Sorted Doubly Linked List.
     * Memory Usage: 37.4 MB, less than 22.18% of Java online submissions for Convert Binary Search Tree to Sorted Doubly Linked List.
     *
     * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/discuss/297335/Java-solution-using-stack
     *
     * @param root
     * @return
     */
    public TreeNode treeToDoublyListV0(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> st = new Stack<>();
        while (root != null) {
            st.push(root);
            root = root.left;
        }
        TreeNode head = st.peek(); // The leftmost node is the new head
        TreeNode cur = null;
        while (!st.empty()) {
            TreeNode top = st.peek();
            cur = st.pop();
            if (cur.right != null) {
                cur = cur.right;
                while (cur != null) {
                    st.push(cur);
                    cur = cur.left;
                }
            }
            if (!st.empty()) {
                top.right = st.peek();
                st.peek().left = top;
            }
        }
        cur.right = head;
        head.left = cur;
        return head;
    }
}
