package com.leetcode.www;

import java.util.ArrayDeque;
import java.util.Deque;

public class AddTwoNumbersII {
    /**
     * Runtime: 4 ms, faster than 79.94% of Java online submissions for Add Two Numbers II.
     * Memory Usage: 44.6 MB, less than 72.54% of Java online submissions for Add Two Numbers II.
     *
     * https://leetcode.com/problems/add-two-numbers-ii/discuss/292138/Two-solutions%3A-reverse-list-and-using-stack
     * http://baddotrobot.com/blog/2013/01/10/stack-vs-deque/
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        DequeStack<ListNode> stack1 = pushNodeToStack(l1);
        DequeStack<ListNode> stack2 = pushNodeToStack(l2);

        int carry = 0;
        ListNode prev = null;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int cur = (stack1.isEmpty() ? 0 : stack1.pop().val) + (stack2.isEmpty() ? 0 : stack2.pop().val) + carry;
            ListNode p = new ListNode(cur % 10);
            carry = cur / 10;
            p.next = prev;
            prev = p;
        }
        if (carry == 1) {
            ListNode start = new ListNode(carry);
            start.next = prev;
            return start;
        }
        return prev;
    }

    private DequeStack<ListNode> pushNodeToStack(ListNode node) {
        DequeStack<ListNode> stack = new DequeStack<>();
        while (node != null) {
            stack.push(node);
            node = node.next;
        }
        return stack;
    }

    public interface Stack<T> {
        void push(T object);
        T pop();
        boolean isEmpty();
    }

    public class DequeStack<T> implements Stack<T> {

        private final Deque<T> deque = new ArrayDeque<T>();

        @Override
        public void push(T object) {
            deque.addFirst(object);
        }

        @Override
        public T pop() {
            return deque.removeFirst();
        }

        @Override
        public boolean isEmpty() {
            return deque.size() == 0;
        }
    }
}
