package com.leetcode.www;

import java.util.*;

/**
 * Created by liyao on 7/17/17.
 */
public class FlattenNestedListIterator { // LC 341
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface com.leetcode.www.NestedInteger {
     *
     *     // @return true if this com.leetcode.www.NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this com.leetcode.www.NestedInteger holds, if it holds a single integer
     *     // Return null if this com.leetcode.www.NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // @return the nested list that this com.leetcode.www.NestedInteger holds, if it holds a nested list
     *     // Return null if this com.leetcode.www.NestedInteger holds a single integer
     *     public List<com.leetcode.www.NestedInteger> getList();
     * }
     */
    class NestedIterator implements Iterator<Integer> { // beats 50.76%
        private Deque<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new ArrayDeque<>();
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                NestedInteger top = stack.peek();
                if (top.isInteger()) {
                    return true;
                } else {
                    top = stack.pop();
                    List<NestedInteger> list = top.getList();
                    for (int i = list.size() - 1; i >= 0; i--) {
                        stack.push(list.get(i));
                    }
                }
            }

            return false;
        }

        @Override
        public void remove() {

        }
    }

    class NestedIteratorV0 implements Iterator<Integer> {
        private Iterator<NestedInteger> iterator;
        private Deque<Iterator<NestedInteger>> parentIterators;

        public NestedIteratorV0(List<NestedInteger> nestedList) {
            iterator = nestedList.iterator();
            parentIterators = new ArrayDeque<>();
        }

        @Override
        public Integer next() {
            NestedInteger cur = iterator.next();
            if (cur.isInteger()) {
                return cur.getInteger();
            } else {
                boolean isInt = false;
                while (cur == null || ((List)cur).size() == 0 && hasNext()) {
                    cur = iterator.next();
                    if (cur.isInteger()) {
                        isInt = true;
                        break;
                    }
                }

                if (isInt) {
                    return cur.getInteger();
                } else {
                    if (hasNext()) {
                        return getNext(cur.getList());
                    } else {
                        return Integer.MIN_VALUE;
                    }
                }
            }
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext() || !parentIterators.isEmpty();
        }

        @Override
        public void remove() {

        }

        private int getNext(List<NestedInteger> cur) {
            Iterator<NestedInteger> tmp = cur.iterator();

            while (!tmp.next().isInteger() && hasNext()) {
                if (tmp == null) {
                    return Integer.MIN_VALUE;
                } else {
                    NestedInteger tmpList = tmp.next();
                    if (((List)tmpList).size() == 0) {
                        tmp.next();
                    } else {
                        Iterator<NestedInteger> parent = iterator;
                        parentIterators.push(parent);
                        iterator = tmp;
                        tmp.next();
                    }
                }
            }

            if (tmp.next().isInteger()) {
                return tmp.next().getInteger();
            } else {
                return Integer.MIN_VALUE;
            }
        }


    }

    // [[1,1],2,[1,1]] => [1,1,2,1,1]
    // [1,[4,[6]]] => [1,4,6]
    // [1,[4,[6],5]] => [1,4,6,5]
    // [1,[4,[6,7],5]] => [1,4,6,7,5]

    /**
     * Your NestedIterator object will be instantiated and called as such:
     * NestedIterator i = new NestedIterator(nestedList);
     * while (i.hasNext()) v[f()] = i.next();
     */
}


interface NestedInteger {
    public boolean isInteger();

    public Integer getInteger();

    public List<NestedInteger> getList();
}
