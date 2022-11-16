package com.leetcode.www;

public class DeleteNodeInALinkedList { // LC 237
    /**
     *  // beats 3.19%
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
