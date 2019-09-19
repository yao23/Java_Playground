package io.educative.www.Coding.P13_KWayMerge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class P13_04_SmallestNumberRange {
    /**
     * Time complexity
     * Since, at most, we’ll be going through all the elements of all the arrays and will remove/add one element in the
     * heap in each step, the time complexity of the above algorithm will be O(N*logM) where ‘N’ is the total
     * number of elements in all the ‘M’ input arrays.
     *
     * Space complexity
     * The space complexity will be O(M) because, at any time, our min-heap will be store one number from all the ‘M’
     * input arrays.
     *
     * @param lists
     * @return
     */
    public static int[] findSmallestRange(List<Integer[]> lists) {
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>(
                Comparator.comparingInt(n -> lists.get(n.arrayIndex)[n.elementIndex]));

        int rangeStart = 0, rangeEnd = Integer.MAX_VALUE, currentMaxNumber = Integer.MIN_VALUE;
        // put the 1st element of each array in the min heap
        for (int i = 0; i < lists.size(); i++)
            if (lists.get(i) != null) {
                minHeap.add(new Node(0, i));
                currentMaxNumber = Math.max(currentMaxNumber, lists.get(i)[0]);
            }

        // take the smallest (top) element form the min heap, if it gives us smaller range, update the ranges
        // if the array of the top element has more elements, insert the next element in the heap
        while (minHeap.size() == lists.size()) {
            Node node = minHeap.poll();
            if (rangeEnd - rangeStart > currentMaxNumber - lists.get(node.arrayIndex)[node.elementIndex]) {
                rangeStart = lists.get(node.arrayIndex)[node.elementIndex];
                rangeEnd = currentMaxNumber;
            }
            node.elementIndex++;
            if (lists.get(node.arrayIndex).length > node.elementIndex) {
                minHeap.add(node); // insert the next element in the heap
                currentMaxNumber = Math.max(currentMaxNumber, lists.get(node.arrayIndex)[node.elementIndex]);
            }
        }
        return new int[] { rangeStart, rangeEnd };
    }

    public static void main(String[] args) {
        Integer[] l1 = new Integer[] { 1, 5, 8 };
        Integer[] l2 = new Integer[] { 4, 12 };
        Integer[] l3 = new Integer[] { 7, 8, 10 };
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int[] result = P13_04_SmallestNumberRange.findSmallestRange(lists);
        System.out.print("Smallest range is: [" + result[0] + ", " + result[1] + "]");
    }
}
