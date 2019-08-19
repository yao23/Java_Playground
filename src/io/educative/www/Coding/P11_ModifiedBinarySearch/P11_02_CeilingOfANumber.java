package io.educative.www.Coding.P11_ModifiedBinarySearch;

public class P11_02_CeilingOfANumber {
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
    public static int searchCeilingOfANumber(int[] arr, int key) {
        if (key > arr[arr.length - 1]) // if the 'key' is bigger than the biggest element
            return -1;

        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key < arr[mid]) {
                end = mid - 1;
            } else if (key > arr[mid]) {
                start = mid + 1;
            } else { // found the key
                return mid;
            }
        }
        // since the loop is running until 'start <= end', so at the end of the while loop, 'start == end+1'
        // we are not able to find the element in the given array, so the next big number will be arr[start]
        return start;
    }

    public static void main(String[] args) {
        System.out.println(P11_02_CeilingOfANumber.searchCeilingOfANumber(new int[] { 4, 6, 10 }, 6));
        System.out.println(P11_02_CeilingOfANumber.searchCeilingOfANumber(new int[] { 1, 3, 8, 10, 15 }, 12));
        System.out.println(P11_02_CeilingOfANumber.searchCeilingOfANumber(new int[] { 4, 6, 10 }, 17));
        System.out.println(P11_02_CeilingOfANumber.searchCeilingOfANumber(new int[] { 4, 6, 10 }, -1));
    }
}
