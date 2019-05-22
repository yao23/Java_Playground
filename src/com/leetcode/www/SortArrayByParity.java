package com.leetcode.www;

public class SortArrayByParity {
    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Sort Array By Parity.
     * Memory Usage: 40.4 MB, less than 89.85% of Java online submissions for Sort Array By Parity.
     *
     * https://leetcode.com/problems/sort-array-by-parity/discuss/170734/C%2B%2BJava-In-Place-Swap
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParity(int[] A) {
        for (int i = 0, j = 0; j < A.length; j++)
            if (A[j] % 2 == 0) {
                int tmp = A[i];
                A[i++] = A[j];
                A[j] = tmp;
            }
        return A;
    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Sort Array By Parity.
     * Memory Usage: 40.4 MB, less than 89.34% of Java online submissions for Sort Array By Parity.
     *
     * https://leetcode.com/problems/sort-array-by-parity/discuss/297258/Java-solution-O(n)-beats-100
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityV0(int[] A) {
        int oddNumIndex = 0;
        int evenNumIndex = 0;

        while (evenNumIndex < A.length){

            if (A[evenNumIndex] % 2 == 0 && oddNumIndex < evenNumIndex){

                // if i is still even
                if (A[oddNumIndex] % 2 == 0) {
                    oddNumIndex += 1;
                    continue;
                }

                // can swap odd with even number
                int temp = A[oddNumIndex];
                A[oddNumIndex] = A[evenNumIndex];
                A[evenNumIndex] = temp;

                oddNumIndex += 1;
            }

            evenNumIndex += 1;

        }

        return A;
    }
}
