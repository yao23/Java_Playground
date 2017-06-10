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
