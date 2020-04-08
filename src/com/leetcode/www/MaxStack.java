import java.util.*;

public class MaxStack { // 716
    /**
     * Runtime: 25 ms, faster than 9.94% of Java online submissions for Max Stack.
     * Memory Usage: 52.6 MB, less than 8.33% of Java online submissions for Max Stack.
     *
     * Time Complexity: O(N) for the popMax operation, and O(1) for the other operations, where N is the number of operations performed.
     *
     * Space Complexity: O(N), the maximum size of the stack.
     */

    private Deque<Integer> stack;
    private Deque<Integer> maxStack;

    /** initialize your data structure here. */
    public MaxStack() {
        stack = new ArrayDeque<>();
        maxStack = new ArrayDeque<>();
    }

    public void push(int x) {
        int max = maxStack.isEmpty() ? x : maxStack.peek();
        maxStack.push(max > x ? max : x);
        stack.push(x);
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int max = peekMax();
        Deque<Integer> buffer = new ArrayDeque();
        while (top() != max) {
            buffer.push(pop());
        }
        pop();
        while (!buffer.isEmpty()) push(buffer.pop());
        return max;
    }
}

class MaxStackV1 {
    TreeMap<Integer, List<Node>> map; // sorted by key
    DoubleLinkedList dll;

    public MaxStackV1() {
        map = new TreeMap<>();
        dll = new DoubleLinkedList();
    }

    public void push(int x) {
        Node node = dll.add(x);
        if(!map.containsKey(x))
            map.put(x, new ArrayList<>());
        map.get(x).add(node);
    }

    public int pop() {
        int val = dll.pop();
        List<Node> L = map.get(val);
        L.remove(L.size() - 1);
        if (L.isEmpty()) map.remove(val);
        return val;
    }

    public int top() {
        return dll.peek();
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = peekMax();
        List<Node> L = map.get(max);
        Node node = L.remove(L.size() - 1);
        dll.unlink(node);
        if (L.isEmpty()) map.remove(max);
        return max;
    }
}

/**
 * Runtime: 17 ms, faster than 59.76% of Java online submissions for Max Stack.
 * Memory Usage: 41.6 MB, less than 100.00% of Java online submissions for Max Stack.
 *
 * Time Complexity: OO(logN) for all operations except peek which is O(1), where NN is the number of operations performed.
 * Most operations involving TreeMap are O(logN).
 *
 * Space Complexity: O(N), the size of the data structures used.
 *
 */
class DoubleLinkedList {
    Node head, tail;

    public DoubleLinkedList() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    public Node add(int val) {
        Node x = new Node(val);
        x.next = tail;
        x.prev = tail.prev;
        tail.prev = tail.prev.next = x;
        return x;
    }

    public int pop() {
        return unlink(tail.prev).val;
    }

    public int peek() {
        return tail.prev.val;
    }

    public Node unlink(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }
}

class Node {
    int val;
    Node prev, next;
    public Node(int v) {val = v;}
}
