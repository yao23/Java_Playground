package io.educative.www.Coding.P13_KWayMerge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class P13_05_KPairsWithLargestSums {
    /**
     * Time complexity
     * Since, at most, we’ll be going through all the elements of both arrays and we will add/remove one element in the
     * heap in each step, the time complexity of the above algorithm will be O(N*M*logK) where ‘N’ and ‘M’ are the total
     * number of elements in both arrays, respectively.
     *
     * If we assume that both arrays have at least ‘K’ elements then the time complexity can be simplified to O(K^2logK),
     * because we are not iterating more than ‘K’ elements in both arrays.
     *
     * Space complexity
     * The space complexity will be O(K) because, at any time, our Min Heap will be storing ‘K’ largest pairs.
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public static List<int[]> findKLargestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((p1, p2) -> (p1[0] + p1[1]) - (p2[0] + p2[1]));
        for (int i = 0; i < nums1.length && i < k; i++) {
            for (int j = 0; j < nums2.length && j < k; j++) {
                if (minHeap.size() < k) {
                    minHeap.add(new int[] { nums1[i], nums2[j] });
                } else {
                    // if the sum of the two numbers from the two arrays is smaller than the smallest (top) element of
                    // the heap, we can 'break' here. Since the arrays are sorted in the descending order, we'll not be
                    // able to find a pair with a higher sum moving forward.
                    if (nums1[i] + nums2[j] < minHeap.peek()[0] + minHeap.peek()[1]) {
                        break;
                    } else { // else: we have a pair with a larger sum, remove top and insert this pair in the heap
                        minHeap.poll();
                        minHeap.add(new int[] { nums1[i], nums2[j] });
                    }
                }
            }
        }
        return new ArrayList<>(minHeap);
    }

    public static void main(String[] args) {
        int[] l1 = new int[] { 9, 8, 2 };
        int[] l2 = new int[] { 6, 3, 1 };
        List<int[]> result = P13_05_KPairsWithLargestSums.findKLargestPairs(l1, l2, 3);
        System.out.print("Pairs with largest sum are: ");
        for (int[] pair : result)
            System.out.print("[" + pair[0] + ", " + pair[1] + "] ");
    }
}
