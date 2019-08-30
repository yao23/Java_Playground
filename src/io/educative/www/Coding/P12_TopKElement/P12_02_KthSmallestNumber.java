package io.educative.www.Coding.P12_TopKElement;

import java.util.*;

public class P12_02_KthSmallestNumber {
    /**
     * Time complexity
     * The time complexity of the above algorithm is O(K+(N-K)*logK).
     *
     * Space complexity
     * The space complexity will be O(K) because we need to store ‘K’ smallest numbers in the heap.
     *
     * An Alternate Approach
     * Alternatively, we can use a Min Heap to find the Kth smallest number. We can insert all the numbers in the
     * min-heap and then extract the top ‘K’ numbers from the heap to find the Kth smallest number. Inserting all
     * numbers in the heap will take O(N) and extracting ‘K’ numbers will take O(KlogN). Overall, the time complexity
     * of this algorithm will be O(N+KlogN) and the space complexity will be O(N).
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthSmallestNumber(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((n1, n2) -> n2 - n1);
        // put first k numbers in the max heap
        for (int i = 0; i < k; i++)
            maxHeap.add(nums[i]);

        // go through the remaining numbers of the array, if the number from the array is smaller than the
        // top (biggest) number of the heap, remove the top number from heap and add the number from array
        for (int i = k; i < nums.length; i++) {
            if (nums[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(nums[i]);
            }
        }

        // the root of the heap has the Kth smallest number
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        int result = P12_02_KthSmallestNumber.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 3);
        System.out.println("Kth smallest number is: " + result);

        // since there are two 5s in the input array, our 3rd and 4th smallest numbers should be a '5'
        result = P12_02_KthSmallestNumber.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 4);
        System.out.println("Kth smallest number is: " + result);

        result = P12_02_KthSmallestNumber.findKthSmallestNumber(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Kth smallest number is: " + result);
    }
}
