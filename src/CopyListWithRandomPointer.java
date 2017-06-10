/**
 * Created by liyao on 6/10/17.
 */

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
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

        while (cur != null) { // construct mapping relationship
            RandomListNode newNode = new RandomListNode(cur.label);
            hashMap.put(cur,newNode);
        }

        cur = head;
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode newPre = dummy;

        while (cur != null) {
            RandomListNode newCur = hashMap.get(cur);
            newCur.next = hashMap.get(cur.next);
            newCur.random = hashMap.get(cur.random);
            newPre.next = newCur;
            newPre = newCur.next;

            cur = cur.next;
        }

        return dummy.next;
    }

    // {-1,1,#,#}
}

class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
}
