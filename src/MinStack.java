/**
 * Created by liyao on 6/6/17.
 */
import java.util.Deque;
import java.util.ArrayDeque;

public class MinStack {

    private Deque<Integer> stack;
    private Deque<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new ArrayDeque<Integer>();
        minStack = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        stack.push(x);

        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            if (x <= minStack.peek()){
                minStack.push(x);
            }
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            int topElem = stack.pop();
            if (topElem == minStack.peek()) {
                minStack.pop();
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    // ["MinStack","push","push","push","getMin","pop","top","getMin"]
    //  [[],[-2],[0],[-3],[],[],[],[]]
    // => [null,null,null,null,-3,null,0,-2]
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */