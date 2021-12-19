package com.leetcode.www; /**
 * Created by liyao on 6/10/17.
 */

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer { // LC 138
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Copy List with Random Pointer.
     * Memory Usage: 33 MB, less than 98.92% of Java online submissions for Copy List with Random Pointer.
     *
     * @param head
     */
    private void addNewNodes(RandomListNode head) { // add new nodes after original ones
        RandomListNode cur = head;

        while (cur != null) {
            RandomListNode newNode = new RandomListNode(cur.label);
            newNode.next = cur.next;
            cur.next = newNode;

            cur = newNode.next;
        }
    }

    private void addRandomPointers(RandomListNode head) { // add random pointers for new nodes
        RandomListNode cur = head;

        while (cur != null) {
            RandomListNode newNode = cur.next;
            if (cur.random == null) {
                newNode.random = null;
            } else {
                newNode.random = cur.random.next;
            }

            cur = newNode.next;
        }
    }

    private RandomListNode splitNewNodes(RandomListNode head) { // 1-1'-2-2'-3-3' => 1-2-3,1'-2'-3'
        RandomListNode cur = head;
        RandomListNode newHead = head.next;

        while (cur != null) {
            RandomListNode newNode = cur.next;
            cur.next = newNode.next; // 1-2-3
            if (newNode.next == null) {
                newNode.next = null;
            } else {
                newNode.next = newNode.next.next; // 1'-2'-3'
            }

            cur = cur.next;
        }

        return newHead;
    }

    private RandomListNode processSingleNode(RandomListNode head) {
        if (head.next == null) {
            RandomListNode newHead = new RandomListNode(head.label);
            if (head.random == null) {
                newHead.random = null;
            } else {
                newHead.random = newHead;
            }
            return newHead;
        } else {
            return null;
        }
    }

    /**
     * without hashmap, beats 72.33%
     *
     * @param head
     * @return
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        RandomListNode tmpNode = processSingleNode(head);
        if (tmpNode != null) {
            return tmpNode;
        }

        addNewNodes(head);

        addRandomPointers(head);

        return splitNewNodes(head);
    }

    private RandomListNode getNode(RandomListNode node, Map<RandomListNode,RandomListNode> hashMap) {
        RandomListNode newNode = hashMap.get(node);
        if (newNode == null) { // if not created in hashamp before
            if (node != null) {
                newNode = new RandomListNode(node.label);
            }
            hashMap.put(node,newNode); // construct old-new node map
        }
        return newNode;
    }

    public RandomListNode copyRandomListV1(RandomListNode head) { // hashmap with one pass, beats 25.53%
        if (head == null) {
            return null;
        }

        RandomListNode tmpNode = processSingleNode(head);
        if (tmpNode != null) {
            return tmpNode;
        }

        Map<RandomListNode,RandomListNode> hashMap = new HashMap<>();
        RandomListNode cur = head;
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode newCur = dummy;

        while (cur != null) { // build new list
            RandomListNode tmpNewCur = getNode(cur, hashMap);
            tmpNewCur.next = getNode(cur.next, hashMap);
            tmpNewCur.random = getNode(cur.random, hashMap);

            newCur.next = tmpNewCur;
            newCur = newCur.next;

            cur = cur.next;
        }

        return dummy.next;
    }

    public RandomListNode copyRandomListV0(RandomListNode head) { // hashmap with two passes, beats 25.53%
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            RandomListNode newHead = new RandomListNode(head.label);
            if (head.random == null) {
                newHead.random = null;
            } else {
                newHead.random = newHead;
            }
            return newHead;
        }

        Map<RandomListNode,RandomListNode> hashMap = new HashMap<>();

        RandomListNode cur = head;

        while (cur != null) { // construct old-new node map
            RandomListNode newNode = new RandomListNode(cur.label);
            hashMap.put(cur,newNode);

            cur = cur.next;
        }

        cur = head;
        RandomListNode dummy = new RandomListNode(0);
        dummy.next = hashMap.get(cur);

        while (cur != null) { // build new list
            RandomListNode newCur = hashMap.get(cur);
            newCur.next = hashMap.get(cur.next);
            newCur.random = hashMap.get(cur.random);

            cur = cur.next;
        }

        return dummy.next;
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Copy List with Random Pointer.
     * Memory Usage: 34.2 MB, less than 95.19% of Java online submissions for Copy List with Random Pointer.
     *
     * https://leetcode.com/problems/copy-list-with-random-pointer/discuss/295385/clean-java-O(n)-solution-with-3-times-loop
     *
     * {-1,1,#,#}
     *
     * / beats 25.53%
     *
     * @param head
     * @return
     */
    public RandomListNode copyRandomListV2(RandomListNode head) {
        if (head == null) {
            return null;
        }

        // 1. duplicate each node, interleave old and new nodes like 1-1'-2-2'-3-3'
        RandomListNode node = head;
        while (node != null) {
            RandomListNode newNode = new RandomListNode(node.label);
            newNode.next = node.next;
            node.next = newNode;
            node = newNode.next;
        }

        // 2. set random pointer
        node = head;
        while (node != null){
            node.next.random = node.random == null ? null : node.random.next;
            node = node.next.next;
        }

        // 3. build new list, 1-1'-2-2'-3-3' => 1-2-3,1'-2'-3'
        RandomListNode newHead = head.next;
        node = newHead;
        while (head != null) {
            head.next = head.next.next;
            head = head.next;
            node.next = head == null ? null : head.next;
            node = node.next;
        }
        return newHead;
    }
}

class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
}
