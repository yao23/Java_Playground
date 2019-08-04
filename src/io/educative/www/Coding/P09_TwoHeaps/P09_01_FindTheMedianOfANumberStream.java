package io.educative.www.Coding.P09_TwoHeaps;

import java.util.*;

public class P09_01_FindTheMedianOfANumberStream {
    PriorityQueue<Integer> maxHeap; //containing first half of numbers
    PriorityQueue<Integer> minHeap; //containing second half of numbers

    /**
     * Time complexity
     * The time complexity of the insertNum() will be O(logN) due to the insertion in the heap.
     * The time complexity of the findMedian() will be O(1) as we can find the median from the top elements of the heaps.
     *
     * Space complexity
     * The space complexity will be O(N) because, as at any time, we will be storing all the numbers.
     *
     */
    public P09_01_FindTheMedianOfANumberStream() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>((a, b) -> a - b);
    }

    public void insertNum(int num) {
        if (maxHeap.isEmpty() || maxHeap.peek() >= num)
            maxHeap.add(num);
        else
            minHeap.add(num);

        // either both the heaps will have equal number of elements or max-heap will have one
        // more element than the min-heap
        if (maxHeap.size() > minHeap.size() + 1)
            minHeap.add(maxHeap.poll());
        else if (maxHeap.size() < minHeap.size())
            maxHeap.add(minHeap.poll());
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            // we have even number of elements, take the average of middle two elements
            return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
        }
        // because max-heap will have one more element than the min-heap
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        P09_01_FindTheMedianOfANumberStream medianOfAStream = new P09_01_FindTheMedianOfANumberStream();
        medianOfAStream.insertNum(3);
        medianOfAStream.insertNum(1);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(5);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(4);
        System.out.println("The median is: " + medianOfAStream.findMedian());
    }
}
