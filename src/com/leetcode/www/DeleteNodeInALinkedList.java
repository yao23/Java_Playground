package com.leetcode.www;

public class DeleteNodeInALinkedList { // LC 237
    public void deleteNode(ListNode node) { // beats 3.19%
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
