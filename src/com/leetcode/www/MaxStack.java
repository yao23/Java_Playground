import java.util.ArrayDeque;
import java.util.Deque;

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
