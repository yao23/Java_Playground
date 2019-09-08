package io.educative.www.Coding.P12_TopKElement;

import java.util.*;

public class P12_10_SumOfElements {
    /**
     * Time complexity
     * Since we need to put all the numbers in a min-heap, the time complexity of the above algorithm will be O(N*logN)
     * where ‘N’ is the total input numbers.
     *
     * Space complexity
     * The space complexity will be O(N), as we need to store all the ‘N’ numbers in the heap.
     *
     * @param nums
     * @param k1
     * @param k2
     * @return
     */
    public static int findSumOfElements(int[] nums, int k1, int k2) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        // insert all numbers to the min heap
        for (int i = 0; i < nums.length; i++)
            minHeap.add(nums[i]);

        // remove k1 small numbers from the min heap
        for (int i = 0; i < k1; i++)
            minHeap.poll();

        int elementSum = 0;
        // sum next K2-1 numbers
        for (int i = 0; i < k2 - k1 - 1; i++)
            elementSum += minHeap.poll();

        return elementSum;
    }

    /**
     * Time complexity
     * Since we need to put only the top K2 numbers in the max-heap at any time, the time complexity of the above
     * algorithm will be O(N*logK2).
     *
     * Space complexity
     * The space complexity will be O(K2), as we need to store the smallest ‘K2’ numbers in the heap.
     *
     * @param nums
     * @param k1
     * @param k2
     * @return
     */
    public static int findSumOfElementsV1(int[] nums, int k1, int k2) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((n1, n2) -> n2 - n1);
        // keep smallest k2 numbers in the max heap
        for (int i = 0; i < nums.length; i++) {
            if (i < k2 - 1) {
                maxHeap.add(nums[i]);
            } else if (nums[i] < maxHeap.peek()) {
                maxHeap.poll(); // as we are interested only in the smallest k2 numbers
                maxHeap.add(nums[i]);
            }
        }

        // get the sum of numbers between k1 and k2 indices
        // these numbers will be at the top of the max heap
        int elementSum = 0;
        for (int i = 0; i < k2 - k1 - 1; i++)
            elementSum += maxHeap.poll();

        return elementSum;
    }

    public static void main(String[] args) {
        int result = P12_10_SumOfElements.findSumOfElements(new int[] { 1, 3, 12, 5, 15, 11 }, 3, 6);
        System.out.println("Sum of all numbers between k1 and k2 smallest numbers: " + result);

        result = P12_10_SumOfElements.findSumOfElements(new int[] { 3, 5, 8, 7 }, 1, 4);
        System.out.println("Sum of all numbers between k1 and k2 smallest numbers: " + result);
    }
}
