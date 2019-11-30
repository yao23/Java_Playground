package io.educative.www.Coding.P03_FastSlowPointers;

class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

class P03_04_MiddleOfTheLinkedList {
    /**
     * Time complexity
     * The above algorithm will have a time complexity of O(N) where ‘N’ is the number of nodes in the LinkedList.
     *
     * Space complexity
     * The algorithm runs in constant space O(1).
     *
     * @param head
     * @return
     */
    public static ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println("Middle Node: " + P03_04_MiddleOfTheLinkedList.findMiddle(head).value);

        head.next.next.next.next.next = new ListNode(6);
        System.out.println("Middle Node: " + P03_04_MiddleOfTheLinkedList.findMiddle(head).value);

        head.next.next.next.next.next.next = new ListNode(7);
        System.out.println("Middle Node: " + P03_04_MiddleOfTheLinkedList.findMiddle(head).value);
    }
}