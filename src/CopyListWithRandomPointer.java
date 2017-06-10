/**
 * Created by liyao on 6/10/17.
 */

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
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

    public RandomListNode copyRandomList(RandomListNode head) { // hashmap with one pass
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

    public RandomListNode copyRandomListV0(RandomListNode head) { // hashmap with two passes
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

    // {-1,1,#,#}

    // beats 25.53%
}

class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
}
