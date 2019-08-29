package io.educative.www.Coding.P12_TopKElement;

import java.util.*;

public class P12_01_TopKNumbers {
    /**
     * Time complexity
     * As discussed above, the time complexity of this algorithm is (K+(N-K)*logK).
     *
     * Space complexity
     * The space complexity will be O(K) since we need to store the top ‘K’ numbers in the heap.
     *
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> findKLargestNumbers(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        // put first 'K' numbers in the min heap
        for (int i = 0; i < k; i++)
            minHeap.add(nums[i]);

        // go through the remaining numbers of the array, if the number from the array is bigger than the
        // top (smallest) number of the min-heap, remove the top number from heap and add the number from array
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(nums[i]);
            }
        }

        // the heap has the top 'K' numbers, return them in a list
        return new ArrayList<>(minHeap);
    }

    public static void main(String[] args) {
        List<Integer> result = P12_01_TopKNumbers.findKLargestNumbers(new int[] { 3, 1, 5, 12, 2, 11 }, 3);
        System.out.println("Here are the top K numbers: " + result);

        result = P12_01_TopKNumbers.findKLargestNumbers(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Here are the top K numbers: " + result);
    }
}
