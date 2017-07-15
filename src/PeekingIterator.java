/**
 * Created by liyao on 7/15/17.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PeekingIterator implements Iterator<Integer> {
    private boolean isPeeked;
    private int peekedInt;
    private Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        isPeeked = false;
        peekedInt = 0;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (isPeeked) {
            return peekedInt;
        } else {
            if (hasNext()) {
                peekedInt = iterator.next();
            } else {
                peekedInt = Integer.MIN_VALUE;
            }

            isPeeked = true;
            return peekedInt;
        }
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (isPeeked) {
            isPeeked = false;
            return peekedInt;
        } else {
            if (hasNext()) {
                peekedInt = iterator.next();
            } else {
                peekedInt = Integer.MIN_VALUE;
            }

            return peekedInt;
        }
    }

    @Override
    public boolean hasNext() {
        if (isPeeked) {
            return true;
        } else {
            return iterator.hasNext();
        }
    }

    @Override
    public void remove() {
        iterator.remove();
    }

    private static void printOps(int mode, PeekingIterator peekingIterator) {
        switch (mode) {
            case 0:
                System.out.println("hasNext: " + (peekingIterator.hasNext() ? "true" : "false"));
                break;
            case 1:
                System.out.println("peek: " + peekingIterator.peek());
                break;
            case 2:
                System.out.println("next: " + peekingIterator.next());
                break;
            default:
                break;
        }
    }

    private static void executeOps(int[] ops, List<Integer> input) {
        List<Integer> list = new ArrayList<>(input);
        PeekingIterator peekingIterator = new PeekingIterator(list.iterator());
        for (int i = 0; i < ops.length; i++) {
            printOps(ops[i], peekingIterator);
        }
    }


    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3);
        int[] ops = new int[]{2, 1, 2, 2};
        System.out.println("List 1");
        executeOps(ops, list);

        System.out.println();
        System.out.println("List 2");
        List<Integer> list1 = Arrays.asList(1,2,3,4);
        int[] ops1 = new int[]{0,1,1,2,2,1,1,2,0,1,0,2,0};
        executeOps(ops1, list1);
    }

    // [1, 2, 3], [2, 1, 2, 2] => ["1","2","2","3"]
    // [1,2,3,4], [0,1,1,2,2,1,1,2,0,1,0,2,0] => ["true","1","1","1","2","3","3","3","true","4","true","4","false"]

    // beats 17.20%
}
