package io.educative.www.Coding.P13_KWayMerge;
import java.util.*;

public class P13_02_KthSmallestNumberInMSortedLists {
    /**
     * Time complexity
     * Since we’ll be going through at most ‘K’ elements among all the arrays, and we will remove/add one element in the
     * heap in each step, the time complexity of the above algorithm will be O(K*logM) where ‘M’ is the total number of
     * input arrays.
     *
     * Space complexity
     * The space complexity will be O(M) because, at any time, our min-heap will be storing one number from all the ‘M’
     * input arrays.
     *
     * Similar Problems
     * Problem 1: Given ‘M’ sorted arrays, find the median number among all arrays.
     *
     * Solution: This problem is similar to our parent problem with K=Median. So if there are ‘N’ total numbers in all
     * the arrays we need to find the K’th minimum number where K=N/2.
     *
     * Problem 2: Given a list of ‘K’ sorted arrays, merge them into one sorted list.
     *
     * Solution: This problem is similar to Merge K Sorted Lists except that the input is a list of arrays compared to
     * LinkedLists. To handle this, we can use a similar approach as discussed in our parent problem. We will create a
     * Node class which will track the array and the element indices.
     *
     * @param lists
     * @param k
     * @return
     */
    public static int findKthSmallest(List<Integer[]> lists, int k) {
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>(
                (n1, n2) -> lists.get(n1.arrayIndex)[n1.elementIndex] - lists.get(n2.arrayIndex)[n2.elementIndex]);

        // put the 1st element of each array in the min heap
        for (int i = 0; i < lists.size(); i++)
            if (lists.get(i) != null)
                minHeap.add(new Node(0, i));

        // take the smallest (top) element form the min heap, if the running count is equal to k return the number
        // if the array of the top element has more elements, add the next element to the heap
        int numberCount = 0, result = 0;
        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            result = lists.get(node.arrayIndex)[node.elementIndex];
            if (++numberCount == k)
                break;
            node.elementIndex++;
            if (lists.get(node.arrayIndex).length > node.elementIndex)
                minHeap.add(node);
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] l1 = new Integer[] { 2, 6, 8 };
        Integer[] l2 = new Integer[] { 3, 6, 7 };
        Integer[] l3 = new Integer[] { 1, 3, 4 };
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int result = P13_02_KthSmallestNumberInMSortedLists.findKthSmallest(lists, 5);
        System.out.print("Kth smallest number is: " + result);
    }
}

class Node {
    int elementIndex;
    int arrayIndex;

    Node(int elementIndex, int arrayIndex) {
        this.elementIndex = elementIndex;
        this.arrayIndex = arrayIndex;
    }
}