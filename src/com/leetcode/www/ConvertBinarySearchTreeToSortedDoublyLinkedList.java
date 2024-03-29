package com.leetcode.www;

import java.util.Stack;
import com.leetcode.www.TreeNode;

public class ConvertBinarySearchTreeToSortedDoublyLinkedList { // LC 426 (Facebook)
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Binary Search Tree to Sorted Doubly Linked List.
     * Memory Usage: 34 MB, less than 89.31% of Java online submissions for Convert Binary Search Tree to Sorted Doubly Linked List.
     *
     * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/discuss/149151/Concise-Java-solution-Beats-100
     *
     * @param root
     * @return
     */
    TreeNode prev = null;
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode dummy = new TreeNode(0);
        prev = dummy;
        helper(root);
        //connect head and tail
        prev.right = dummy.right;
        dummy.right.left = prev;
        return dummy.right;
    }

    private void helper (TreeNode cur) {
        if (cur == null) {
            return;
        }
        helper(cur.left);
        prev.right = cur;
        cur.left = prev;
        prev = cur;
        helper(cur.right);
    }

    private TreeNode head = null, tail = null;

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Binary Search Tree to Sorted Doubly Linked List.
     * Memory Usage: 35.2 MB, less than 57.47% of Java online submissions for Convert Binary Search Tree to Sorted Doubly Linked List.
     *
     * Inorder traverse recursion
     *
     * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/discuss/174111/inorder-vs-divide-and-conquer
     *
     * @param root
     * @return
     */
    public TreeNode treeToDoublyListV4(TreeNode root) {
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
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Binary Search Tree to Sorted Doubly Linked List.
     * Memory Usage: 33.3 MB, less than 98.00% of Java online submissions for Convert Binary Search Tree to Sorted Doubly Linked List.
     *
     * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/discuss/287149/Recursive-single-method-O(1)-space-Beats-100
     *
     * @param root
     * @return
     */
    public TreeNode treeToDoublyListV3(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = treeToDoublyList(root.left);
        TreeNode right = treeToDoublyList(root.right);

        TreeNode newLeft = left == null ? root : left;
        TreeNode newRight = right == null ? root : right.left;

        TreeNode midLeft = left == null ? null : left.left;
        TreeNode midRight = right == null ? null : right;

        // Connect the middle.
        if (midLeft != null) {
            root.left = midLeft;
            midLeft.right = root;
        }
        if (midRight != null) {
            root.right = midRight;
            midRight.left = root;
        }

        // Connect two sides;
        newLeft.left = newRight;
        newRight.right = newLeft;

        return newLeft;
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Binary Search Tree to Sorted Doubly Linked List.
     * Memory Usage: 34.1 MB, less than 86.76% of Java online submissions for Convert Binary Search Tree to Sorted Doubly Linked List.
     *
     * DFS
     *
     * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/discuss/286345/Java-DFS-0-ms-no-global-variables-no-extra-functions
     *
     * @param root
     * @return
     */
    public TreeNode treeToDoublyListV2(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode min = root, max = root;

        if (root.left != null) {
            TreeNode leftMinMax = treeToDoublyList(root.left);
            min = leftMinMax;
            root.left = leftMinMax.left;
            leftMinMax.left.right = root;
        }

        if (root.right != null) {
            TreeNode rightMinMax = treeToDoublyList(root.right);
            max = rightMinMax.left;
            root.right = rightMinMax;
            rightMinMax.left = root;
        }

        min.left = max;
        max.right = min;

        return min;
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Binary Search Tree to Sorted Doubly Linked List.
     * Memory Usage: 35.8 MB, less than 53.64% of Java online submissions for Convert Binary Search Tree to Sorted Doubly Linked List.
     *
     * Divide and Conquer
     * @param root
     * @return
     */
    public TreeNode treeToDoublyListV1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftHead = treeToDoublyListV1(root.left);
        TreeNode rightHead = treeToDoublyListV1(root.right);

        root.left = root;
        root.right = root;

        return connect(connect(leftHead, root), rightHead);
    }

    /**
     * list1 and list2 must be circular doubly linked list
     *
     * @param list1
     * @param list2
     * @return
     */
    private TreeNode connect(TreeNode list1, TreeNode list2) {
        if (list2 == null) {
            return list1;
        }
        if (list1 == null) {
            return list2;
        }

        TreeNode tail1 = list1.left;
        TreeNode tail2 = list2.left;
        list1.left = tail2;
        tail2.right = list1;
        tail1.right = list2;
        list2.left = tail1;

        return list1;
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
