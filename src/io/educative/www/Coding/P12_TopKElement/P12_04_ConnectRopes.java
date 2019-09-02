package io.educative.www.Coding.P12_TopKElement;

import java.util.*;

public class P12_04_ConnectRopes {
    /**
     * Time complexity
     * Given ‘N’ ropes, we need O(N*logN) to insert all the ropes in the heap. In each step, while processing the heap,
     * we take out two elements from the heap and insert one. This means we will have a total of ‘N’ steps, having a
     * total time complexity of O(N*logN).
     *
     * Space complexity
     * The space complexity will be O(N) because we need to store all the ropes in the heap.
     *
     * @param ropeLengths
     * @return
     */
    public static int minimumCostToConnectRopes(int[] ropeLengths) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        // add all ropes to the min heap
        for (int i = 0; i < ropeLengths.length; i++)
            minHeap.add(ropeLengths[i]);

        // go through the values of the heap, in each step take top (lowest) rope lengths from the min heap
        // connect them and push the result back to the min heap.
        // keep doing this until the heap is left with only one rope
        int result = 0, temp = 0;
        while (minHeap.size() > 1) {
            temp = minHeap.poll() + minHeap.poll();
            result += temp;
            minHeap.add(temp);
        }

        return result;
    }

    public static void main(String[] args) {
        int result = P12_04_ConnectRopes.minimumCostToConnectRopes(new int[] { 1, 3, 11, 5 });
        System.out.println("Minimum cost to connect ropes: " + result);
        result = P12_04_ConnectRopes.minimumCostToConnectRopes(new int[] { 1, 3, 11, 5, 2 });
        System.out.println("Minimum cost to connect ropes: " + result);
    }
}
