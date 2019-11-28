package io.educative.www.Coding.P03_FastSlowPointers;

class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

class P03_01_LinkedListCycle {
    /**
     * Time Complexity
     * As we have concluded above, once the slow pointer enters the cycle, the fast pointer will meet the slow pointer
     * in the same loop. Therefore, the time complexity of our algorithm will be O(N) where ‘N’ is the total number of
     * nodes in the LinkedList.
     *
     * Space Complexity
     * The algorithm runs in constant space O(1).
     *
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast)
                return true; // found the cycle
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println("LinkedList has cycle: " + P03_01_LinkedListCycle.hasCycle(head));

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList has cycle: " + P03_01_LinkedListCycle.hasCycle(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList has cycle: " + P03_01_LinkedListCycle.hasCycle(head));
    }
}