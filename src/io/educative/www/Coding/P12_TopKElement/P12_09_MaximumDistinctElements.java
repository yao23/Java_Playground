package io.educative.www.Coding.P12_TopKElement;

import java.util.*;

public class P12_09_MaximumDistinctElements {
    /**
     * Time complexity
     * Since we will insert all numbers in a HashMap and a Min Heap, this will take O(N) where ‘N’ is the total input
     * numbers. While extracting numbers from the heap, in the worst case, we will need to take out ‘K’ numbers. This
     * will happen when we have at least ‘K’ numbers with a frequency of two. Since the heap can have a maximum of ‘N/2’
     * numbers, therefore, extracting an element from the heap will take O(logN) and extracting ‘K’ numbers will take
     * O(KlogN). So overall, the time complexity of our algorithm will be O(N + KlogN).
     *
     * We can optimize the above algorithm and only push ‘K’ elements in the heap, as in the worst case we will be
     * extracting ‘K’ elements from the heap. This optimization will reduce the overall time complexity to O(N + KlogK).
     *
     * Space complexity
     * The space complexity will be O(N) as, in the worst case, we need to store all the ‘N’ characters in the HashMap.
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findMaximumDistinctElements(int[] nums, int k) {
        int distinctElementsCount = 0;
        if (nums.length <= k)
            return distinctElementsCount;

        // find the frequency of each number
        Map<Integer, Integer> numFrequencyMap = new HashMap<>();
        for (int i : nums)
            numFrequencyMap.put(i, numFrequencyMap.getOrDefault(i, 0) + 1);

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<Map.Entry<Integer, Integer>>(
                (e1, e2) -> e1.getValue() - e2.getValue());

        // insert all numbers with frequency greater than '1' into the min-heap
        for (Map.Entry<Integer, Integer> entry : numFrequencyMap.entrySet()) {
            if (entry.getValue() == 1)
                distinctElementsCount++;
            else
                minHeap.add(entry);
        }

        // following a greedy approach, try removing the least frequent numbers first from the min-heap
        while (k > 0 && !minHeap.isEmpty()) {
            Map.Entry<Integer, Integer> entry = minHeap.poll();
            // to make an element distinct, we need to remove all of its occurrences except one
            k -= entry.getValue() - 1;
            if (k >= 0)
                distinctElementsCount++;
        }

        // if k > 0, this means we have to remove some distinct numbers
        if (k > 0)
            distinctElementsCount -= k;

        return distinctElementsCount;
    }

    public static void main(String[] args) {
        int result = P12_09_MaximumDistinctElements.findMaximumDistinctElements(new int[] { 7, 3, 5, 8, 5, 3, 3 }, 2);
        System.out.println("Maximum distinct numbers after removing K numbers: " + result);

        result = P12_09_MaximumDistinctElements.findMaximumDistinctElements(new int[] { 3, 5, 12, 11, 12 }, 3);
        System.out.println("Maximum distinct numbers after removing K numbers: " + result);

        result = P12_09_MaximumDistinctElements.findMaximumDistinctElements(new int[] { 1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5 }, 2);
        System.out.println("Maximum distinct numbers after removing K numbers: " + result);
    }
}
