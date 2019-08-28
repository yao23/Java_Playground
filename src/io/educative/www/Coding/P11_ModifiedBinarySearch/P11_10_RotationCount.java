package io.educative.www.Coding.P11_ModifiedBinarySearch;

public class P11_10_RotationCount {
    /**
     * Time complexity
     * Since we are reducing the search range by half at every step, this means that the time complexity of our
     * algorithm will be O(logN) where ‘N’ is the total elements in the given array.
     *
     * Space complexity
     * The algorithm runs in constant space O(1).
     *
     * @param arr
     * @return
     */
    public static int countRotations(int[] arr) {
        int start = 0, end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;

            if (mid < end && arr[mid] > arr[mid + 1]) // if mid is greater than the next element
                return mid + 1;
            if (mid > start && arr[mid - 1] > arr[mid]) // if mid is smaller than the previous element
                return mid;

            if (arr[start] < arr[mid]) { // left side is sorted, so the pivot is on right side
                start = mid + 1;
            } else { // right side is sorted, so the pivot is on the left side
                end = mid - 1;
            }
        }

        return 0; // the array has not been rotated
    }

    public static void main(String[] args) {
        System.out.println(P11_10_RotationCount.countRotations(new int[] { 10, 15, 1, 3, 8 }));
        System.out.println(P11_10_RotationCount.countRotations(new int[] { 4, 5, 7, 9, 10, -1, 2 }));
        System.out.println(P11_10_RotationCount.countRotations(new int[] { 1, 3, 8, 10 }));
    }
}
