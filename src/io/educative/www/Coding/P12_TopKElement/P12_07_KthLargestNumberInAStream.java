package io.educative.www.Coding.P12_TopKElement;

import java.util.*;

public class P12_07_KthLargestNumberInAStream {
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
    final int k;

    public P12_07_KthLargestNumberInAStream(int[] nums, int k) {
        this.k = k;
        // add the numbers in the min heap
        for (int i = 0; i < nums.length; i++)
            add(nums[i]);
    }

    /**
     * Time complexity
     * The time complexity of the add() function will be O(logK) since we are inserting the new number in the heap.
     *
     * Space complexity
     * The space complexity will be O(K) for storing numbers in the heap.
     *
     * @param num
     */
    public int add(int num) {
        // add the new number in the min heap
        minHeap.add(num);

        // if heap has more than 'k' numbers, remove one number
        if (minHeap.size() > this.k)
            minHeap.poll();

        // return the 'Kth largest number
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] input = new int[] { 3, 1, 5, 12, 2, 11 };
        P12_07_KthLargestNumberInAStream kthLargestNumber = new P12_07_KthLargestNumberInAStream(input, 4);
        System.out.println("4th largest number is: " + kthLargestNumber.add(6));
        System.out.println("4th largest number is: " + kthLargestNumber.add(13));
        System.out.println("4th largest number is: " + kthLargestNumber.add(4));
    }
}
