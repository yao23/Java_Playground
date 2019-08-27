package io.educative.www.Coding.P11_ModifiedBinarySearch;

public class P11_09_SearchInRotatedArray {
    /**
     * Time complexity
     * Since we are reducing the search range by half at every step, this means that the time complexity of our
     * algorithm will be O(logN) where ‘N’ is the total elements in the given array.
     *
     * Space complexity
     * The algorithm runs in constant space O(1).
     *
     * @param arr
     * @param key
     * @return
     */
    public static int search(int[] arr, int key) {
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == key)
                return mid;

            if (arr[start] <= arr[mid]) { // left side is sorted in ascending order
                if (key >= arr[start] && key < arr[mid]) {
                    end = mid - 1;
                } else { //key > arr[mid]
                    start = mid + 1;
                }
            } else { // right side is sorted in ascending order
                if (key > arr[mid] && key <= arr[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        // we are not able to find the element in the given array
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(P11_09_SearchInRotatedArray.search(new int[] { 10, 15, 1, 3, 8 }, 15));
        System.out.println(P11_09_SearchInRotatedArray.search(new int[] { 4, 5, 7, 9, 10, -1, 2 }, 10));
    }
}
