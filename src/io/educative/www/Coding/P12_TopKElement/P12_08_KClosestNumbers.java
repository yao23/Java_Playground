package io.educative.www.Coding.P12_TopKElement;

import java.util.*;

public class P12_08_KClosestNumbers {
    /**
     * Time complexity
     * The time complexity of the above algorithm is O(logN + K*logK). We need O(logN) for Binary Search and O(K*logK)
     * to insert the numbers in the Min Heap, as well as to sort the output array.
     *
     * Space complexity
     * The space complexity will be O(K), as we need to put a maximum of 2K numbers in the heap.
     *
     * @param arr
     * @param K
     * @param X
     * @return
     */
    public static List<Integer> findClosestElements(int[] arr, int K, Integer X) {
        int index = binarySearch(arr, X);
        int low = index - K, high = index + K;
        low = Math.max(low, 0); // 'low' should not be less than zero
        high = Math.min(high, arr.length - 1); // 'high' should not be greater the size of the array

        PriorityQueue<Entry> minHeap = new PriorityQueue<>((n1, n2) -> n1.key - n2.key);
        // add all candidate elements to the min heap, sorted by their absolute difference from 'X'
        for (int i = low; i <= high; i++)
            minHeap.add(new Entry(Math.abs(arr[i] - X), i));

        // we need the top 'K' elements having smallest difference from 'X'
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < K; i++)
            result.add(arr[minHeap.poll().value]);

        Collections.sort(result);
        return result;
    }

    private static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (low > 0) {
            return low - 1;
        }
        return low;
    }

    /**
     * Time complexity
     * The time complexity of the above algorithm is O(logN + K). We need O(logN) for Binary Search and O(K) for
     * finding the ‘K’ closest numbers using the two pointers.
     *
     * Space complexity
     * If we ignoring the space required for the output list, the algorithm runs in constant space O(1).
     *
     * @param arr
     * @param K
     * @param X
     * @return
     */
    public static List<Integer> findClosestElementsV1(int[] arr, int K, Integer X) {
        List<Integer> result = new LinkedList<>();
        int index = binarySearch(arr, X);
        int leftPointer = index;
        int rightPointer = index + 1;
        for (int i = 0; i < K; i++) {
            if (leftPointer >= 0 && rightPointer < arr.length) {
                int diff1 = Math.abs(X - arr[leftPointer]);
                int diff2 = Math.abs(X - arr[rightPointer]);
                if (diff1 <= diff2)
                    result.add(0, arr[leftPointer--]); // append in the beginning
                else
                    result.add(arr[rightPointer++]); // append at the end
            } else if (leftPointer >= 0) {
                result.add(0, arr[leftPointer--]);
            } else if (rightPointer < arr.length) {
                result.add(arr[rightPointer++]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = P12_08_KClosestNumbers.findClosestElements(new int[] { 5, 6, 7, 8, 9 }, 3, 7);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = P12_08_KClosestNumbers.findClosestElements(new int[] { 2, 4, 5, 6, 9 }, 3, 6);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = P12_08_KClosestNumbers.findClosestElements(new int[] { 2, 4, 5, 6, 9 }, 3, 10);
        System.out.println("'K' closest numbers to 'X' are: " + result);
    }
}

class Entry {
    int key;
    int value;

    public Entry(int key, int value) {
        this.key = key;
        this.value = value;
    }
}