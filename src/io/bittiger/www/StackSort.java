package io.bittiger.www; /**
 * Created by liyao on 6/10/17.
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class StackSort {
    public void stackSort(Deque<Integer> stack) {
        if (stack.size() <= 1) {
            return;
        }
        Deque<Integer> helperStack = new ArrayDeque<>();

        while (!stack.isEmpty()) {
            if (helperStack.isEmpty()) {
                helperStack.push(stack.pop());
            } else { // helper stack is not empty
                int topElem = stack.pop();
                if (topElem < helperStack.peek()) { // top element is smaller than top one in helperStack
                    helperStack.push(topElem);
                    while (!stack.isEmpty() && stack.peek() <= helperStack.peek()) {
                        helperStack.push(stack.pop());
                    }
                } else {
                    while (!helperStack.isEmpty() && helperStack.peek() < topElem) {
                        stack.push(helperStack.pop());
                    }
                    helperStack.push(topElem);
                }
            }
        }

        while (!helperStack.isEmpty()) {
            stack.push(helperStack.pop());
        }
    }

    // [3,1,2,4]
}
